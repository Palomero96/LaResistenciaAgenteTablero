package LaResistenciaAgenteTablero.Tablero

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/** Acabado
 *  Plan para asignar el lider
 */
public class AsignarLiderPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = (Lista_Jugadores) getBeliefbase().getBeliefset("jugadores");
		List<Jugador> listajugadores = jugadores.getjugadores();
		AgentIdentifier liderId = new AgentIdentifier();
		for(int i=0; i<listajugadores; i++){
			listajugadores.get(i).getLider();
			if(listajugadores.get(i).getLider()==true){
				listajugadores.get(i).setLider(false);
				if(i==6){
					listajugadores.get(1).setLider(true);
					lider = listajugadores.get(1).getIDAgente();
				}else{
					listajugadores.get(i+1).setLider(true);
					lider = listajugadores.get(i+1).getIDAgente();
				}
			}
		}
		
		Lider_asignado liderasginado = new Lider_asignado();
		liderasignado.setLider(true);
		int numjugadoresequipo;
		int ronda = getBeliefbase().getBelief("Ronda")
		switch(ronda){
			case 1:
					numjugadoresequipo=getBeliefbase().getBelief("Equipo1");
					break;
			case 2:
					numjugadoresequipo=getBeliefbase().getBelief("Equipo2");
					break;
			case 3:
					numjugadoresequipo=getBeliefbase().getBelief("Equipo3");
					break;
			case 4:
					numjugadoresequipo=getBeliefbase().getBelief("Equipo4");
					break;
			case 5:
					numjugadoresequipo=getBeliefbase().getBelief("Equipo5");
					break;										

		}
		liderasignado.setEquipo(numjugadoresequipo);
		//Le enviamos al lider el mensaje de que es lider
		IMessageEvent enviar = createMessageEvent("Inform_Lider_asignado");
		enviar.setContent(liderasignado);
		enviar.getParameterSet(SFipa.RECEIVERS).addValue(lider);
}
}