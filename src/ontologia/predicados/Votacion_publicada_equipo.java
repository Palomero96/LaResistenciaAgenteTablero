package ontologia.predicados;

import ontologia.conceptos.Resultado;

public class Votacion_publicada_equipo extends Predicado {
    private Resultado resultado;
    private ArrayList <Voto> votosEquipo; 
    
		public Votacion_publicada_equipo()
		{ ; }
      public Resultado getResultado() {
           return resultado;
       }

       public void setResultado(Resultado l) {
    	   resultado=l;
       }

		public Resultado getvotosEquipo() {
           return votosEquipo;
       }

       public void setvotosEquipo(ArrayList <Voto> l) {
    	   votosEquipo=l;
       }

}