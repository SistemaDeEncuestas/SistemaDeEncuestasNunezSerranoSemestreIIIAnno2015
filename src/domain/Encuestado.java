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
public class Encuestado extends Usuario{
    
   private List<Encuesta> listaEncuestas;

    public Encuestado(String nombre, String nombreUsuario, String contrasenna, String correoElectronico) {
        
        super(nombre, nombreUsuario, contrasenna, correoElectronico);
        this.listaEncuestas = new ArrayList<Encuesta>();
    }

    public List<Encuesta> getListaEncuestas() {
        return listaEncuestas;
    }

    public void setListaEncuestas(List<Encuesta> listaEncuestas) {
        this.listaEncuestas = listaEncuestas;
    }
   
   
    
}
