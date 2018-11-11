package predicados;
import java.util.List;
import conceptos.*;

public class Tarjetas_aceptacion_repartidas implements Predicados {
    private List<Tarjeta> lista;
    
		public Tarjetas_aceptacion_repartidas()
		{ ; }
      public List<Tarjeta> getTarjetas() {
           return lista;
       }
       public void setTarjetas(List<Tarjeta_votacion> l) {
    	   lista=l;
       }
}