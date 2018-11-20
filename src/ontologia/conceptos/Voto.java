package ontologia.conceptos;

public class Voto extends Concepto {

 private boolean equipo;
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
}