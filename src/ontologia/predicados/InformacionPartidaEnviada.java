package ontologia.predicados;

import ontologia.conceptos.*;


public class InformacionPartidaEnviada extends Predicado {
     private Lista_Jugadores lista;
    
    public InformacionPartidaEnviada()
    { ; }
      public Lista_Jugadores getLista_jugadores() {
           return lista;
       }
       public void setLista_jugadores(Lista_Jugadores l) {
           lista=l;
       }
}