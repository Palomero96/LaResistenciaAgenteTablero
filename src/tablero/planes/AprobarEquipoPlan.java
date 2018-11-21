package tablero.planes;

import java.util.*;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.conceptos.*;
import ontologia.predicados.*;
import ontologia.acciones.*;
import ontologia.*;

/**
 *  Plan para pedir un equipo al lider y
 */
public class AprobarEquipoPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		Lista_Jugadores equipo = (Lista_Jugadores) getBeliefbase().getBelief("Equipo").getFact();
		// Reset de votos de equipo en las creencias
		getBeliefbase().getBeliefSet("votosequipo").removeFacts();
		System.out.println("INICIANDO APROBAREQUIPO");		
		int votosContra = 0;
		for (int i = 0; i < jugadores.getjugadores().size(); i++ ) {
			System.out.println(i);
			IMessageEvent request = createMessageEvent("Request_Aprobar_equipo");
			Aprobar_equipo accionAprobarEquipo = new Aprobar_equipo();
			request.setContent(accionAprobarEquipo);
			System.out.println("Tablero envia voto al jugador: "+ jugadores.getjugadores().get(i).getIDAgente());
			request.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
			IMessageEvent reply = sendMessageAndWait(request,1000);
			// Una vez recibido el reply
			accionAprobarEquipo = (Aprobar_equipo) reply.getContent();
			System.out.println(accionAprobarEquipo + " tablero");
			System.out.println(accionAprobarEquipo.getvoto() + " tablero");
			System.out.println(accionAprobarEquipo.getvoto().getequipo() + " tablero");

			getBeliefbase().getBeliefSet("votosequipo").addFact(accionAprobarEquipo.getvoto());

			if (!accionAprobarEquipo.getvoto().getequipo()) {
				++votosContra;
			}
		}
		
		Resultado resultVotos = new Resultado();
		int votacionesRechazadas = 0;
		
		if (votosContra < 4) {
			System.out.println("Equipo aprobado");
			resultVotos.setResultadoequipo(true);
			getBeliefbase().getBelief("ResultadoEquipo").setFact(true);

		}else {
			System.out.println("Equipo no aprobado");
			votacionesRechazadas = (int) getBeliefbase().getBelief("VotacionesRechazadas").getFact();
			++votacionesRechazadas;

			getBeliefbase().getBelief("VotacionesRechazadas").setFact(votacionesRechazadas);
			if(votacionesRechazadas == 5) {
			Partida_Finalizada finPartida = new Partida_Finalizada();
			finPartida.setGanaResistencia(false);
			
			for (int i = 0; i < jugadores.getjugadores().size(); i++) {				
				IMessageEvent informFinalPartida = createMessageEvent("Inform_Partida_Finalizada");
				informFinalPartida.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
				informFinalPartida.setContent(finPartida);
				sendMessage(informFinalPartida);			
			}	
			System.out.println("GANAN LOS ESPIAS");
			getBeliefbase().getBelief("Preparada").setFact(false);
			getBeliefbase().getBelief("FinPartida").setFact(true);		
		
		}
			resultVotos.setResultadoequipo(false);
			getBeliefbase().getBelief("LiderAsignado").setFact(false);
		}

		Votacion_publicada_equipo votacion = new Votacion_publicada_equipo();
		votacion.setResultado(resultVotos);
		
		for (int i = 0; i < jugadores.getjugadores().size(); i++) {
			IMessageEvent informVotacion = createMessageEvent("Inform_Votacion_publicada_equipo");
			informVotacion.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
			informVotacion.setContent(votacion);
			sendMessage(informVotacion);			
		}	
		getBeliefbase().getBelief("ActivarAprobarEquipo").setFact(false);
		getBeliefbase().getBelief("VotacionEquipoRealizada").setFact(true);
		
	}
}