package logic;

import domain.Administrador;
import domain.Correo;
import domain.Encuesta;
import domain.Encuestado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            PrintStream enviar = new PrintStream(socket.getOutputStream());
            BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Comienza el protocolo de comunicacion
            switch (this.peticion) {

                case Strings.PETICION_LOGIN_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(this.nick);
                    enviar.println(this.contrasenna);
//                    Administrador a = recibir.readLine();
                    
                    break;

                case Strings.PETICION_LOGIN_USER:
                    enviar.println(this.peticion);
                    enviar.println(this.nick);
                    enviar.println(this.contrasenna);

                    break;

                case Strings.PETICION_REGISTRA_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(this.administrador);

                    break;

                case Strings.PETICION_REGISTRAR_USER:
                    enviar.println(this.peticion);
                    enviar.println(this.encuestado);

                    break;

                case Strings.PETICION_GET_ENCUESTADOS: 
                    enviar.println(this.peticion);;
                //TODO
                case Strings.PETICION_CREAR_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.encuesta);

                    break;

                case Strings.PETICION_EDITA_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nombreEncuesta);

                    break;

                case Strings.PETICION_GUARDA_EDICION:
                    enviar.println(this.peticion);
                    enviar.println(this.encuesta);

                    break;

                case Strings.PETICION_ENVIAR_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nombreEncuesta);
                    enviar.println(this.listaEncuestados);

                    break;

                case Strings.PETICION_DEVOLVER_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.encuesta);

                    break;

                case Strings.PETICION_ENVIAR_CORREO:
                    enviar.println(this.peticion);
                    enviar.println(this.listaCorreos);
                    break;
                    
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(this.administrador);
                    
                    break;
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO:
                    enviar.println(this.peticion);
                    enviar.println(this.encuestado);
                    
                    break;

            }

//            enviar.println("instruccion que el cliente le manda al server");
//            System.out.println("El servidor dice: " + recibir.readLine());

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
