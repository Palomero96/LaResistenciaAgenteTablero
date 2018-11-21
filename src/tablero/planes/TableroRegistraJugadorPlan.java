package tablero.planes;

import java.util.*;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.acciones.*;
import ontologia.conceptos.*;
import ontologia.predicados.Lider_asignado;


/**
 *  Plan para unir a un jugador a la partida
 */
public class TableroRegistraJugadorPlan extends Plan
{
	
	public void body()
	{
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();

		//se crea al nuevo jugador que se va a unir
		Unirse_a_la_partida rj = (Unirse_a_la_partida) request.getContent();
		Jugador player = new Jugador();
		player.setIDAgente((AgentIdentifier) request.getParameter("sender").getValue());
		System.out.println("Nuevo Jugador creado:" + player);
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> lista = (ArrayList) jugadores.getjugadores();

		//En caso de que sea el primer jugador en unirse a la partida se crea la lista de jugadores
		if(jugadores.getjugadores()==null){
			
							lista = new ArrayList<Jugador>();
							player.setLider(false);
							player.setEspia(false);
							lista.add(player);
							jugadores.setjugadores((List)lista);
							getBeliefbase().getBelief("jugadores").setFact(jugadores);
							//Creamos la respuesta para enviar al usuario/jugador
							Unirse_a_la_partida rj3 = new Unirse_a_la_partida();
							rj3.setjugador(player);
							sendMessage(request.createReply("Agree_unirse_a_partida", rj3));

/* Se coge el numero de jugadores registrado y se aumenta en 1 unidad */
							int total = (int) getBeliefbase().getBelief("total").getFact();
							total= total+1;
							getBeliefbase().getBelief("total").setFact(total);
							System.out.println("Jugador unido a la partida.");
		//en caso de que no sea el primer jugador en unirse a la partida
		}else{
				if (lista.size() < 7) {
						player.setLider(false);
						player.setEspia(false);
						lista.add(player);
						jugadores.setjugadores((List)lista);
						getBeliefbase().getBelief("jugadores").setFact(jugadores);
						//Creamos la respuesta para enviar al usuario/jugador
						Unirse_a_la_partida rj3 = new Unirse_a_la_partida();
						rj3.setjugador(player);
						sendMessage(request.createReply("Agree_unirse_a_partida", rj3));

/* Se coge el numero de jugadores registrado y se aumenta en 1 unidad */
						int total = (int) getBeliefbase().getBelief("total").getFact();
						total= total+1;
						getBeliefbase().getBelief("total").setFact(total);
						System.out.println("Jugador unido a la partida.");
				}else{
/* Se ha alcanzado el numero maximo de jugadores, asi que se rechaza al nuevo jugador */
					System.out.println("La partida es para 7 jugadores.");
					sendMessage(request.createReply("Refuse_unirse_a_partida", rj));
				}
		}

/* Se informa de la lista de jugadores */
		Lista_Jugadores jugadoresb = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> listaa = (ArrayList) jugadoresb.getjugadores();		
	}
}