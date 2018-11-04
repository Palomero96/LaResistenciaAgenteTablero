import Resistencia.Concepto;
import jadex.runtime.*;

public class Resultado implements Concepto {
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