import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *  PLan para unir a un jugador a la partida
 */
public class EquipoElegidoPlan extends Plan
{
	
	public void body()
	{	
		IMessageEvent inform	= (IMessageEvent)getInitialEvent();
		Equipo_elegido rj = (Equipo_elegido)inform.getContent();
		getBeliefbase().getBeliefSet("Equipo").setFact(rj.getLista_jugadores());
}	