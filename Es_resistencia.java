package predicados;

import java.util.List;
import conceptos.*;

public class Es_resistencia implements Predicados {
    private List<Jugadores> lista;
    
		public Es_resistencia()
		{ ; }
      public List<Jugadores> getLista_jugadores_resistencia() {
           return lista;
       }
       public void setLista_jugadores_resistencia(List<Jugadores> l) {
           lista=l;
       }
}