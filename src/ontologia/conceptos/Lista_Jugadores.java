package ontologia.conceptos;

import java.util.List;

public class Lista_Jugadores extends Concepto {
private List<Jugador> jugadores; 
	public Lista_Jugadores()
	{ ; }
       public List<Jugador> getjugadores() {
           return jugadores;
       }
       public void setjugadores(List<Jugador> a) {
           jugadores=a;
       }   
}