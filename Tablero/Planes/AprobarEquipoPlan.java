package tablero.planes;

import java.util.List;

import jadex.adapter.fipa.*;
import jadex.bdi.runtime.IBelief;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.conceptos.*;
import ontologia.predicados.*;
import ontologia.acciones.*;

/**
 *  Plan para pedir un equipo al lider y
 */
public class AprobarEquipoPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores");
		Lista_Jugadores equipo = (Lista_Jugadores) getBeliefbase().getBelief("Equipo");
		
		// Reset de votos de equipo en las creencias
		getBeliefbase().getBeliefSet("votosequipo").removeFacts();
				
		int votosContra = 0;
		
		for (int i = 0; i < equipo.getjugadores().size(); i++ ) {
			IMessageEvent request = createMessageEvent("Request_Aprobar_equipo");
			Aprobar_equipo accionAprobarEquipo = new Aprobar_equipo();
			request.setContent(accionAprobarEquipo);
			request.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			sendMessageAndWait(request, 10000);
			
			// TODO: Revisar como obtener el objeto de una supuesta Reply
			// o confirmar que ha llegado dicha Reply
			accionAprobarEquipo = (Aprobar_equipo) request.getContent();
			getBeliefbase().getBeliefSet("votosequipo").addFact(accionAprobarEquipo.getvoto());
		
			if (!accionAprobarEquipo.getvoto().getequipo()) {
				++votosContra;
			}
		}
		
		Resultado resultVotos = new Resultado();
		
		if (votosContra < 4) {
			System.out.println("Equipo aprobado");
			
			resultVotos.setResultadoequipo(true);
			getBeliefbase().getBelief("ResultadoEquipo").setFact(true);

		}else {
			System.out.println("Equipo no aprobado");
			int votacionesRechazadas = (int) getBeliefbase().getBelief("VotacionesRechazadas").getFact();
			++votacionesRechazadas;
			/*si se rechazan 5 votaciones de equipo ganan los espias. Habrá que crear protocolos de partida ganada/perdida
			if{
				votacionesRechazadas==5;
				//sendMessage(partida_perdida);
			}*/

			getBeliefbase().getBelief("VotacionesRechazadas").setFact(votacionesRechazadas);

			resultVotos.setResultadoequipo(false);	

			getBeliefbase().getBelief("VotacionEquipoRealizada").setFact(true);		
		}
		
		Votacion_publicada_equipo votacion = new Votacion_publicada_equipo();
		votacion.setResultado(resultVotos);
		
		for (int i = 0; i < jugadores.getjugadores().size(); i++) {
			IMessageEvent informVotacion = createMessageEvent("Inform_Votacion_publicada_equipo");
			informVotacion.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			informVotacion.setContent(votacion);
			sendMessage(informVotacion);			
		}		
	}
}