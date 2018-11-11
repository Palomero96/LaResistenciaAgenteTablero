import Resistencia.Concepto;
import jadex.runtime.*;

public class Tarjeta implements Concepto {
 private int id_tarjeta;
 private AgentIdentifier IDAgente; //Podria omitirse porque podria ser la relacion entre jugador y tarjeta
 private boolean armas; //Para elegir equipo
 private boolean votacion_equipo;
 private boolean votacion_mision;
 
	public Tarjeta()
	{ ; }
       public int getid_tarjeta() {
           return id_tarjeta;
       }
       public void setid_tarjeta(int a) {
           id_tarjeta=a;
       }
      public AgentIdentifier getIDAgente() {
           return IDAgente;
       }
       public void setIDAgente(AgentIdentifier a) {
           IDAgente=a;
       }
      public boolean getarmas() {
           return armas;
       }
       public void setarmas(boolean a) {
           armas=a;
       }
       public boolean getvotacion_equipo() {
           return votacion_equipo;
       }
       public void setvotacion_equipo(boolean a) {
           votacion_equipo=a;
       }
       public boolean getvotacion_mision() {
           return votacion_mision;
       }
       public void setvotacion_mision(boolean a) {
           votacion_mision=a;
       }
}