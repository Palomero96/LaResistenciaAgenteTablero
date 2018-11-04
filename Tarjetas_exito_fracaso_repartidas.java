package predicados;
import java.util.List;
import conceptos.*;

public class Tarjetas_exito_fracaso_repartidas implements Predicados {
    private List<Tarjeta> lista;
    
		public Tarjetas_exito_fracaso_repartidas()
		{ ; }
      public List<Tarjeta> getTarjetas() {
           return lista;
       }
       public void setTarjetas(List<Tarjeta> l) {
    	   lista=l;
       }
}