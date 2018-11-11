package LaResistenciaAgenteTablero.Tablero

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *  PLan para unir a un jugador a la partida
 */
public class TableroRegistraJugadorPlan extends Plan
{
	
	public void body()
	{
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();

		
		Unirse_a_la_Partida rj = (Unirse_a_la_Partida)request.getContent();
		Jugador player = rj.getJugador();
		player.setAgentID((AgentIdentifier)request.getParameter("sender").getValue());
		getLogger().info("New player "+player);
		Lista_Jugadores jugadores = getBeliefbase().getBeliefSet("jugadores");
		if (jugadores.size() < 8) {
			player.setLider(False);
			if(jugadores.isEmpty()){
				//Si el jugador es el primero que se une a la partida se le asigna como lider
				player.setLider(True)
				//Le enviamos al lider el mensaje de que es lider
				IMessageEvent enviar = createMessageEvent("Inform_Tarjetas_personajes_repartidas");
				Lider_asignado rj = new Lider_asignado;
				rj.setLider(True);
				rj.setEquipo(getBeliefbase().getBelief("Equipo1"))
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(player.getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}
			player.setEspia(False);
			jugadores.add(player);
			getBeliefbase().getBeliefSet("jugadores").setFact(jugadores);
		//Creamos la respuesta para enviar al usuario/jugador
		Unirse_a_la_Partida rj = new Unirse_a_la_Partida(rj);
		sendMessage(request.createReply("agree_unirse_a_partida", rj));
		System.out.println("hay " + getBeliefbase().getBeliefSet("jugadores").length + " jugadores");
	}
	Unirse_a_la_Partida rj = new Unirse_a_la_Partida(rj);
	sendMessage(request.createReply("refuse_unirse_a_partida", rj));
}