/** Acabado
Plan para asignar agentes resistencia y agentes espia**/

// Habra que asignar 4 roles de RESISTENCIA y 3 roles de ESPIA


package tablero.planes;

import java.util.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.conceptos.*;
import ontologia.predicados.*;

/**
 *  
 */
public class PrepararPartidaPlan extends Plan {
	
	public void body()
	{
		System.out.println();
		System.out.println("Preparando Partida");
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> lista = (ArrayList)jugadores.getjugadores();
		int[] aux = {1, 2, 3, 4, 5, 6, 0};
		shuffleArray(aux);
		//Le enviamos su rol espia/resistencia
		IMessageEvent enviar = createMessageEvent("Inform_Tarjetas_personajes_repartidas");
		//Le enviamos las tarjetas para aprobar el equipo
		IMessageEvent aprobar = createMessageEvent("Inform_Tarjetas_aceptacion_repartidas");
		Tarjetas_personajes_repartidas rj = new Tarjetas_personajes_repartidas();
		
		for(int i=0; i< aux.length; i++){
			Tarjetas_aceptacion_repartidas aceptacion = new Tarjetas_aceptacion_repartidas();
			aceptacion.setTarjeta(true);
			aprobar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
			aprobar.setContent(aceptacion);
			sendMessage(aprobar);
			if (i<4){
				lista.get(aux[i]).setEspia(false);
				rj.setTarjetas_personajes_repartidas(false);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}else{
			lista.get(aux[i]).setEspia(true);
			rj.setTarjetas_personajes_repartidas(true);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}
		}	
		System.out.println("Se han repartido las tarjetas de personaje y las de aceptacion.");	
		Lista_Jugadores listaenviar = new Lista_Jugadores();
		ArrayList<Jugador> listaE = new ArrayList<Jugador>();
		for(int i = 0; i < lista.size(); i++){
			Jugador jugador = new Jugador();
			jugador.setEspia(false);
			jugador.setLider(false);
			jugador.setIDAgente(lista.get(i).getIDAgente());
			listaE.add(jugador);
		}
		listaenviar.setjugadores((List) listaE);
		InformacionPartidaEnviada info = new InformacionPartidaEnviada();
		info.setLista_jugadores(listaenviar);
		IMessageEvent enviarinfo = createMessageEvent("Inform_ListaJugadores");
		enviarinfo.setContent(info);
		
		for(int i = 0; i < lista.size(); i++){
			enviarinfo.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(i).getIDAgente());
		}
		sendMessage(enviarinfo);
		System.out.println("Informacion de la partida enviada.");
		getBeliefbase().getBelief("Preparada").setFact(true);

		IMessageEvent liderenviar = createMessageEvent("Inform_Lider_asignado");
		Lider_asignado rj2 = new Lider_asignado();
		rj2.setLider(true);
		rj2.setEquipo((int) getBeliefbase().getBelief("Equipo1").getFact());
		Jugador player = lista.get(0);
		player.setLider(true);
		System.out.println("Nuevo Lider asignado:"+ player.getIDAgente());
		liderenviar.getParameterSet(SFipa.RECEIVERS).addValue(player.getIDAgente());
		liderenviar.setContent(rj2);
		sendMessage(liderenviar);
	}


	  // Implementing Fisher–Yates shuffle
	  static void shuffleArray(int[] aux)
	  {
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = aux.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = aux[index];
	      aux[index] = aux[i];
	      aux[i] = a;
	    }
	  }
}