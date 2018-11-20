package jugador.planes;


import java.util.Random;

import jadex.adapter.fipa.*;
import jadex.runtime.IGoal;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
//import jdk.nashorn.internal.runtime.Scope;
import ontologia.acciones.*;
import ontologia.conceptos.*;


/**
 *  PLan para unir a un jugador a la partida
 */
public class UnirsePartidaPlan extends Plan
{
	
	public void body()
	{		
		//System.out.println("Searching dealer...");
		AgentIdentifier newIdAgente = new AgentIdentifier();
		Jugador newJugador = new Jugador();
		newJugador.setIDAgente(newIdAgente);
		// Create a service description to search for.
		ServiceDescription sd = new ServiceDescription();
		sd.setName("tablero");
		sd.setType("agente");
		AgentDescription dfadesc = new AgentDescription();
		dfadesc.addService(sd);
		SearchConstraints	sc	= new SearchConstraints();
		sc.setMaxResults(-1);

		// Use a subgoal to search for a dealer-agent
		IGoal ft = createGoal("df_search");
		ft.getParameter("description").setValue(dfadesc);
		ft.getParameter("constraints").setValue(sc);
		dispatchSubgoalAndWait(ft);
		//AgentDescription[]	result	= (AgentDescription[])ft.getResult();
		AgentDescription[]	result	= (AgentDescription[])ft.getParameterSet("result").getValues();

		if(result==null || result.length==0)
		{
			System.out.println("No se ha encontrado tablero");
		}
		else
		{
			System.out.println ("Nuevo jugador pide unirse a partida...");
			AgentIdentifier tableroID = result[0].getName();						
			IMessageEvent requestUnirse = createMessageEvent("Request_unirsepartida");
			Unirse_a_la_partida unirsePartida = new Unirse_a_la_partida();
			unirsePartida.setjugador(newJugador);
			requestUnirse.setContent(unirsePartida);
			
			requestUnirse.getParameterSet(SFipa.RECEIVERS).addValue(tableroID);
			sendMessage(requestUnirse);
		}

	}

	}