package jugador.planes;
import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.predicados.Equipo_elegido;

/**	Acabado
 *  Plan para conocer el posible equipo que ira a la mision
 */
public class ComprobarPlan extends Plan
{
	
	public void body()
	{	
		IMessageEvent inform = (IMessageEvent)getInitialEvent();
		System.out.println("HE RECIBIDO EL MENSAJE DE LA INFO DE VOTACION");
	}
}