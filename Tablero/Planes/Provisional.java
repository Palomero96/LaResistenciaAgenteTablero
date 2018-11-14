
import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *  PLan para unir a un jugador a la partida
 */
public class RepartirTarjetasEquipoPlan extends Plan
{
	
	public void body()
	{
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		Lista_Jugadores jugadores = getBeliefbase().getBeliefSet("jugadores");	
		Jugador lider = new Jugador();

		//Buscamos a los jugadores espias y los vamos almacenando en una lista
		for(int i=0; i< jugadores.size(); i++){
			if(jugadores.get(i).getLider == true){
				lider = jugadores.get(i);
				break;
			}
		}

		int Ronda = getBeliefbase().getBelief("Ronda");
		IMessageEvent enviar = createMessageEvent("Agree_Pedir_tarjetas_equipo");
		switch(Ronda){
				case 1:
				int numequipo = getBeliefbase().getBelief("Equipo1");
				List<Tarjeta> lista = new List<Tarjeta>();
				for(int i=0; i<numequipo; i++){
					lista.add(new Tarjeta());
				}
				Pedir_tarjetas_equipo rj = new Pedir_tarjetas_equipo;
				rj.setListTarjeta_equipo;
				sendMessage(request.createReply("Agree_Pedir_tarjetas_equipo", rj));


		}

}	