package logic;

import domain.Administrador;
import domain.Correo;
import domain.Encuesta;
import domain.Encuestado;
import gui.JIFAdministrador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
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
    private JDialog contexto;
    private Administrador administradorRecibido;
    private Encuestado encuestadoRecibido;
    private Encuesta encuestaRecibida;
    private Encuestado[] listaEncuestadosRecibida;
    private JDesktopPane escritorio;

    /*PETICION_LOGIN_ADMIN, PETICION_LOGIN_USER*/
    public Cliente(JDesktopPane escritorio, JDialog contexto, String peticion, String nick, String contrasenna) {
        this.escritorio = escritorio;
        this.contexto = contexto;
        this.peticion = peticion;
        this.nick = nick;
        this.contrasenna = contrasenna;
//        this.start();
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
            PrintStream enviar = new PrintStream(socket.getOutputStream());
            BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /*Comienza el protocolo de comunicacion*/
            switch (this.peticion) {

                case Strings.PETICION_LOGIN_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(this.nick);
                    enviar.println(this.contrasenna);
                    String adminXML = recibir.readLine();
                    if (!adminXML.equals("null")) {
                        this.administrador = recibirPeticionLoginAdmin(adminXML);
                        JIFAdministrador jifAdministrador = new JIFAdministrador(this.escritorio, this.administrador);
                        
                        Thread h1 = new Thread(jifAdministrador);
                        h1.start();
                        
                        this.contexto.dispose();
                        System.out.println(this.administrador);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                
                
//
//                case Strings.PETICION_LOGIN_USER:
//                    enviar.println(this.peticion);
//                    enviar.println(this.nick);
//                    enviar.println(this.contrasenna);
//                    this.encuestadoRecibido = (Encuestado) recibir.readObject();
//                    break;
//                    
//                case Strings.PETICION_REGISTRA_ADMIN:
//                    enviar.println(this.peticion);
//                    enviar.println(this.administrador);
//                    break;
//
//                case Strings.PETICION_REGISTRAR_USER:
//                    enviar.println(this.peticion);
//                    enviar.println(this.encuestado);
//                    break;
//                    
//                case Strings.PETICION_GET_ENCUESTADOS: 
//                    enviar.println(this.peticion);
//                    this.listaEncuestadosRecibida = (Encuestado[]) recibir.readObject();
//                    break;
//                    
//                case Strings.PETICION_CREAR_ENCUESTA:
//                    enviar.println(this.peticion);
//                    enviar.println(this.encuesta);
//                    break;
//
//                case Strings.PETICION_EDITA_ENCUESTA:
//                    enviar.println(this.peticion);
//                    enviar.println(this.nombreEncuesta);
//                    this.encuestaRecibida = (Encuesta) recibir.readObject();
//                    break;
//
//                    
//                case Strings.PETICION_GUARDA_EDICION:
//                    enviar.println(this.peticion);
//                    enviar.println(this.encuesta);
//                    
//                    break;
//
//                    
//                    
//                    
//                    //admin
//                case Strings.PETICION_ENVIAR_ENCUESTA:
//                    enviar.writeUTF(this.peticion);
//                    enviar.writeUTF(this.nombreEncuesta);
//                    enviar.writeObject(this.listaEncuestados);
//                    break;
//
//                    
//                    
//                    //encuestado
//                case Strings.PETICION_DEVOLVER_ENCUESTA:
//                    enviar.writeUTF(this.peticion);
//                    enviar.writeObject(this.encuesta);
//
//                    break;
//                    
//                case Strings.PETICION_CAMBIAR_CONTRASENNA_ADMIN:
//                    enviar.writeUTF(this.peticion);
//                    enviar.writeObject(this.administrador);
//                    
//                    break;
//                case Strings.PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO:
//                    enviar.writeUTF(this.peticion);
//                    enviar.writeObject(this.encuestado);
//                    
//                    break;
            }
            recibir.close();

            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ConnectException ce) {
            System.out.println("SERVIDOR EN MANTENIMIENTO");
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public Administrador recibirPeticionLoginAdmin(String adminXML) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(adminXML);
            Document doc = saxBuilder.build(stringReader);
            Element rootAdmin = doc.getRootElement();
            
            Administrador admin = new Administrador(rootAdmin.getAttributeValue("nickname"),
                                                            rootAdmin.getChildText("nombre"),
                                                            rootAdmin.getChildText("contrasenna"),
                                                            rootAdmin.getChildText("correo"));
            admin.setPrimeraVez(Boolean.getBoolean(rootAdmin.getChildText("primeraVez")));

            List<String> listaEncuestas = new ArrayList<>();
            
            for (int i = 0; i < rootAdmin.getChild("encuestas").getChildren().size(); i++) {
                listaEncuestas.add(rootAdmin.getChild("encuestas").getChild("encuesta"+i).getValue());
            }
            
            admin.setEncuestasCreadas(listaEncuestas);
            
            return admin;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
