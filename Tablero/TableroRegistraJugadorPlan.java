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

		// ¿que hace el getContent? puede no enviar nada el jugador?
		Unirse_a_la_Partida rj = (Unirse_a_la_Partida)request.getContent();
		Jugador player = rj.getJugador();
		player.setAgentID((AgentIdentifier)request.getParameter("sender").getValue());
		getLogger().info("New player "+player);
		if (getBeliefbase.getBeliefSet("jugadores").length < 10) {
			if(getBeliefbase().getBeliefSet("jugadores")==null){
				//Si el jugador es el primero que se une a la partida se le asigna como lider
				player.setLider(True)
			}
			if(!getBeliefbase().getBeliefSet("jugadores").containsFact(player))
			{
				getBeliefbase().getBeliefSet("jugadores").addFact(player);
			}
			player.setEspia(False);
			player.setLider(False);
		}
		//Creamos la respuesta para enviar al usuario/jugador
		Unirse_a_la_Partida rj = new Unirse_a_la_Partida(rj);
		sendMessage(request.createReply("agree_unirse_a_partida", rj));
	}
}