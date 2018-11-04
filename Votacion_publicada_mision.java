package predicados;
import java.util.List;
import conceptos.*;

public class Votacion_publicada_mision implements Predicados {
    private Resultado resultado;
    
		public Votacion_publicada_mision()
		{ ; }
      public Resultado getResultado() {
           return resultado;
       }
       public void setResultado(Resultado l) {
    	   resultado=l;
       }
}