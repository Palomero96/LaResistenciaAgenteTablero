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
public class AvisadoEspiasPlan extends Plan
{
	public void body()
	{	
		//Leer el mensaje
		IMessageEvent	inform	= (IMessageEvent)getInitialEvent();

		Avisar_espias rj = (Avisar_espias) inform.getContent();
		//Comprobamos si la lista de jugadores está vacía. 
		if(!rj.getLista_jugadores().getjugadores().isEmpty())
		{
			//Agregamos a las creencias
			getBeliefbase().getBelief("espias").setFact(rj.getLista_jugadores());
			Jugador jugador = (Jugador) getBeliefbase().getBelief("jugador").getFact();
			jugador.setEspia(true);
			getBeliefbase().getBelief("jugador").setFact(jugador);
			getBeliefbase().getBelief("soy_Espia").setFact(true);
		}
	}

}