import Resistencia.Concepto;
import jadex.runtime.*;
import Resistencia.concepto.*;

public class Lista_Jugadores implements Concepto {
private List<Jugadores> jugadores; 
	public Lista_Jugadores()
	{ ; }
       public List<Jugadores> getjugadores() {
           return jugadores;
       }
       public void setjugadores(List<Jugadores> a) {
           jugadores=a;
       }   
}