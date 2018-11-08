package Tablero

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
		if (getBeliefbase.getBeliefSet("jugadores").length < 8) {
			player.setLider(False);
			if(getBeliefbase().getBeliefSet("jugadores")==null){
				//Si el jugador es el primero que se une a la partida se le asigna como lider
				player.setLider(True)
			}
			player.setEspia(False);
			
			if(!getBeliefbase().getBeliefSet("jugadores").containsFact(player))
			{
				getBeliefbase().getBeliefSet("jugadores").addFact(player);
			}
			
		//Creamos la respuesta para enviar al usuario/jugador
		Unirse_a_la_Partida rj = new Unirse_a_la_Partida(rj);
		sendMessage(request.createReply("agree_unirse_a_partida", rj));
		System.out.println("hay " + getBeliefbase().getBeliefSet("jugadores").length + " jugadores");
	}
	Unirse_a_la_Partida rj = new Unirse_a_la_Partida(rj);
	sendMessage(request.createReply("refuse_unirse_a_partida", rj));
}