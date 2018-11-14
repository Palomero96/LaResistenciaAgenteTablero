package predicados;
import java.util.List;
import conceptos.*;

public class Tarjetas_aceptacion_repartidas implements Predicados {
    private boolean equipo;
    
		public Tarjetas_aceptacion_repartidas()
		{ ; }
      public boolean getTarjeta() {
           return equipo;
       }
       public void setTarjeta(boolean l) {
    	   equipo=l;
       }
}