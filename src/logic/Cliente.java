package logic;

import domain.Administrador;
import domain.Encuesta;
import domain.Encuestado;
import domain.Pregunta;
import gui.JIFAdministrador;
import gui.JIFEncuestado;
import gui.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Cliente {

    private String peticion;
    private String nick;
    private String contrasenna;
    private Administrador administrador;
    private Encuestado encuestado;
    private Encuesta encuesta;
    private String nombreEncuesta;
    private List<String> listaEncuestados;
    private List<String> listaCorreos;
    private JDialog contexto;
    private JDesktopPane escritorio;

    /*PETICION_LOGIN_ADMIN, PETICION_LOGIN_USER*/
    public Cliente(JDesktopPane escritorio, JDialog contexto, String peticion, String nick, String contrasenna) {
        this.escritorio = escritorio;
        this.contexto = contexto;
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

    public Cliente(JDesktopPane escritorio, String peticion, Encuestado encuestado) {
        this.escritorio = escritorio;
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

    public Cliente(String peticion, List<String> listaCorreos, String nombreEncuesta) {
        this.peticion = peticion;
        this.nombreEncuesta = nombreEncuesta;
        this.listaCorreos = listaCorreos;
        this.start();
    }

    private void start() {
        try {
            InetAddress direccionIP = InetAddress.getByName(Strings.IP);
            Socket socket = new Socket(direccionIP, Strings.PUERTO);
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

                        this.contexto.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_LOGIN_USER:
                    enviar.println(this.peticion);
                    enviar.println(this.nick);
                    enviar.println(this.contrasenna);
                    String userXML = recibir.readLine();
                    if (!userXML.equals("null")) {
                        this.encuestado = recibirPeticionLoginUser(userXML);
                        JIFEncuestado jjifEncuestado = new JIFEncuestado(this.escritorio, this.encuestado);

                        this.contexto.dispose();
                        System.out.println(this.encuestado);

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_REGISTRA_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(enviarRegistrarAdmin(this.administrador));
                    String respuesta = recibir.readLine();
                    if (respuesta.equals("insertado")) {
                        JOptionPane.showMessageDialog(null, "El administrador fue insertado con exito", "Hecho", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "El administrador ya existe", Strings.ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_REGISTRAR_USER:
                    enviar.println(this.peticion);
                    enviar.println(enviarRegistrarUser(this.encuestado));

                    String respuestaUser = recibir.readLine();
                    if (respuestaUser.equals("insertado")) {
                        JOptionPane.showMessageDialog(null, "El usuario fue insertado con exito", "Hecho", JOptionPane.INFORMATION_MESSAGE);
                        Login loginUsuario = new Login(escritorio);

                        loginUsuario.setLocationRelativeTo(null);

                        loginUsuario.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe", Strings.ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_CREAR_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(enviarNuevaEncuesta(this.encuesta));

                    String respuestaEncuestaCreada = recibir.readLine();
                    if (respuestaEncuestaCreada.equals("insertada")) {
                        JOptionPane.showMessageDialog(null, "Su encuesta se ha insertado con exito", "Listo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "El nombre de la encuesta ya existe, renómbrela", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

//                case Strings.PETICION_EDITA_ENCUESTA:
//                    enviar.println(this.peticion);
//                    enviar.println(this.nombreEncuesta);
//                    this.encuestaRecibida = (Encuesta) recibir.readObject();
//                    break;

                    
//                case Strings.PETICION_GUARDA_EDICION:
//                    enviar.println(this.peticion);
//                    enviar.println(this.encuesta);
//                    
//                    break;

                    
                    
                    
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
                case Strings.PETICION_ENVIAR_CORREO:
                        enviar.println(this.peticion);
                        enviar.println(this.nombreEncuesta);
                        enviar.println(enviarCorreos(this.listaCorreos));
                    break;
    
                case Strings.PETICION_LISTAS_USUARIOS:
                    enviar.println(this.peticion);
                    Strings.listaNombresUsuarios = recibirPeticionListasUsuarios(recibir.readLine());
                    break;
            }
            recibir.close();
            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ConnectException ce) {
            JOptionPane.showMessageDialog(null, "El servidor se encuentra en mantenimiento", "Lo sentimos", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String enviarRegistrarUser(Encuestado encuestado) {

        Element elemEncuestado = new Element("encuestado");
        elemEncuestado.setAttribute("nickname", encuestado.getNickname());

        Element elemNombre = new Element("nombre");
        elemNombre.addContent(encuestado.getNombre());

        Element elemContrasenna = new Element("contrasenna");
        elemContrasenna.addContent(encuestado.getContrasenna());

        Element elemCorreo = new Element("correo");
        elemCorreo.addContent(encuestado.getCorreoElectronico());

        Element elemEncuestas = new Element("encuestas");

        for (int i = 0; i < encuestado.getListaEncuestas().size(); i++) {
            Element elemEncuesta = new Element("encuesta" + i);
            elemEncuesta.addContent(encuestado.getListaEncuestas().get(i));
            elemEncuestas.addContent(elemEncuesta);
        }

        elemEncuestado.addContent(elemNombre);
        elemEncuestado.addContent(elemContrasenna);
        elemEncuestado.addContent(elemCorreo);
        elemEncuestado.addContent(elemEncuestas);

        XMLOutputter output = new XMLOutputter(Format.getCompactFormat());
        String userXML = output.outputString(elemEncuestado);

        userXML = userXML.replace("\n", "");

        return userXML;
    }

    public String enviarRegistrarAdmin(Administrador admin) {
        Element elemAdmin = new Element("administrador");
        elemAdmin.setAttribute("nickname", admin.getNickname());

        Element elemNombre = new Element("nombre");
        elemNombre.addContent(admin.getNombre());

        Element elemContrasenna = new Element("contrasenna");
        elemContrasenna.addContent(admin.getContrasenna());

        Element elemCorreo = new Element("correo");
        elemCorreo.addContent(admin.getCorreoElectronico());

        Element elemPrimeraVez = new Element("primeraVez");
        elemPrimeraVez.addContent(String.valueOf(admin.isPrimeraVez()));

        Element elemEncuestas = new Element("encuestas");

        for (int i = 0; i < admin.getEncuestasCreadas().size(); i++) {
            Element elemEncuesta = new Element("encuesta" + i);
            elemEncuesta.addContent(admin.getEncuestasCreadas().get(i));
            elemEncuestas.addContent(elemEncuesta);
        }

        elemAdmin.addContent(elemNombre);
        elemAdmin.addContent(elemContrasenna);
        elemAdmin.addContent(elemCorreo);
        elemAdmin.addContent(elemPrimeraVez);
        elemAdmin.addContent(elemEncuestas);

        XMLOutputter output = new XMLOutputter(Format.getCompactFormat());
        String adminXML = output.outputString(elemAdmin);

        adminXML = adminXML.replace("\n", "");

        return adminXML;
    }

    public String enviarNuevaEncuesta(Encuesta encuesta) {
        Element elemEncuesta = new Element("encuesta");
        Element elemCreador = new Element("creador");
        elemCreador.addContent(encuesta.getNickname());

        Element elemTitulo = new Element("titulo");
        elemTitulo.addContent(encuesta.getTitulo());

        Element elemDescripcion = new Element("descripcion");
        elemDescripcion.addContent(encuesta.getDescripcion());

        Element elemNombreArchivo = new Element("nombreArchivo");
        elemNombreArchivo.addContent(encuesta.getNombreArchivo());

        Element elemPreguntas = new Element("preguntas");

        List<Pregunta> listaPreguntas = encuesta.getPreguntas();

        for (int i = 0; i < listaPreguntas.size(); i++) {

            Element elemPregunta = new Element("pregunta");
            elemPregunta.setAttribute("tipo", listaPreguntas.get(i).getTipo());
            elemPregunta.setAttribute("enunciado", listaPreguntas.get(i).getEnunciado());

            List<String> listaRespuestas = encuesta.getPreguntas().get(i).getListaRespuestas();

            for (String respuesta : listaRespuestas) {
                Element elemRespuesta = new Element("respuesta");
                elemRespuesta.addContent(respuesta);
                elemPregunta.addContent(elemRespuesta);
            }

            elemPreguntas.addContent(elemPregunta);
        }

        elemEncuesta.addContent(elemCreador);
        elemEncuesta.addContent(elemTitulo);
        elemEncuesta.addContent(elemDescripcion);
        elemEncuesta.addContent(elemNombreArchivo);
        elemEncuesta.addContent(elemPreguntas);

        XMLOutputter output = new XMLOutputter(Format.getCompactFormat());
        String encuestaXML = output.outputString(elemEncuesta);

        encuestaXML = encuestaXML.replace("\n", "");

        return encuestaXML;

    }

    public Administrador recibirPeticionLoginAdmin(String adminXML) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(adminXML);
            Document doc = saxBuilder.build(stringReader);
            Element rootAdmin = doc.getRootElement();

            Administrador admin = new Administrador(rootAdmin.getChildText("nombre"),
                    rootAdmin.getAttributeValue("nickname"),
                    rootAdmin.getChildText("contrasenna"),
                    rootAdmin.getChildText("correo"));
            admin.setPrimeraVez(Boolean.getBoolean(rootAdmin.getChildText("primeraVez")));

            List<String> listaEncuestas = new ArrayList<>();

            for (int i = 0; i < rootAdmin.getChild("encuestas").getChildren().size(); i++) {
                listaEncuestas.add(rootAdmin.getChild("encuestas").getChild("encuesta" + i).getValue());
            }

            admin.setEncuestasCreadas(listaEncuestas);

            return admin;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Encuestado recibirPeticionLoginUser(String userXML) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(userXML);
            Document doc = saxBuilder.build(stringReader);
            Element rootUser = doc.getRootElement();

            Encuestado user = new Encuestado(rootUser.getChildText("nombre"),
                    rootUser.getAttributeValue("nickname"),
                    rootUser.getChildText("contrasenna"),
                    rootUser.getChildText("correo"));

            List<String> listaEncuestas = new ArrayList<>();

            for (int i = 0; i < rootUser.getChild("encuestas").getChildren().size(); i++) {
                listaEncuestas.add(rootUser.getChild("encuestas").getChild("encuesta" + i).getValue());
            }

            user.setListaEncuestas(listaEncuestas);

            return user;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<String> recibirPeticionListasUsuarios(String nombresEncuestadosXML) {

        List<String> nombres = new ArrayList<>();

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(nombresEncuestadosXML);
            Document doc;
            doc = saxBuilder.build(stringReader);
            Element rootAdmin = doc.getRootElement();
            
            List lista = rootAdmin.getChildren();
            
            for (Object objActual : lista) {
                Element elemActual = (Element) objActual;
                nombres.add(elemActual.getValue());
            }
            
            return nombres;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    private String enviarCorreos(List<String> listaCorreos) {
        
        Element elemCorreos = new Element("correos");
        
        for (int i = 0; i < listaCorreos.size(); i++) {
            Element elemCorreo = new Element("correo");
            elemCorreo.addContent(listaCorreos.get(i));
            elemCorreos.addContent(elemCorreo);
        }
        
        XMLOutputter output = new XMLOutputter(Format.getCompactFormat());
        String userXML = output.outputString(elemCorreos);

        userXML = userXML.replace("\n", "");

        return userXML;
    }
}
