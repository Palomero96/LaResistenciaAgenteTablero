package ontologia.conceptos;

public class Tarjeta extends Concepto {
 private boolean armas; //Para elegir equipo
 private boolean votacion_equipo;
 private boolean votacion_mision;
 
	public Tarjeta()
	{ ; }
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