
package ontologia.acciones;

import ontologia.conceptos.*;


public class Avisar_espias extends Accion {
    private Lista_Jugadores lista;
    
		public Avisar_espias()
		{ ; }
      public Lista_Jugadores getLista_jugadores() {
           return lista;
       }
       public void setLista_jugadores(Lista_Jugadores l) {
           lista=l;
       }
}