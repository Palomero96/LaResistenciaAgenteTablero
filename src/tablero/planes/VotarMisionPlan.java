package tablero.planes;

import java.util.List;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.acciones.Votar_mision;
import ontologia.conceptos.Jugador;
import ontologia.conceptos.Lista_Jugadores;
import ontologia.conceptos.Resultado;
import ontologia.predicados.Partida_Finalizada;
import ontologia.predicados.Tarjetas_exito_fracaso_repartidas;
import ontologia.predicados.Votacion_publicada_mision;

/**	Acabado
 *  Plan para votar la mision
 */
public class VotarMisionPlan extends Plan
{
	
	public void body()
	{
		System.out.println("Votar_mision lanzado");
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		Lista_Jugadores equipo = (Lista_Jugadores) getBeliefbase().getBelief("Equipo").getFact();
		List<Jugador> listajugadores = jugadores.getjugadores();
		List<Jugador> listaequipo = equipo.getjugadores();
		// Reset de votos de equipo en las creencias
		getBeliefbase().getBeliefSet("votosmision").removeFacts();
				
		int fracasos = 0;
		int exitos = 0;
		for (int i = 0; i < listaequipo.size(); i++ ) {
			Tarjetas_exito_fracaso_repartidas tarjeta = new Tarjetas_exito_fracaso_repartidas();
			tarjeta.setTarjetas(true);
			IMessageEvent sendTarjeta = createMessageEvent("Inform_Tarjetas_exito_fracaso_repartidas");
			sendTarjeta.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			sendTarjeta.setContent(tarjeta);
			System.out.println(tarjeta);
			sendMessage(sendTarjeta);

			//Le enviamos las tarjetas para aprobar el equipo
			IMessageEvent request = createMessageEvent("Request_Votar_mision");
			request.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			Votar_mision votar_mision = new Votar_mision();
			request.setContent(votar_mision);
			IMessageEvent reply = sendMessageAndWait(request, 10000);

			
			// Preguntar por la respuesta
			votar_mision = (Votar_mision) reply.getContent();
			System.out.println("El jugador ha votado " + votar_mision.getvoto().getmision());

			
			if (votar_mision.getvoto().getmision()==false) {
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
			
			int contador = (int) getBeliefbase().getBelief("MisionesCompletadas").getFact();
			contador = contador+1;
			getBeliefbase().getBelief("MisionesCompletadas").setFact(contador);	
		}
		 int NumRonda = (int) getBeliefbase().getBelief("Ronda").getFact();
		 Partida_Finalizada finPartida = new Partida_Finalizada();

		Votacion_publicada_mision votacion = new Votacion_publicada_mision();
		votacion.setResultado(resultVotos);
		votacion.setFracasos(fracasos);
		votacion.setExitos(exitos);

		
		for (int i = 0; i < listajugadores.size(); i++) {
			IMessageEvent informVotacion = createMessageEvent("Inform_Votacion_publicada_mision");
			informVotacion.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
			informVotacion.setContent(votacion);
			sendMessage(informVotacion);
		}


		int ronda = (int) getBeliefbase().getBelief("Ronda").getFact();
		
		ronda = ronda+1;
		if(ronda>5){
			getBeliefbase().getBelief("FinPartida").setFact(true);
		}
			
		System.out.println("FIN VOTAR MISION");
		if((int) getBeliefbase().getBelief("MisionesCompletadas").getFact()==3){
			finPartida.setGanaResistencia(true);
			for (int i = 0; i < listajugadores.size(); i++) {
				IMessageEvent informFinPartida = createMessageEvent("Inform_Partida_Finalizada");
				informFinPartida.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
				informFinPartida.setContent(finPartida);
				sendMessage(informFinPartida);
			}
			System.out.println("GANAN LA RESISTENCIA");
			getBeliefbase().getBelief("FinPartida").setFact(true);

		}
		if((NumRonda - ((int) getBeliefbase().getBelief("MisionesCompletadas").getFact()))==3){
			finPartida.setGanaResistencia(false);
			for (int i = 0; i < listajugadores.size(); i++) {
				IMessageEvent informFinPartida = createMessageEvent("Inform_Partida_Finalizada");
				informFinPartida.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
				informFinPartida.setContent(finPartida);
				sendMessage(informFinPartida);
			}
			System.out.println("GANAN LOS ESPIAS");
			getBeliefbase().getBelief("FinPartida").setFact(true);
		}
		getBeliefbase().getBelief("Ronda").setFact(ronda);
		getBeliefbase().getBelief("LiderAsignado").setFact(false);
		getBeliefbase().getBelief("VotacionesRechazadas").setFact(0);
		getBeliefbase().getBelief("ResultadoEquipo").setFact(false);
		getBeliefbase().getBelief("VotacionMisionRealizada").setFact(true);

	}
}