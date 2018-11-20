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
public class ElegirEquipoPlan extends Plan
{
	public void body()
	{	

		//Leer el mensaje
		IMessageEvent	inform	= (IMessageEvent)getInitialEvent();
		Lider_asignado rj = (Lider_asignado) inform.getContent();
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> lista = (ArrayList) jugadores.getjugadores();
		Equipo_elegido equipoelegido = new Equipo_elegido();
		Lista_Jugadores jugadoresequipo = new Lista_Jugadores();
		ArrayList<Jugador> listaequipo = new ArrayList<Jugador>();
		int equipo = (int) rj.getEquipo();
		IMessageEvent enviar = createMessageEvent("Inform_Equipo_elegido");
		for(int i=0; i<equipo; i++){
			listaequipo.add(lista.get(i));
		}
		jugadoresequipo.setjugadores((List)listaequipo);
		equipoelegido.setLista_jugadores(jugadoresequipo);
		enviar.setContent(equipoelegido);
		for(int j=0; j<lista.size(); j++){
			enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(j).getIDAgente());
		}
		//Al tablero tambien le enviamos el mensaje
		enviar.getParameterSet(SFipa.RECEIVERS).addValue((AgentIdentifier)getBeliefbase().getBelief("tablero").getFact());
		sendMessage(enviar);
		System.out.println("Equipo elegido por el lider.");
	}

}