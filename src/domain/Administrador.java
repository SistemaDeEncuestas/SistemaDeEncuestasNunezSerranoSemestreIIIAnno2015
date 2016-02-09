/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Administrador extends Usuario {

    private List<Encuesta> encuestasCreadas;
    private boolean primeraVez;

    public Administrador(String nombre, String nombreUsuario, String contrasenna,String correoElectronico) {

        super(nombre, nombreUsuario, contrasenna, correoElectronico);
        this.encuestasCreadas = new ArrayList<Encuesta>();
        this.primeraVez = true;
    }

    public List<Encuesta> getEncuestasCreadas() {
        return encuestasCreadas;
    }

    public void setEncuestasCreadas(List<Encuesta> encuestasCreadas) {
        this.encuestasCreadas = encuestasCreadas;
    }

   

    public boolean isPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

    
    
    
}
