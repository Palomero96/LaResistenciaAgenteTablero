package jugador.planes;


import java.util.*;

import jadex.adapter.fipa.*;
import jadex.runtime.IGoal;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
//import jdk.nashorn.internal.runtime.Scope;
import ontologia.acciones.*;
import ontologia.conceptos.*;
import ontologia.predicados.*;


/**
 *  PLan que guarda la lista de espias en las creencias del jugador
 */
public class InfoPartidaPlan extends Plan
{
	public void body()
	{	

		//Leer el mensaje
		IMessageEvent	inform	= (IMessageEvent)getInitialEvent();
		InformacionPartidaEnviada rj = (InformacionPartidaEnviada) inform.getContent();
		Lista_Jugadores jugadores = (Lista_Jugadores) rj.getLista_jugadores();
		getBeliefbase().getBelief("tablero").setFact((AgentIdentifier)inform.getParameter("sender").getValue());
		getBeliefbase().getBelief("jugadores").setFact(jugadores);
		System.out.println("Soy un jugador y guardo la informacion de quienes son los jugadores de la partida en la base de creencias.");
	}

}