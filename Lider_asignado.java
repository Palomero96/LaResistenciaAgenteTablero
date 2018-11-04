package predicados;


import java.util.List;
import conceptos.*;

public class Lider_asignado implements Predicados {
    private Jugador lider;
    
		public Lider_asignado()
		{ ; }
      public Lider getLider() {
           return lider;
       }
       public void setLider(Lider l) {
           lider=l;
       }
}