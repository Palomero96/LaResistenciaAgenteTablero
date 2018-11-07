package predicados;


import java.util.List;
import conceptos.*;

public class Lider_asignado implements Predicados {
    private boolean lider;
    
		public Lider_asignado()
		{ ; }
      public boolean getLider() {
           return lider;
       }
       public void setLider(boolean l) {
           lider=l;
       }
}