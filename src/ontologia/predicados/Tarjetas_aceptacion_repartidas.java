package ontologia.predicados;

public class Tarjetas_aceptacion_repartidas extends Predicado {
    private boolean equipo;
    
		public Tarjetas_aceptacion_repartidas()
		{ ; }
      public boolean getTarjeta() {
           return equipo;
       }
       public void setTarjeta(boolean l) {
    	   equipo=l;
       }
}