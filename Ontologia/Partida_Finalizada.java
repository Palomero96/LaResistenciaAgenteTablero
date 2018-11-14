package predicados;

public class Partida_Finalizada implements Predicados {
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