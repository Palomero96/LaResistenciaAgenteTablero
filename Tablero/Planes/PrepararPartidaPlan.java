/**Plan para asignar agentes resistencia y agentes espia**/

// Habra que asignar 4 roles de RESISTENCIA y 3 roles de ESPIA


package LaResistenciaAgenteTablero.Tablero

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *  PLan para unir a un jugador a la partida
 */
public class PrepararPartidaPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = getBeliefbase().getBeliefSet("jugadores");
		int[] aux = {1, 2, 3, 4, 5, 6, 7};
		shuffleArray(aux);
		IMessageEvent enviar = createMessageEvent("Inform_Tarjetas_personajes_repartidas");
		Tarjetas_personajes_repartidas rj = new Tarjetas_personajes_repartidas;
		for(int i=0; i<aux.size();i++){
			if (i<4){
				jugadores.get(aux[i]).setEspia=False;
				rj.setTarjetas_personajes_repartidas(False);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}
			jugadores.get(aux[i]).setEspia=True;
			rj.setTarjetas_personajes_repartidas(True);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(jugadores.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
		}
		getBeliefbase().getBelief("Preparada").setFact(True);
}