package tablero.planes;

import jadex.adapter.fipa.*;
import java.util.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.acciones.*;
import ontologia.conceptos.*;

/** 
 *  Plan para avisar a los espias de quien son los otos espias 
 */
public class AvisarEspiasPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> listajugadores = (ArrayList) jugadores.getjugadores();	
		Lista_Jugadores espias = new Lista_Jugadores();
		ArrayList<Jugador> listaespias = new ArrayList<Jugador>();
		//Buscamos a los jugadores espias y los vamos almacenando en una lista
		for(int i = 0; i < listajugadores.size(); i++){
			if(listajugadores.get(i).getEspia() == true){
				listaespias.add(listajugadores.get(i));
			}
		}
		IMessageEvent enviar = createMessageEvent("Inform_Avisar_espias");
		Avisar_espias rj = new Avisar_espias();

		//se envia a cada espia una lista de todos los jugadores espias
		for(int i=0; i< listaespias.size(); i++){
			enviar.getParameterSet(SFipa.RECEIVERS).addValue(listaespias.get(i).getIDAgente());
		}
		espias.setjugadores((List)listaespias);
		rj.setLista_jugadores(espias);
		enviar.setContent(rj);
		System.out.println("Los espias se conocen entre ellos.");
		sendMessage(enviar);
	}

}	
