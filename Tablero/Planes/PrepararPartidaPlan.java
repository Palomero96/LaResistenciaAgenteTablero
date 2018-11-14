/** Acabado
Plan para asignar agentes resistencia y agentes espia**/

// Habra que asignar 4 roles de RESISTENCIA y 3 roles de ESPIA


package LaResistenciaAgenteTablero.Tablero

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *  
 */
public class PrepararPartidaPlan extends Plan
{
	
	public void body()
	{
		Lista_Jugadores jugadores = getBeliefbase().getBeliefSet("jugadores");
		List<Jugador> lista = jugadores.getjugadores();
		int[] aux = {1, 2, 3, 4, 5, 6, 7};
		shuffleArray(aux);
		//Le enviamos su rol espia/resistencia
		IMessageEvent enviar = createMessageEvent("Inform_Tarjetas_personajes_repartidas");
		//Le enviamos las tarjetas para aprobar el equipo
		IMessageEvent aprobar = createMessageEvent("Inform_Tarjetas_aceptacion_repartidas");
		Tarjetas_personajes_repartidas rj = new Tarjetas_personajes_repartidas;
		for(int i=0; i<aux.size();i++){
			Tarjetas_aceptacion_repartidas aceptacion = new Tarjetas_aceptacion_repartidas;
			aceptacion.setTarjeta(true);
			aprobar.setContent(aceptacion);
			sendMessage(aprobar);
			if (i<4){
				lista.get(aux[i]).setEspia=false;
				rj.setTarjetas_personajes_repartidas(false);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}else{
			lista.get(aux[i]).setEspia=true;
			rj.setTarjetas_personajes_repartidas(true);
				enviar.getParameterSet(SFipa.RECEIVERS).addValue(lista.get(aux[i]).getIDAgente());
				enviar.setContent(rj);
				sendMessage(enviar);
			}
		}
		getBeliefbase().getBelief("Preparada").setFact(true);
}