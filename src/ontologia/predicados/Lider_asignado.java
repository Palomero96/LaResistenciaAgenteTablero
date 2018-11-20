package ontologia.predicados;


public class Lider_asignado extends Predicado {
    private boolean lider;
    private int equipo;
    
		public Lider_asignado()
		{ ; }
      public boolean getLider() {
           return lider;
       }
       public void setLider(boolean l) {
           lider=l;
       }
       public int getEquipo() {
           return equipo;
       }
       public void setEquipo(int l) {
           equipo=l;
       }
}