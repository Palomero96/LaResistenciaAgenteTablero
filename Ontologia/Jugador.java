import Resistencia.Concepto;
import jadex.runtime.*;

public class Jugador implements Concepto {
 private AgentIdentifier IDAgente;
 private boolean Espia; //Valor default false
 private boolean Lider; //valor default false
	public Jugador()
	{ ; }
       public AgentIdentifier getIDAgente() {
           return IDAgente;
       }
       public void setIDAgente(AgentIdentifier a) {
           IDAgente=a;
       }
       public boolean getEspia() {
           return Espia;
       }
       public void setEspia(boolean a) {
           Espia=a;
       }
        public boolean getLider() {
           return Lider;
       }
       public void setLider(boolean a) {
           Lider=a;
       }

}
