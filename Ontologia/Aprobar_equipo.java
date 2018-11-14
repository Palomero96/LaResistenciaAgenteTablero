
package acciones;



public class Aprobar_equipo implements Accion {
    private Voto voto;
    
		public Aprobar_equipo()
		{ ; }
      public Voto getvoto() {
           return voto;
       }
       public void setQue(Voto l) {
           voto=l;
       }
}