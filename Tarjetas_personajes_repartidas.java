package predicados;

import java.util.List;
import conceptos.*;

public class Tarjetas_aceptacion_repartidas implements Predicados {
    private List<Tarjeta> lista;
    
		public Tarjetas_aceptacion_repartidas()
		{ ; }
      public List<Tarjeta> getTarjetas_aceptacion_repartidas() {
           return lista;
       }
       public void setTarjetas_aceptacion_repartidas(List<Tarjeta> l) {
    	   lista=l;
       }
}