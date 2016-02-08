/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class PreguntaRespuestaUnica extends Pregunta {

    private List listaRespuestas;
    public PreguntaRespuestaUnica(String nombre, String texto, List listaRespuestas) {
        super(nombre, texto);
        this.listaRespuestas = listaRespuestas;
    }

    public List getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(List listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }
    
     @Override
    public void cargaComponentes(){
        //TODO
    }
}
