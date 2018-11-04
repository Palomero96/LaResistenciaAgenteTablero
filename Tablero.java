import Resistencia.Concepto;
import jadex.runtime.*;
import Resistencia.*;

public class Tablero implements Concepto {
 private int id;
 private List jugadores;
 
	public Tablero()
	{ ; }
       public int getId() {
           return id;
       }
       public void setId(int a) {
           id=a;
       }
       public List getJugadores(){
           return jugadores;
       }
       public void setJugadores(List j){
           jugadores=j;
       }
}