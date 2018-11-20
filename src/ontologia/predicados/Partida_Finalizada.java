package ontologia.predicados;

public class Partida_Finalizada extends Predicado {
    private boolean ganaResistencia;
    
		public Partida_Finalizada()
		{ ; }
      public boolean getGanaResistencia() {
           return ganaResistencia;
       }
       public void setGanaResistencia(boolean ganaResis) {
    	   ganaResistencia = ganaResis;
       }

}