package LaResistenciaAgenteTablero.Tablero

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**	Acabado
 *  Plan para votar la mision
 */
public class VotarMisionPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBeliefset("jugadores");
		Lista_Jugadores equipo = (Lista_Jugadores) getBeliefbase().getBeliefset("Equipo");
		List<Jugador> listajugadores = jugadores.getjugadores();
		List<Jugador> listaequipo = equipo.getjugadores();
		// Reset de votos de equipo en las creencias
		getBeliefbase().getBeliefSet("votosmision").removeFacts();
				
		int fracasos = 0;
		int exitos=0;
		for (int i = 0; i < listaequipo.size(); i++ ) {
			Tarjertas_exito_fracaso_repartidas tarjeta = new Tarjertas_exito_fracaso_repartidas();
			tarjeta.setTarjetas(true);
			IMessageEvent sendTarjeta = createMessageEvent("Inform_Tarjetas_exito_fracaso_repartidas");
			sendTarjeta.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			sendTarjeta.setContent(tarjeta);
			sendMessage(sendTarjeta);

			//Le enviamos las tarjetas para aprobar el equipo
			IMessageEvent request = createMessageEvent("Request_Votar_mision");
			request.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			Votar_mision votar_mision = new Votar_mision();
			sendMessageAndWait(request, 10000);
			
			// Preguntar por la respuesta
			votar_mision = (Votar_mision) request.getContent();
			getBeliefbase().getBeliefSet("votosmision").addFact(votar_mision.getvoto());
		
			if (!votar_mision.getvoto().getequipo()) {
				++fracasos;
			}else{
				++exitos;
			}
		}
		
		Resultado resultVotos = new Resultado();
		
		if (fracasos>0) {
			System.out.println("Mision Fracasada");
			resultVotos.setResultadomision(false);
		}else {
			System.out.println("Mision completada con exito");
			resultVotos.setResultadomision(true);
			
			Int contador = (int) getBeliefbase().getBelief("MisionesCompletadas");
			contador = contador+1;
			getBeliefbase().getBelief("MisionesCompletadas").setFact(contador);		
		}
		
		Votacion_publicada_equipo votacion = new Votacion_publicada_equipo();
		votacion.setResultado(resultVotos);
		
		for (int i = 0; i < listajugadores.size(); i++) {
			IMessageEvent informVotacion = createMessageEvent("Inform_Votacion_publicada_mision");
			informVotacion.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			informVotacion.setContent(votacion);
			sendMessage(informVotacion);
	}
			getBeliefbase().getBelief("VotacionMisionRealizada").setFact(true);	
			Int ronda = (int) getBeliefbase().getBelief("Ronda");
			ronda = ronda+1;
			getBeliefbase().getBelief("Ronda").setFact(ronda);
}
}