package logic;

import domain.Administrador;
import domain.Correo;
import domain.Encuesta;
import domain.Encuestado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
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
    private List<Encuestado> listaEncuestados;
    private List<Correo> listaCorreos;

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

    public Cliente(String peticion, String nombreEncuesta, List<Encuestado> lista) {
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
            
            /*Comienza el protocolo de comunicacion*/
            switch (this.peticion) {

                case Strings.PETICION_LOGIN_ADMIN:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nick);
                    enviar.writeUTF(this.contrasenna);
                    
                    break;

                case Strings.PETICION_LOGIN_USER:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nick);
                    enviar.writeUTF(this.contrasenna);

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
                    enviar.writeUTF(this.peticion);;
                //TODO
                    
                    break;
                    
                case Strings.PETICION_CREAR_ENCUESTA:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuesta);

                    break;

                case Strings.PETICION_EDITA_ENCUESTA:
                    enviar.writeUTF(this.peticion);
                    enviar.writeUTF(this.nombreEncuesta);

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

//                case Strings.PETICION_ENVIAR_CORREO:
//                    enviar.writeUTF(this.peticion);
//                    enviar.writeObject(this.listaCorreos);
//                    break;
                    
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ADMIN:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.administrador);
                    
                    break;
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO:
                    enviar.writeUTF(this.peticion);
                    enviar.writeObject(this.encuestado);
                    
                    break;

            }
            
            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ConnectException ess) {
            System.out.println("SERVIDOR EN MANTENIMIENTO");
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.exit(0);

    }

}
