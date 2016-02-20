package logic;

import domain.Administrador;
import domain.Encuesta;
import domain.Encuestado;
import domain.Pregunta;
import domain.PreguntaAbierta;
import domain.PreguntaRespuestaMultiple;
import domain.PreguntaRespuestaUnica;
import gui.JIFAbrirEncuesta;
import gui.JIFAdministrador;
import gui.JIFCambioDeContrasennaAdmin;
import gui.JIFEditaEncuesta;
import gui.JIFEncuestado;
import gui.JIFResponderEncuesta;
import gui.Login;
import java.awt.BorderLayout;
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
import javax.swing.JInternalFrame;
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
    private String nombre;
    private List<String> lista;
    private JDialog contexto;
    private JDesktopPane escritorio;
    private JInternalFrame jInternal;

    private boolean flag;

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

    public Cliente(String peticion, Encuestado encuestado) {
        this.peticion = peticion;
        this.encuestado = encuestado;
        this.start();
    }

    public Cliente(JDesktopPane escritorio, String peticion, Encuestado encuestado) {
        this.escritorio = escritorio;
        this.peticion = peticion;
        this.encuestado = encuestado;
        this.start();
    }

    public Cliente(JDesktopPane escritorio, String peticion, Administrador admin) {
        this.escritorio = escritorio;
        this.peticion = peticion;
        this.administrador = admin;
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

    public Cliente(String peticion, Encuesta encuesta, Administrador administrador) {
        this.administrador = administrador;
        this.peticion = peticion;
        this.encuesta = encuesta;
        this.start();
    }

    public Cliente(String peticion, String nombreEncuesta) {
        this.peticion = peticion;
        this.nombre = nombreEncuesta;
        this.start();
    }

    public Cliente(String peticion, String nombreEncuesta, JInternalFrame jifEncuestado) {
        this.peticion = peticion;
        this.jInternal = jifEncuestado;
        this.nombre = nombreEncuesta;
        this.start();
    }

    public Cliente(String peticion, String nombreEncuesta, JInternalFrame jifAdmin, boolean flag) {
        this.peticion = peticion;
        this.nombre = nombreEncuesta;
        this.jInternal = jifAdmin;
        this.flag = flag;
        this.start();
    }

    public Cliente(String peticion, String nombreEncuesta, List<String> lista) {
        this.peticion = peticion;
        this.nombre = nombreEncuesta;
        this.lista = lista;
        this.start();
    }

    public Cliente(String peticion, String nickName, String nombreEncuesta, List<String> lista) {
        this.peticion = peticion;
        this.nick = nickName;
        this.nombre = nombreEncuesta;
        this.lista = lista;
        this.start();
    }

    public Cliente(String peticion, List<String> listaEncuestados, String nombreEncuesta) {
        this.peticion = peticion;
        this.nombre = nombreEncuesta;
        this.lista = listaEncuestados;
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
                        this.administrador = recibirAdministrador(adminXML);
                        System.out.println(this.administrador.getPrimeraVez());
                        if (this.administrador.getPrimeraVez().equals("true")) {
                            JIFCambioDeContrasennaAdmin jifCambio = new JIFCambioDeContrasennaAdmin(this.escritorio, this.administrador);
                            this.contexto.dispose();
                        } else {
                            JIFAdministrador jifAdministrador = new JIFAdministrador(this.escritorio, this.administrador);

                            this.contexto.dispose();
                        }

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
                        this.encuestado = recibirEncuestado(userXML);
                        JIFEncuestado jjifEncuestado = new JIFEncuestado(this.escritorio, this.encuestado);

                        this.contexto.dispose();
                        System.out.println(this.encuestado);

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_REGISTRA_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(enviarAdministrador(this.administrador));
                    String respuesta = recibir.readLine();
                    if (respuesta.equals("insertado")) {
                        JOptionPane.showMessageDialog(null, "El administrador fue insertado con exito", "Hecho", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "El administrador ya existe", Strings.ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_REGISTRAR_USER:
                    enviar.println(this.peticion);
                    enviar.println(enviarEncuestado(this.encuestado));

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
                    enviar.println(enviarEncuesta(this.encuesta));

                    String respuestaEncuestaCreada = recibir.readLine();
                    if (respuestaEncuestaCreada.equals("insertada")) {
                        JOptionPane.showMessageDialog(null, "Su encuesta se ha insertado con exito", "Listo", JOptionPane.INFORMATION_MESSAGE);
                        this.administrador.agregaEncuesta(this.encuesta.getTitulo());

                    } else {
                        JOptionPane.showMessageDialog(null, "El nombre de la encuesta ya existe, renómbrela", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_NOMBRES_POR_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nick);
                    enviar.println(this.nombre);
                    this.lista = recibirLista(recibir.readLine());
                    break;

                case Strings.PETICION_GUARDA_EDICION:
                    enviar.println(this.peticion);
                    enviar.println(enviarEncuesta(this.encuesta));
                    String respuestaEncuestaEditada = recibir.readLine();
                    if (respuestaEncuestaEditada.equals("listo")) {
                        JOptionPane.showMessageDialog(null, "Su encuesta se ha insertado con exito", "Listo", JOptionPane.INFORMATION_MESSAGE);
//                        this.administrador.eliminaEncuesta(this.encuesta.getTitulo());
//                        this.administrador.agregaEncuesta(this.encuesta.getTitulo());

                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido guardar la encuesta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                    //admin

                case Strings.PETICION_ENVIAR_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nombre);
                    enviar.println(enviarLista(this.lista));
                    if (recibir.readLine().equals("listo")) {
                        JOptionPane.showMessageDialog(null, "Hemos enviado las encuestas", "Hecho",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    break;
//
//                    
//                    
                //encuestado
                case Strings.PETICION_DEVOLVER_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(enviarEncuesta(this.encuesta));

                    if (recibir.readLine().equals("listo")) {
                        JOptionPane.showMessageDialog(null, "Hemos recibido su respuesta", "Muchas gracias por su colaboración",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido subir su encuesta", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    break;
//                    
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(enviarAdministrador(this.administrador));
                    String editado = recibir.readLine();
                    if (editado.equals("listo")) {
                        JIFAdministrador jifAdministrador = new JIFAdministrador(this.escritorio, this.administrador);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido cambiar la contraseña", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case Strings.PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO:
                    enviar.println(this.peticion);
                    enviar.println(enviarEncuestado(this.encuestado));
                    String userEditado = recibir.readLine();
                    if (userEditado.equals("listo")) {
                        JOptionPane.showMessageDialog(null, "Se ha cambiado la contraseña", "Listo",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido cambiar la contraseña", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case Strings.PETICION_GET_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nombre);
                    Encuesta encuestActual = recibirEncuesta(recibir.readLine());

                    if (!this.flag) {
                        JIFAbrirEncuesta abrir = new JIFAbrirEncuesta(this.jInternal, encuestActual);
                        abrir.ocultarBarraTitulo();
                        this.jInternal.add(abrir, BorderLayout.CENTER);
                    } else {

                        JIFEditaEncuesta edita = new JIFEditaEncuesta(this.jInternal, encuestActual);
                        edita.ocultarBarraTitulo();
                        this.jInternal.add(edita, BorderLayout.CENTER);

                    }

                    break;

                case Strings.PETICION_SOLICITA_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nombre);
                    String stringEncuesta = recibir.readLine();
                    if (!stringEncuesta.equals("null")) {
                        Encuesta encuestaActual = recibirEncuesta(stringEncuesta);

                        JIFResponderEncuesta responde = new JIFResponderEncuesta(encuestaActual);
                        responde.ocultarBarraTitulo();
                        this.jInternal.add(responde, BorderLayout.CENTER);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado la encuesta",
                                Strings.ERROR, JOptionPane.ERROR_MESSAGE);
                    }

                    break;

                case Strings.PETICION_ELIMINA_ENCUESTA:
                    enviar.println(this.peticion);
                    enviar.println(this.nombre);

                    String respuestaEliminaEncuesta = recibir.readLine();
                    if (respuestaEliminaEncuesta.equals("eliminado")) {
                        JOptionPane.showMessageDialog(null, "Hemos eliminado la encuesta", "Listo",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar la encuesta", Strings.ERROR,
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case Strings.PETICION_ENVIAR_CORREO:
                    enviar.println(this.peticion);
                    enviar.println(this.nombre);
                    enviar.println(enviarLista(this.lista));
                    if (recibir.readLine().equals("listo")) {
                        JOptionPane.showMessageDialog(null, "Hemos enviado las encuestas", "Hecho",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    break;

                case Strings.PETICION_LISTAS_USUARIOS:
                    enviar.println(this.peticion);
                    Strings.listaNombresUsuarios = recibirLista(recibir.readLine());
                    break;
                case Strings.PETICION_CERRAR_SESION:
                    
                    enviar.println(this.peticion);
                    enviar.println(this.nombre);
                    
                    break;
            }
            recibir.close();
            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ConnectException ce) {
            JOptionPane.showMessageDialog(null, "El servidor se encuentra en mantenimiento", "Lo sentimos",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Encuesta recibirEncuesta(String encuestaXML) {

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(encuestaXML);
            Document doc = saxBuilder.build(stringReader);
            Element rootEncuesta = doc.getRootElement();

            List<Pregunta> listaPreguntas = new ArrayList<>();
            Element elemPreguntas = rootEncuesta.getChild("preguntas");
            List lista = elemPreguntas.getChildren();

            for (Object objetoActualPreguntas : lista) {
                List<String> listaRespuestas = new ArrayList<>();
                Element elemActualPregunta = (Element) objetoActualPreguntas;

                Pregunta pregunta;
                switch (elemActualPregunta.getAttributeValue("tipo")) {
                    case Strings.TIPO_MULTIPLE:
                        pregunta = new PreguntaRespuestaMultiple(elemActualPregunta.getAttributeValue("enunciado"));
                        break;
                    case Strings.TIPO_UNICA:
                        pregunta = new PreguntaRespuestaUnica(elemActualPregunta.getAttributeValue("enunciado"));
                        break;
                    default://TIPO_ABIERTA
                        pregunta = new PreguntaAbierta(elemActualPregunta.getAttributeValue("enunciado"));
                        break;
                }

                List listaElementosRespuestas = elemActualPregunta.getChildren();

                for (Object objetoActualRespuesta : listaElementosRespuestas) {
                    Element elemRespuesta = (Element) objetoActualRespuesta;
                    listaRespuestas.add(elemRespuesta.getValue());
                }

                pregunta.setListaRespuestas(listaRespuestas);

                listaPreguntas.add(pregunta);

            }

            Encuesta encuesta = new Encuesta(rootEncuesta.getChildText("creador"),
                    rootEncuesta.getChildText("titulo"),
                    rootEncuesta.getChildText("descripcion"),
                    rootEncuesta.getChildText("nombreArchivo"),
                    listaPreguntas);

            return encuesta;

        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String enviarEncuestado(Encuestado encuestado) {

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

    private String enviarAdministrador(Administrador admin) {
        Element elemAdmin = new Element("administrador");
        elemAdmin.setAttribute("nickname", admin.getNickname());

        Element elemNombre = new Element("nombre");
        elemNombre.addContent(admin.getNombre());

        Element elemContrasenna = new Element("contrasenna");
        elemContrasenna.addContent(admin.getContrasenna());

        Element elemCorreo = new Element("correo");
        elemCorreo.addContent(admin.getCorreoElectronico());

        Element elemPrimeraVez = new Element("primeraVez");
        elemPrimeraVez.addContent((admin.getPrimeraVez()));

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

    private String enviarEncuesta(Encuesta encuesta) {
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

    private Administrador recibirAdministrador(String adminXML) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(adminXML);
            Document doc = saxBuilder.build(stringReader);
            Element rootAdmin = doc.getRootElement();

            Administrador admin = new Administrador(rootAdmin.getChildText("nombre"),
                    rootAdmin.getAttributeValue("nickname"),
                    rootAdmin.getChildText("contrasenna"),
                    rootAdmin.getChildText("correo"));
            admin.setPrimeraVez(rootAdmin.getChildText("primeraVez"));

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

    private Encuestado recibirEncuestado(String userXML) {
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

    private String enviarLista(List<String> lista) {

        Element elemNombres = new Element("nombres");

        for (int i = 0; i < lista.size(); i++) {
            Element elemNombre = new Element("nombre");
            elemNombre.addContent(lista.get(i));
            elemNombres.addContent(elemNombre);
        }

        XMLOutputter output = new XMLOutputter(Format.getCompactFormat());
        String nombresXML = output.outputString(elemNombres);

        nombresXML = nombresXML.replace("\n", "");

        return nombresXML;
    }

    private List<String> recibirLista(String listaString) {

        List<String> nombres = new ArrayList<>();

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(listaString);
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
    
    public List<String> getLista() {
        return lista;
    }

}
