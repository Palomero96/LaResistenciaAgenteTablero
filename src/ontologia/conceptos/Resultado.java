package ontologia.conceptos;

public class Resultado extends Concepto {
 private boolean Resultado_mision;
 private boolean Resultado_equipo;
 
 
	public Resultado()
	{ ; }
       public boolean getResultadomision() {
           return Resultado_mision;
       }
       public void setResultadomision(boolean a) {
           Resultado_mision=a;
       }
       public boolean getResultadoequipo() {
           return Resultado_equipo;
       }
       public void setResultadoequipo(boolean a) {
           Resultado_equipo=a;
       }
       
}