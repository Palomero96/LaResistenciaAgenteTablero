package ontologia.predicados;

import ontologia.conceptos.*;


public class Equipo_elegido extends Predicado {
    private Lista_Jugadores lista;
    
		public Equipo_elegido()
		{ ; }
      public Lista_Jugadores getLista_jugadores() {
           return lista;
       }
       public void setLista_jugadores(Lista_Jugadores l) {
           lista=l;
       }
}