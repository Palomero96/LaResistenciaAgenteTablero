package ontologia.conceptos;

public class Tablero extends Concepto {
 private int id;
 private Lista_Jugadores jugadores;
 
	public Tablero()
	{ ; }
       public int getId() {
           return id;
       }
       public void setId(int a) {
           id=a;
       }
       public Lista_Jugadores getJugadores(){
           return jugadores;
       }
       public void setJugadores(Lista_Jugadores j){
           jugadores=j;
       }
}