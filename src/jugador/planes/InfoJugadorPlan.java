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
public class InfoJugadorPlan extends Plan
{
	public void body()
	{	
		//Leer el mensaje
		IMessageEvent	inform	= (IMessageEvent)getInitialEvent();
		Unirse_a_la_partida rj = (Unirse_a_la_partida) inform.getContent();
		Jugador jugador = (Jugador) rj.getjugador();
		getBeliefbase().getBelief("jugador").setFact(jugador);
		System.out.println("Soy un jugador y guardo mi informacion a la base de creencias.");
	}

}