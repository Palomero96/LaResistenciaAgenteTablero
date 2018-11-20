package jugador.planes;


import java.util.*;

import jadex.adapter.fipa.*;
import jadex.runtime.IGoal;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
//import jdk.nashorn.internal.runtime.Scope;
import ontologia.acciones.*;
import ontologia.conceptos.*;
import ontologia.predicados.*;


/**
 *  PLan que guarda la lista de espias en las creencias del jugador
 */
public class AprobarEquipoPlan extends Plan
{
	public void body()
	{	
		//Leer el mensaje
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		Aprobar_equipo rj = (Aprobar_equipo) request.getContent();
		Voto voto= new Voto();
		voto.setequipo(true);
		rj.setVoto(voto);
		sendMessage(request.createReply("Agree_Aprobar_equipo", rj));
		System.out.println(" He votado afirmativo");
	}
}