package ontologia.predicados;

import ontologia.conceptos.Resultado;

public class Votacion_publicada_mision extends Predicado {
    private Resultado resultado;
    private int fracasos;
    private int exitos;

    
		public Votacion_publicada_mision()
		{ ; }
      public Resultado getResultado() {
           return resultado;
       }
       public void setResultado(Resultado l) {
    	   resultado=l;
       }
       public int getFracasos() {
           return fracasos;
       }

       public void setFracasos(int l) {
    	   fracasos=l;
       }
        public int getExitos() {
           return exitos;
       }

       public void setExitos(int l) {
    	   exitos=l;
       }
}