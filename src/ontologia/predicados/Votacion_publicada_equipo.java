package ontologia.predicados;

import ontologia.conceptos.Resultado;

public class Votacion_publicada_equipo extends Predicado {
    private Resultado resultado;
    
		public Votacion_publicada_equipo()
		{ ; }
      public Resultado getResultado() {
           return resultado;
       }
       public void setResultado(Resultado l) {
    	   resultado=l;
       }
}