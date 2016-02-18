package util;

import java.util.List;

/**
 * @author adriansb3105
 */
public class Strings {

    public final static String IP = "localhost";
    public final static int PUERTO = 5700;
    public static List<String> listaNombresUsuarios;
    
    /*PantallaPrincipal*/
    public final static String JMENU_NOMBRE = "Archivo";
    public final static String ITEM_REGISTRARSE = "Registrar Usuario";
    public final static String ITEM_INICIAR = "Iniciar Sesión";
    public final static String ITEM_CERRAR = "Cerrar Sesión";

    public final static String PANTALLAPRINCIPALTITULO = "Sistema de Encuestas";
    public final static String LOGINTITULO = "Login";
    public final static String REGISTROTITULO = "Registro";
    public final static String REGISTRAR = "Registrarse";

    /*Login*/
    public final static String LOGINNICKADMIN = "Ingrese su usuario administrador";
    public final static String LOGINNICKUSUARIO = "Ingrese su usuario";
    public final static String LOGINCONTRASENIA = "Ingrese su contraseña";
    public final static String LOGININGRESAR = "Ingresar";

    /*Registro*/
    public final static String REGISTRONOMBRE = "Ingrese su nombre";
    public final static String REGISTRONOMBREUSUARIO = "Ingrese su nombre de usuario";
    public final static String REGISTROCORREO = "Ingrese su correo electrónico";
    public final static String REGISTROCONTRASENIA = "Ingrese su contraseña";
    public final static String REGISTROVERIFICACONTRASENIA = "Verifique su contraseña";
    public final static String REGISTRO_CONTRASENIA_TEMPORAL = "Ingrese su contraseña (Recuerde que esta es temporal)";

    /*Mensajes*/
    public final static String ERROR = "Error";
    public final static String ERRORCAMPOVACIO = "Debe llenar todos los campos";
    public final static String ERROR_CONTRASENNA_DIFERENTE = "La contraseña debe coincidir en los dos campos";
    public final static String ERROR_NOMBRE_USUARIO_OCUPADO = "El nombre de usuario escogido ya existe, por favor escoja otro";

    /*JIFAdministrador*/
    public final static String ENCUESTAS = "Tus encuestas";
    public final static String NUEVA_ENCUESTA = "Nueva encuesta";
    public final static String EDITAR_ENCUESTA = "Editar encuesta";
    public final static String ELIMINAR_ENCUESTA = "Eliminar encuesta";
    public final static String A_CORREO = "Enviar a correo electrónico";
    public final static String NUEVO_ADMIN = "Crear nueva cuenta de administrador";
    public final static String ESTADISTICAS = "Mostrar estadísticas";
    public final static String A_PDF = "Exportar a pdf";

    public final static String BORDE_DATOS = "Datos de usuario";
    public final static String BORDE_HISTORIAL = "Historial";
    public final static String BORDE_BANDEJA_USUARIO = "Bandeja de entrada";
    public final static String BORDE_BANDEJA_ADMIN = "Tus encuestas";

    public final static String TIPO_MULTIPLE = "multiple";
    public final static String TIPO_UNICA = "unica";
    public final static String TIPO_ABIERTA = "abierta";

    public final static String RUTA_ARCHIVOS = "src/files/nombresDeArchivos.xml";

    /* NuevaEncuesta*/
    public final static String TITULO = "Ingrese el título de la encuesta:";
    public final static String DESCRIPCION = "Ingrese una breve descripción";
    public final static String PREGUNTA = "Nueva pregunta";
    public final static String RESPUESTA = "Agregar respuesta";
    public final static String TIPO_1 = "Pregunta de respuesta única";
    public final static String TIPO_2 = "Pregunta de respuesta múltiple";
    public final static String TIPO_3 = "Pregunta de respuesta abierta";
    public final static String OPTION_TITULO = "Elija el tipo";
    public final static String OPTION_PREGUNTA = "¿Qué tipo de pregunta desea ingresar?";
    public final static String GUARDAR = "Guardar";
    public final static String CANCELAR = "Cancelar";

    /*Constantes para las peticiones del cliente*/
    public final static String PETICION_LISTAS_USUARIOS = "listasUsuarios";
    public final static String PETICION_LOGIN_ADMIN = "loginAdministrador";
    public final static String PETICION_LOGIN_USER = "loginEncuestado";
    public final static String PETICION_REGISTRA_ADMIN = "registrarAdministrador";
    public final static String PETICION_REGISTRAR_USER = "registarUsuario";
    public final static String PETICION_GET_ENCUESTADOS = "getEncuestados";
    public final static String PETICION_CREAR_ENCUESTA = "crearEncuesta";
    public final static String PETICION_EDITA_ENCUESTA = "editarEncuesta";
    public final static String PETICION_GUARDA_EDICION = "guardarEdicion";
    public final static String PETICION_ENVIAR_ENCUESTA = "enviarEncuesta";
    public final static String PETICION_DEVOLVER_ENCUESTA = "devolverEncuesta";
    public final static String PETICION_ENVIAR_CORREO = "enviarCorreo";
    public final static String PETICION_CAMBIAR_CONTRASENNA_ADMIN = "cambiarContrasennaAdministrador";
    public final static String PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO = "cambiarContrasennaEncuestado";

    /* PanelEnviarCorreos*/
    public final static String LABEL_ENCUESTADOS = "Escoja a los usuarios que quiera enviarle la encuesta";
    public final static String BOTON_ANNADIR = "Añadir";
    public final static String LABEL_ENCUESTA = "Escoja una encuesta para enviar";
    public final static String BOTON_ENVIAR = "Enviar";
    public final static String BOTON_ELIMINAR = "Eliminar";

    /*Panel elimina encuestas*/
    public final static String LABEL_ESCOGE_ENCUESTA = "Por favor, escoja una encuesta";
     public final static String LABEL_AVISO = "Se ha eliminado la encuesta seleccionada";

}
