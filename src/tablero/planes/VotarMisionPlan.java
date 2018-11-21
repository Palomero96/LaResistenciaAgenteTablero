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
import ontologia.predicados.Votacion_publicada_equipo;

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
			System.out.println(tarjeta.getTarjetas());
			sendMessage(sendTarjeta);

			//Le enviamos las tarjetas para aprobar el equipo
			System.out.println("ANTES DE CREAR REQUEST");
			IMessageEvent request = createMessageEvent("Request_Votar_mision");
			request.getParameterSet(SFipa.RECEIVERS).addValue(equipo.getjugadores().get(i).getIDAgente());
			Votar_mision votar_mision = new Votar_mision();
			request.setContent(votar_mision);
			System.out.println("ANTES DE MANDAR REQUEST");
			IMessageEvent reply = sendMessageAndWait(request, 10000);
			System.out.println("DESPUES DE RECIBIR REQUEST");

			
			// Preguntar por la respuesta
			votar_mision = (Votar_mision) reply.getContent();
			getBeliefbase().getBeliefSet("votosmision").addFact(votar_mision.getvoto());
		
			if (!votar_mision.getvoto().getmision()) {
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
		
		if((int) getBeliefbase().getBelief("MisionesCompletadas").getFact()==3){
			finPartida.setGanaResistencia(true);
			for (int i = 0; i < listajugadores.size(); i++) {
				IMessageEvent informFinPartida = createMessageEvent("Inform_Partida_Finalizada");
				informFinPartida.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
				informFinPartida.setContent(finPartida);
				sendMessage(informFinPartida);
			}
			System.out.println("GANAN LA RESISTENCIA");
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
		}

		Votacion_publicada_equipo votacion = new Votacion_publicada_equipo();
		votacion.setResultado(resultVotos);
		
		for (int i = 0; i < listajugadores.size(); i++) {
			IMessageEvent informVotacion = createMessageEvent("Inform_Votacion_publicada_mision");
			informVotacion.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.getjugadores().get(i).getIDAgente());
			informVotacion.setContent(votacion);
			sendMessage(informVotacion);
		}


		int ronda = (int) getBeliefbase().getBelief("Ronda").getFact();
		
		ronda = ronda+1;
		getBeliefbase().getBelief("Ronda").setFact(ronda);
		getBeliefbase().getBelief("LiderAsignado").setFact(false);
		getBeliefbase().getBelief("VotacionesRechazadas").setFact(0);
		// Para cumplir target condition
		getBeliefbase().getBelief("ResultadoEquipo").setFact(false);
		System.out.println("FIN VOTAR MISION");
		getBeliefbase().getBelief("VotacionMisionRealizada").setFact(true);
		

		System.out.println("DESPUES DE ACABAR ENTERO EL PLAN VOTAR MISION");
	}
}