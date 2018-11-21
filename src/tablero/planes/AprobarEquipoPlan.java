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
 *  Plan para pedir votacion a los jugadores, si quieren que el equipo que ha escogido el lider vaya o no a la misión
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
		ArrayList <Voto> votosEquipo = new ArrayList <Voto>();
		//contador de votos en contra, se usara para saber si el equipo va o no a la mision
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
			Voto voto = new Voto();
			voto.setequipo(accionAprobarEquipo.getvoto().getequipo());
			voto.setIDAgente((AgentIdentifier) reply.getParameter("sender").getValue());
			votosEquipo.add(voto);
			//si el jugador vota no ir el equipo a la mision
			if (!accionAprobarEquipo.getvoto().getequipo()) {
				++votosContra;
			}
		}
		
		Resultado resultVotos = new Resultado();
		//contador de rechazos de equipo. Se usa para saber cuantos equipos rechazados llevamos en una misma ronda
		int votacionesRechazadas = 0;
		
		//si hay menor cantidad de votos en contra que a favor, se aprueba el equipo
		if (votosContra < 4) {
			System.out.println("Equipo aprobado");
			resultVotos.setResultadoequipo(true);
			getBeliefbase().getBelief("ResultadoEquipo").setFact(true);

		//si hay mayor cantidad de votos en contra que a favor, no se aprueba el equipo
		}else {
			System.out.println("Equipo no aprobado");
			votacionesRechazadas = (int) getBeliefbase().getBelief("VotacionesRechazadas").getFact();
			++votacionesRechazadas;

			getBeliefbase().getBelief("VotacionesRechazadas").setFact(votacionesRechazadas);
			//si se llega a 5 equipos rechazados en una misma ronda, se termina la partida con victoria de espias y se informa a los jugadores
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
			//se tendra que escoger un nuevo lider si la votacion de equipo se rechaza
			resultVotos.setResultadoequipo(false);
			getBeliefbase().getBelief("LiderAsignado").setFact(false);
		}

		Votacion_publicada_equipo votacion = new Votacion_publicada_equipo();
		votacion.setResultado(resultVotos);
		//se informa del resultado de la votacion a los jugadores
		for (int i = 0; i < jugadores.getjugadores().size(); i++) {
			IMessageEvent informVotacion = createMessageEvent("Inform_Votacion_publicada_equipo");
			informVotacion.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
			informVotacion.setContent(votacion);
			sendMessage(informVotacion);			
		}	
		//la votacion queda concluida
		getBeliefbase().getBelief("ActivarAprobarEquipo").setFact(false);
		getBeliefbase().getBelief("VotacionEquipoRealizada").setFact(true);
		
	}
}