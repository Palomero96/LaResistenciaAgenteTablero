package tablero.planes;
import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.predicados.Equipo_elegido;

/**	Acabado
 *  Plan para conocer el posible equipo que ira a la mision
 */
public class EquipoElegidoPlan extends Plan
{
	
	public void body()
	{	
		IMessageEvent inform = (IMessageEvent)getInitialEvent();
		Equipo_elegido rj = (Equipo_elegido) inform.getContent();
		getBeliefbase().getBelief("Equipo").setFact(rj.getLista_jugadores());
	}
}