package predicados;

import java.util.List;
import conceptos.*;

public class Es_espia implements Predicados {
    private List<Jugadores> lista;
    
		public Es_espia()
		{ ; }
      public List<Jugadores> getLista_jugadores_sospechoso() {
           return lista;
       }
       public void setLista_jugadores_sospechoso(List<Jugadores> l) {
           lista=l;
       }
}