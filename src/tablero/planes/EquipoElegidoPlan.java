package tablero.planes;
import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.predicados.Equipo_elegido;

/**	
 *  Plan para conocer el posible equipo que ira a la mision
 */
public class EquipoElegidoPlan extends Plan
{
	
	public void body()
	{	
		IMessageEvent inform = (IMessageEvent)getInitialEvent();
		Equipo_elegido rj = (Equipo_elegido) inform.getContent();

	/* Se informa del equipo planteado para la mision */

	getBeliefbase().getBelief("Equipo").setFact(rj.getLista_jugadores());
	
	/* Se informa de que ya se puede votar para ver si el equipo se aprueba o no */
	getBeliefbase().getBelief("ActivarAprobarEquipo").setFact(true);
	}
}