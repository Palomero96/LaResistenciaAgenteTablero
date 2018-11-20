
package ontologia.acciones;

import ontologia.conceptos.Voto;

public class Aprobar_equipo extends Accion {
    private Voto voto;
    
		public Aprobar_equipo()
		{ ; }
      public Voto getvoto() {
           return voto;
       }
       public void setVoto(Voto l) {
           voto=l;
       }
}