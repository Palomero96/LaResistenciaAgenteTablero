package predicados;

import conceptos;


public class Equipo_elegido implements Predicados {
    private Lista_jugadores lista;
    
		public Equipo_elegido()
		{ ; }
      public Lista_jugadores getLista_jugadores() {
           return lista;
       }
       public void setLista_jugadores(Lista_jugadores l) {
           lista=l;
       }
}