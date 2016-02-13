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
    private String contrasnna;
    private Administrador administrador;
    private Encuesta encuesta;
    private String nombreEncuesta;
    private List<Encuestado> listaEncuestados;
    private List<Correo> listaCorreos;

    public Cliente(String peticion, String nick, String contrasenna) {
        this.peticion = peticion;
        this.nick = nick;
        this.contrasnna = contrasenna;
    }

    public Cliente(String peticion, Administrador administrador) {
        this.peticion = peticion;
        this.administrador = administrador;
    }

    public Cliente(String peticion) {
        this.peticion = peticion;
    }

    public Cliente(String peticion, Encuesta encuesta) {
        this.peticion = peticion;
        this.encuesta = encuesta;
    }

    public Cliente(String peticion, String nombreEncuesta) {
        this.peticion = peticion;
        this.nombreEncuesta = nombreEncuesta;
    }

    public Cliente(String peticion, String nombreEncuesta, List<Encuestado> lista) {
        this.peticion = peticion;
        this.nombreEncuesta = nombreEncuesta;
        this.listaEncuestados = lista;
    }

    public Cliente(String peticion, List<Correo> listaCorreos) {
        this.peticion = peticion;
        this.listaCorreos = listaCorreos;
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
                
                case Strings.PETICION_LOGIN_ADMIN: ;
                    
                case Strings.PETICION_LOGIN_USER: ;
                    
                case Strings.PETICION_REGISTRA_ADMIN: ;
                    
                case Strings.PETICION_REGISTRAR_USER: ;
                    
                case Strings.PETICION_GET_ENCUESTADOS: ;
                    
                case Strings.PETICION_CREAR_ENCUESTA: ;
                    
                case Strings.PETICION_EDITA_ENCUESTA: ;
                    
                case Strings.PETICION_GUARDA_EDICION: ;
                    
                case Strings.PETICION_ENVIAR_ENCUESTA: ;
                    
                case Strings.PETICION_DEVOLVER_ENCUESTA: ;
                    
                case Strings.PETICION_ENVIAR_CORREO: ; 
                    
            }

            enviar.println("instruccion que el cliente le manda al server");

            System.out.println("El servidor dice: " + recibir.readLine());

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
