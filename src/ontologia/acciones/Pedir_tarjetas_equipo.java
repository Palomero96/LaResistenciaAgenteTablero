
package ontologia.acciones;

import java.util.List;
import ontologia.conceptos.*;


public class Pedir_tarjetas_equipo extends Accion {
    private List<Tarjeta> lista;
    
		public Pedir_tarjetas_equipo()
		{ ; }
      public List<Tarjeta> getListTarjeta_equipo() {
           return lista;
       }
       public void setListTarjeta_equipo(List<Tarjeta> l) {
           lista=l;
       }
}