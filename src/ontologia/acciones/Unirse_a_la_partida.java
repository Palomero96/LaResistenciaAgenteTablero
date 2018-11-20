
package ontologia.acciones;

import ontologia.conceptos.Jugador;

public class Unirse_a_la_partida extends Accion {
    private Jugador jugador;
		public Unirse_a_la_partida()
		{ ; }
       public Jugador getjugador() {
           return jugador;
       }
       public void setjugador(Jugador l) {
          jugador=l;
       }
}