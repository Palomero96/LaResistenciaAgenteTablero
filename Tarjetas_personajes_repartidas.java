package predicados;

import java.util.List;
import conceptos.*;

public class Tarjetas_personajes_repartidas implements Predicados {
    private boolean espia;
    
		public Tarjetas_personajes_repartidas()
		{ ; }
      public boolean getTarjetas_personajes_repartidas() {
           return espia;
       }
       public void setTarjetas_personajes_repartidas(boolean l) {
    	   espia=l;
       }
}