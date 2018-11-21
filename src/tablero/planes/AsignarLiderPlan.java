package tablero.planes;

import java.util.ArrayList;
import java.util.List;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.conceptos.Jugador;
import ontologia.conceptos.Lista_Jugadores;
import ontologia.predicados.Lider_asignado;

/** Acabado
 *  Plan para asignar el lider
 */
public class AsignarLiderPlan extends Plan
{
	/* Se recorre la lista de jugadores para asignar el nuevo lider */
	public void body()
	{
		System.out.println("ESTOY ASIGNANDO NUEVO LIDER");
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBelief("jugadores").getFact();
		ArrayList<Jugador> listajugadores = (ArrayList<Jugador>) jugadores.getjugadores();
		AgentIdentifier liderId = new AgentIdentifier();
		for(int i = 0; i < listajugadores.size(); i++){
			listajugadores.get(i).getLider();
			if(listajugadores.get(i).getLider()==true){
				listajugadores.get(i).setLider(false);
				if(i==6){
					listajugadores.get(1).setLider(true);
					liderId = listajugadores.get(1).getIDAgente();
				}else{
					listajugadores.get(i+1).setLider(true);
					liderId = listajugadores.get(i+1).getIDAgente();
				}
			}
		}
		
		/* Una vez el lider es asignado se le informa de cuantas personas pueden componer el equipo a formar acorde a la ronda */
		Lider_asignado liderasignado = new Lider_asignado();
		liderasignado.setLider(true);
		int numjugadoresequipo = 0;
		int ronda = (int) getBeliefbase().getBelief("Ronda").getFact();
		switch(ronda){
			case 1:
					numjugadoresequipo = (int) getBeliefbase().getBelief("Equipo1").getFact();
					break;
			case 2:
					numjugadoresequipo = (int) getBeliefbase().getBelief("Equipo2").getFact();
					break;
			case 3:
					numjugadoresequipo = (int) getBeliefbase().getBelief("Equipo3").getFact();
					break;
			case 4:
					numjugadoresequipo = (int) getBeliefbase().getBelief("Equipo4").getFact();
					break;
			case 5:
					numjugadoresequipo = (int) getBeliefbase().getBelief("Equipo5").getFact();
					break;										

		}
		liderasignado.setEquipo(numjugadoresequipo);
		//Le enviamos al lider el mensaje de que es lider
		IMessageEvent enviar = createMessageEvent("Inform_Lider_asignado");
		enviar.setContent(liderasignado);
		enviar.getParameterSet(SFipa.RECEIVERS).addValue(liderId);
		System.out.println("Nuevo liderasignado:"+ liderId);
		sendMessage(enviar);
		
	/* comunicamos que el lider ha sido asignado */	getBeliefbase().getBelief("VotacionEquipoRealizada").setFact(false);	
		getBeliefbase().getBelief("VotacionMisionRealizada").setFact(false);
		getBeliefbase().getBelief("LiderAsignado").setFact(true);

	}
}