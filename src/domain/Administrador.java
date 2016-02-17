package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Administrador extends Usuario {

    private List<String> encuestasCreadas;
    private boolean primeraVez;

    public Administrador(String nombre, String nickName, String contrasenna,String correoElectronico) {
        super(nombre, nickName, contrasenna, correoElectronico);
        this.encuestasCreadas = new ArrayList<>();
        this.primeraVez = true;
    }

    public Administrador() {
        super("", "", "", "");
        this.encuestasCreadas = new ArrayList<>();
        this.primeraVez = true;
    }
    
    public List<String> getEncuestasCreadas() {
        return encuestasCreadas;
    }

    public void setEncuestasCreadas(List<String> encuestasCreadas) {
        this.encuestasCreadas = encuestasCreadas;
    }

   

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

    @Override
    public String toString() {
        return super.toString() + " Administrador{" + "encuestasCreadas=" + encuestasCreadas + ", primeraVez=" + primeraVez + '}';
    }

}
