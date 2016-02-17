package domain;

import java.io.Serializable;

/**
 *
 * @author Daniel
 * 
 */
public class Usuario implements Serializable{
    
    private String nombre;
    private String nickName;
    private String contrasenna;
    private String correoElectronico;

    public Usuario() {
        this.nombre = "";
        this.nickName = "";
        this.contrasenna = "";
        this.correoElectronico = "";
    }

    
    public Usuario(String nombre, String nickName, String contrasenna, String correoElectronico) {
        this.nombre = nombre;
        this.nickName = nickName;
        this.contrasenna = contrasenna;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nombreUsuario) {
        this.nickName = nombreUsuario;
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
        return "Usuario{" + "nombre=" + nombre + ", nombreUsuario=" + nickName + 
                ", contrasenna=" + contrasenna + ", correoElectronico=" + correoElectronico + '}';
    }
    
}
