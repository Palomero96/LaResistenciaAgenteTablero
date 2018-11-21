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
 *  Plan para preparar la partida de 7 jugadores, asignando 4 agentes resistencia y 3 agentes espia
 */
public class PrepararPartidaPlan extends Plan {
	
	public void body()
	{
		System.out.println();
		System.out.println("Preparando Partida");
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> lista = (ArrayList)jugadores.getjugadores();
		//array auxiliar que servirá para darle un rol aleatorio a cada jugador
		int[] aux = {1, 2, 3, 4, 5, 6, 0};
		shuffleArray(aux);
		//Le enviaremos su rol espia/resistencia
		IMessageEvent enviar = createMessageEvent("Inform_Tarjetas_personajes_repartidas");
		//Le enviaremos las tarjetas para aprobar el equipo
		IMessageEvent aprobar = createMessageEvent("Inform_Tarjetas_aceptacion_repartidas");
		Tarjetas_personajes_repartidas rj = new Tarjetas_personajes_repartidas();
		
		for(int i=0; i< aux.length; i++){
			//a cada jugador, primero repartimos sus tarjetas de aceptacion
			Tarjetas_aceptacion_repartidas aceptacion = new Tarjetas_aceptacion_repartidas();
			aceptacion.setTarjeta(true);
			aprobar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
			aprobar.setContent(aceptacion);
			sendMessage(aprobar);
		//y luego si el numero aleatorio del jugador es menor que 4, su rol es de resistencia	
			if (i<4){
				lista.get(aux[i]).setEspia(false);
				rj.setTarjetas_personajes_repartidas(false);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}else{
		//y si el numero aleatorio del jugador es 4 o mayor, su rol es de espía
			lista.get(aux[i]).setEspia(true);
			rj.setTarjetas_personajes_repartidas(true);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}
		}	
		System.out.println("Se han repartido las tarjetas de personaje y las de aceptacion.");	
		//se crea una lista con los id de los jugadores para que todos los jugadores sepan que jugadores se encuentran en la partida
		Lista_Jugadores listaenviar = new Lista_Jugadores();
		ArrayList<Jugador> listaE = new ArrayList<Jugador>();
		//Lo importante de la lista son los ID, por lo que se establecen todos los roles a resistencia - no lideres
		for(int i = 0; i < lista.size(); i++){
			Jugador jugador = new Jugador();
			jugador.setEspia(false);
			jugador.setLider(false);
			jugador.setIDAgente(lista.get(i).getIDAgente());
			listaE.add(jugador);
		}
		//se envia la lista a cada jugador
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
		//se asigna rol de lider al primer jugador de la lista que habia sido ordenada aleatoriamente y se envia
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

	/*Metodo utilizado para ordenar aleatoriamente el array auxiliar y asi asignar roles aleatorios a los jugadores*/

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