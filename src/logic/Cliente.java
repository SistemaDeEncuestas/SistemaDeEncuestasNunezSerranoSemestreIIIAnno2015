package logic;

import domain.Administrador;
import domain.Correo;
import domain.Encuesta;
import domain.Encuestado;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Strings;

/**
 *
 * @author adriansb3105
 * @author Daniel
 */
public class Cliente extends Thread {

    private String peticion;
    private String nick;
    private String contrasenna;
    private Administrador administrador;
    private Encuestado encuestado;
    private Encuesta encuesta;
    private String nombreEncuesta;
    private List<String> listaEncuestados;
    private List<Correo> listaCorreos;
    
    private Administrador administradorRecibido;
    private Encuestado encuestadoRecibido;
    private Encuesta encuestaRecibida;
    private Encuestado[] listaEncuestadosRecibida;

    /*PETICION_LOGIN_ADMIN, PETICION_LOGIN_USER*/
    public Cliente(String peticion, String nick, String contrasenna) {
        this.peticion = peticion;
        this.nick = nick;
        this.contrasenna = contrasenna;
        this.start();
    }

    public Cliente(String peticion, Administrador administrador) {
        this.peticion = peticion;
        this.administrador = administrador;
        this.start();
    }

    public Cliente(String peticion, Encuestado encuestado) {
        this.peticion = peticion;
        this.encuestado = encuestado;
        this.start();
    }

    public Cliente(String peticion) {
        this.peticion = peticion;
        this.start();
    }

    public Cliente(String peticion, Encuesta encuesta) {
        this.peticion = peticion;
        this.encuesta = encuesta;
        this.start();
    }

    public Cliente(String peticion, String nombreEncuesta) {
        this.peticion = peticion;
        this.nombreEncuesta = nombreEncuesta;
        this.start();
    }

    public Cliente(String peticion, String nombreEncuesta, List<String> lista) {
        this.peticion = peticion;
        this.nombreEncuesta = nombreEncuesta;
        this.listaEncuestados = lista;
        this.start();
    }

    public Cliente(String peticion, List<Correo> listaCorreos) {
        this.peticion = peticion;
        this.listaCorreos = listaCorreos;
        this.start();
    }

    @Override
    public void run() {
        try {
            InetAddress direccionIP = InetAddress.getByName("127.0.0.1");
            Socket socket = new Socket(direccionIP, 5700);
            ObjectOutputStream enviar = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream recibir = new ObjectInputStream(socket.getInputStream());
//              ObjectInputStream recibir = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
            
            /*Comienza el protocolo de comunicacion*/
            switch (this.peticion) {

                case Strings.PETICION_LOGIN_ADMIN:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nick);
                    enviar.writeUTF(this.contrasenna);
                    this.administradorRecibido = (Administrador) recibir.readObject();
                    break;

                case Strings.PETICION_LOGIN_USER:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nick);
                    enviar.writeUTF(this.contrasenna);
                    this.encuestadoRecibido = (Encuestado) recibir.readObject();
                    break;
                    
                case Strings.PETICION_REGISTRA_ADMIN:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.administrador);
                    break;

                case Strings.PETICION_REGISTRAR_USER:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuestado);
                    break;
                    
                case Strings.PETICION_GET_ENCUESTADOS: 
                    enviar.writeUTF(this.peticion);
                    this.listaEncuestadosRecibida = (Encuestado[]) recibir.readObject();
                    break;
                    
                case Strings.PETICION_CREAR_ENCUESTA:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuesta);
                    break;

                case Strings.PETICION_EDITA_ENCUESTA:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nombreEncuesta);
                    this.encuestaRecibida = (Encuesta) recibir.readObject();
                    break;

                    
                case Strings.PETICION_GUARDA_EDICION:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuesta);
                    
                    break;

                    
                    
                    
                    //admin
                case Strings.PETICION_ENVIAR_ENCUESTA:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nombreEncuesta);
                    enviar.writeObject(this.listaEncuestados);
                    break;

                    
                    
                    //encuestado
                case Strings.PETICION_DEVOLVER_ENCUESTA:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuesta);

                    break;
                    
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ADMIN:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.administrador);
                    
                    break;
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuestado);
                    
                    break;

            }
            recibir.close();
            
            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ConnectException ce) {
            System.out.println("SERVIDOR EN MANTENIMIENTO");
            System.exit(0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.exit(0);

    }

    public Administrador getAdministradorRecibido() {
        return administradorRecibido;
    }

    public Encuestado getEncuestadoRecibido() {
        return encuestadoRecibido;
    }

    public Encuesta getEncuestaRecibida() {
        return encuestaRecibida;
    }

    public Encuestado[] getListaEncuestadosRecibida() {
        return listaEncuestadosRecibida;
    }
    
    
    

}
