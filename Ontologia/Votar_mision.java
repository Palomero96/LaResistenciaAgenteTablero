package acciones;


public class Votar_mision implements Accion {
    private Voto voto;
    
		public Votar_mision()
		{ ; }
      public Voto getvoto() {
           return voto;
       }
       public void setVoto(voto l) {
           voto=l;
       }
}