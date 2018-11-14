package predicados;
import java.util.List;
import conceptos.*;

public class Tarjetas_exito_fracaso_repartidas implements Predicados {
    private boolean mision;
    
		public Tarjetas_exito_fracaso_repartidas()
		{ ; }
      public boolean getTarjetas() {
           return mision;
       }
       public void setTarjetas(boolean l) {
    	   mision=l;
       }
}