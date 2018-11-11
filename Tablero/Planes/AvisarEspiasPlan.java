
import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *  PLan para unir a un jugador a la partida
 */
public class AvisarEspiasPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = getBeliefbase().getBeliefSet("jugadores");	
		Lista_Jugadores espias = new Lista_Jugadores();
		//Buscamos a los jugadores espias y los vamos almacenando en una lista
		for(int i=0; i< jugadores.size(); i++){
			if(jugadores.get(i).getEspia == True){
				espias.add(jugadores.get(i));
			}
		}
		IMessageEvent enviar = createMessageEvent("Inform_Avisar_espias");
		Avisar_espias rj = new Avisar_espias;

		for(int i=0; i< espias.size(); i++){
			//Preguntar si esta bien hecho esto
			enviar.getParameterSet(SFipa.RECEIVERS).addValue(espias.get(i).getIDAgente());
		}
		rj.setLista_jugadores(espias);
		enviar.setContent(rj);
		sendMessage(enviar);
	}

}	
