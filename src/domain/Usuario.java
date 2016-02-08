/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Daniel
 * 
 */
public class Usuario {
    
    private String nombre;
    private String nombreUsuario;
    private String contrasenna;
    private String correoElectronico;

    public Usuario() {
    }

    
    public Usuario(String nombre, String nombreUsuario, String contrasenna, String correoElectronico) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + 
                ", contrasenna=" + contrasenna + ", correoElectronico=" + correoElectronico + '}';
    }
    
    
    
}
