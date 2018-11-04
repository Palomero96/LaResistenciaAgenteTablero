
package acciones;

import conceptos.*;


public class Avisar_espias implements Accion {
    private Lista_jugadores lista;
    
		public Avisar_espias()
		{ ; }
      public Lista_espias getLista_jugadores() {
           return lista;
       }
       public void setLista_jugadores(Lista_jugadores l) {
           lista=l;
       }
}