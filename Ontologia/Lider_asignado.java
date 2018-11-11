package predicados;


import java.util.List;
import conceptos.*;

public class Lider_asignado implements Predicados {
    private boolean lider;
    private int equipo;
    
		public Lider_asignado()
		{ ; }
      public boolean getLider() {
           return lider;
       }
       public void setLider(boolean l) {
           lider=l;
       }
       public boolean getEquipo() {
           return equipo;
       }
       public void setEquipo(int l) {
           equipo=l;
       }
}