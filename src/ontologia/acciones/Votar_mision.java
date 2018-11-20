package ontologia.acciones;

import ontologia.conceptos.Voto;

public class Votar_mision extends Accion {
    private Voto voto;
    
		public Votar_mision()
		{ ; }
      public Voto getvoto() {
           return voto;
       }
       public void setVoto(Voto l) {
           voto=l;
       }
}