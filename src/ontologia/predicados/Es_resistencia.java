package ontologia.predicados;

import java.util.List;
import ontologia.conceptos.*;

public class Es_resistencia extends Predicado {
    private List<Jugador> lista;
    
		public Es_resistencia()
		{ ; }
      public List<Jugador> getLista_jugadores_resistencia() {
           return lista;
       }
       public void setLista_jugadores_resistencia(List<Jugador> l) {
           lista=l;
       }
}