package jugador.planes;

import java.util.List;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import ontologia.acciones.Unirse_a_la_partida;
import ontologia.acciones.Votar_mision;
import ontologia.conceptos.Jugador;
import ontologia.conceptos.Lista_Jugadores;
import ontologia.conceptos.Resultado;
import ontologia.conceptos.Voto;
import ontologia.predicados.Partida_Finalizada;
import ontologia.predicados.Tarjetas_exito_fracaso_repartidas;
import ontologia.predicados.Votacion_publicada_equipo;

/**	Acabado
 *  Plan para votar la mision (jugador)
 */
public class VotarMisionPlan extends Plan
{
	
	public void body()
	{		
		IMessageEvent request	= (IMessageEvent)getInitialEvent();

		boolean soyEspia = (boolean) getBeliefbase().getBelief("soy_Espia").getFact();
		
		if(!soyEspia) {
			Votar_mision votar_mision = new Votar_mision();
			Voto voto = new Voto();
			votar_mision.setVoto(voto);
			votar_mision.getvoto().setmision(true);
			IMessageEvent  reply = request.createReply("Agree_Votar_mision", votar_mision);		
			sendMessage(reply);
		}else {
			Votar_mision votar_mision = new Votar_mision();
			Voto voto = new Voto();
			votar_mision.setVoto(voto);
			votar_mision.getvoto().setmision(true);
			IMessageEvent  reply = request.createReply("Agree_Votar_mision", votar_mision);		
			sendMessage(reply);
		}
	}
}