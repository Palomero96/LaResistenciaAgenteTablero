package ontologia.predicados;

import java.util.List;
import ontologia.conceptos.*;

public class Es_espia extends Predicado {
    private List<Jugador> lista;
    
		public Es_espia()
		{ ; }
      public List<Jugador> getLista_jugadores_sospechoso() {
           return lista;
       }
       public void setLista_jugadores_sospechoso(List<Jugador> l) {
           lista=l;
       }
}