package ontologia.conceptos;
import jadex.adapter.fipa.AgentIdentifier;

public class Voto extends Concepto {

 private boolean equipo;
 private AgentIdentifier IDAgente;
 private boolean mision;
 
	public Voto()
	{ ; }
       public boolean getequipo() {
           return equipo;
       }
       public void setequipo(boolean a) {
           equipo=a;
       } 
       public boolean getmision() {
           return mision;
       }
       public void setmision(boolean a) {
           mision=a;
       }   
         public AgentIdentifier getIDAgente() {
           return IDAgente;
       }
       public void setIDAgente(AgentIdentifier a) {
           IDAgente=a;
       }

}