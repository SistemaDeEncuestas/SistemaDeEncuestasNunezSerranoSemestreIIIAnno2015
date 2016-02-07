/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Daniel
 */
public class PreguntaAbierta extends Pregunta{
    
    private String respuesta;

    public PreguntaAbierta(String nombre, String texto) {
        super(nombre, texto);
        this.respuesta="";
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    @Override
    public void cargaComponentes(){
        //TODO
    }
    
    
}
