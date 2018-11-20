package ontologia.predicados;

public class Tarjetas_exito_fracaso_repartidas extends Predicado {
    private boolean mision;
    
		public Tarjetas_exito_fracaso_repartidas()
		{ ; }
      public boolean getTarjetas() {
           return mision;
       }
       public void setTarjetas(boolean l) {
    	   mision=l;
       }
}