package tablero.planes;

import java.util.*;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.acciones.*;
import ontologia.conceptos.*;
import ontologia.predicados.Lider_asignado;


/**
 *  PLan para unir a un jugador a la partida
 */
public class TableroRegistraJugadorPlan extends Plan
{
	
	public void body()
	{
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();

		
		Unirse_a_la_partida rj = (Unirse_a_la_partida) request.getContent();
		Jugador player = new Jugador();
		player.setIDAgente((AgentIdentifier) request.getParameter("sender").getValue());
		System.out.println("Nuevo Jugador creado:" + player);
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> lista = (ArrayList) jugadores.getjugadores();

		/*HAY QUE QUITAR LO DE ASIGNAR LIDER AL PRIMER JUGADOR*/
		if(jugadores.getjugadores()==null){
			//Si el jugador es el primero que se une a la partida se le asigna como lider
							lista = new ArrayList<Jugador>();
							player.setLider(false);
							//Le enviamos al lider el mensaje de que es lider
						/*	IMessageEvent enviar = createMessageEvent("Inform_Lider_asignado");
							Lider_asignado rj2 = new Lider_asignado();
							rj2.setLider(true);
							rj2.setEquipo((int) getBeliefbase().getBelief("Equipo1").getFact());
							enviar.getParameterSet(SFipa.RECEIVERS).addValue(player.getIDAgente());
							enviar.setContent(rj2);
							sendMessage(enviar);
							System.out.println("Voy a ser el lider");*/
							player.setEspia(false);
							lista.add(player);
							jugadores.setjugadores((List)lista);
							getBeliefbase().getBelief("jugadores").setFact(jugadores);
							//Creamos la respuesta para enviar al usuario/jugador
							Unirse_a_la_partida rj3 = new Unirse_a_la_partida();
							rj3.setjugador(player);
							sendMessage(request.createReply("Agree_unirse_a_partida", rj3));
							int total = (int) getBeliefbase().getBelief("total").getFact();
							total= total+1;
							getBeliefbase().getBelief("total").setFact(total);
							System.out.println("Jugador unido a la partida.");

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
						int total = (int) getBeliefbase().getBelief("total").getFact();
						total= total+1;
						getBeliefbase().getBelief("total").setFact(total);
						System.out.println("Jugador unido a la partida.");
				}else{
					System.out.println("La partida es para 7 jugadores.");
					sendMessage(request.createReply("Refuse_unirse_a_partida", rj));
				}
		}

		Lista_Jugadores jugadoresb = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> listaa = (ArrayList) jugadoresb.getjugadores();		
	}
}