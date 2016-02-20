package gui;

import domain.Encuesta;
import domain.Encuestado;
import domain.Pregunta;
import domain.PreguntaAbierta;
import domain.PreguntaRespuestaMultiple;
import domain.PreguntaRespuestaUnica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import logic.Cliente;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFEncuestado extends JInternalFrame implements ActionListener {

    private JPanel jpArriba;
    private JPanel jpIzquierda;
    private JPanel jpEntrada;
    private JPanel jpBoton;
    private JPanel jpHistorial;
    private JLabel jlNombre;
    private JLabel jlNickname;
    private JLabel jlCorreo;
    private JLabel jlLIsta;
    private JLabel jlBandeja;
    private JButton jbAbrir;
    private JButton jbConfiguracion;
    private JButton jbCerrarSesion;
    private Border tituloArriba;
    private Border bandejaDeEntrada;
    private Border historial;
    private Encuestado encuestado;
    private JList jListEncuestasCompartidas;
    private JDesktopPane escritorio;

    public JIFEncuestado(JDesktopPane escritorio, Encuestado encuestado) {

        super("Bienvenido " + encuestado.getNickname(), true, false, true);
        this.setLayout(new BorderLayout());
        this.escritorio = escritorio;
        this.encuestado = encuestado;
        initComponents();
        this.setSize(800, 600);
        this.escritorio.add(this);
        this.setVisible(true);

    }

    private void initComponents() {

        jpArriba = new JPanel();
        tituloArriba = BorderFactory.createTitledBorder(null, Strings.BORDE_DATOS, TitledBorder.CENTER, TitledBorder.CENTER);
        jpArriba.setBorder(tituloArriba);
        jpArriba.setLayout(new BoxLayout(jpArriba, BoxLayout.Y_AXIS));
        this.add(jpArriba, BorderLayout.NORTH);

        jlNombre = new JLabel(this.encuestado.getNombre());
        jpArriba.add(jlNombre);
        jlNickname = new JLabel(this.encuestado.getNickname());
        jpArriba.add(jlNickname);
        jlCorreo = new JLabel(this.encuestado.getCorreoElectronico());
        jpArriba.add(jlCorreo);
        jlLIsta = new JLabel("Encuestas (" + this.encuestado.getListaEncuestas().size() + ")");
        jpArriba.add(jlLIsta);
        jbConfiguracion = new JButton();
        jbConfiguracion.setIcon(new ImageIcon(getClass().getResource("/images/settings.png")));
        jbConfiguracion.addActionListener(this);
        jpArriba.add(jbConfiguracion);
        
         jbCerrarSesion = new JButton();
        jbCerrarSesion.setIcon(new ImageIcon(getClass().getResource("/images/close.png")));
        jbCerrarSesion.addActionListener(this);
        jpArriba.add(jbCerrarSesion);
        

        jpIzquierda = new JPanel();
        jpIzquierda.setBackground(Color.blue);
        jpIzquierda.setLayout(new BoxLayout(jpIzquierda, BoxLayout.Y_AXIS));
        this.add(jpIzquierda, BorderLayout.WEST);

        jpEntrada = new JPanel(new GridBagLayout());
        bandejaDeEntrada = BorderFactory.createTitledBorder(null, Strings.BORDE_BANDEJA_USUARIO, TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.NONE;
        grid.anchor = GridBagConstraints.NORTH;

        jlBandeja = new JLabel("  Lista de encuestas  ");
        grid.gridx = 0;
        grid.gridy = 0;
        jpEntrada.add(jlBandeja, grid);

        jListEncuestasCompartidas = new JList();
        jListEncuestasCompartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListEncuestasCompartidas.setBackground(new java.awt.Color(192, 192, 192));
        jListEncuestasCompartidas.setListData(llenaLista());
        grid.gridx = 0;
        grid.gridy = 1;
        jpEntrada.add(jListEncuestasCompartidas, grid);
        jpIzquierda.add(jpEntrada);

        jpBoton = new JPanel();
        jpIzquierda.add(jpBoton);

        jbAbrir = new JButton("Abrir");
        jbAbrir.addActionListener(this);
        jpBoton.add(jbAbrir);

        jpHistorial = new JPanel();
        historial = BorderFactory.createTitledBorder(null, Strings.BORDE_HISTORIAL, TitledBorder.CENTER, TitledBorder.CENTER);
        jpHistorial.setBorder(historial);
        jpIzquierda.add(jpHistorial);

//        jpDerecha = new JPanel();
//        jpDerecha.setBackground(Color.GRAY);
//        this.add(jpDerecha, BorderLayout.CENTER);

    }

    public String[] llenaLista() {
        String[] encuestas = new String[this.encuestado.getListaEncuestas().size()];

        for (int i = 0; i < this.encuestado.getListaEncuestas().size(); i++) {
            encuestas[i] = this.encuestado.getListaEncuestas().get(i);

        }

        return encuestas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbAbrir) {
//            List<Pregunta> lista = new ArrayList<>();
//           
//            Pregunta p1 = new PreguntaRespuestaMultiple("Pregunta 1");
//            p1.setTipo(Strings.TIPO_MULTIPLE);
//            List<String> respuestap1 = new ArrayList<>();
//            String r1p1 = "reps 1";
//            String r1p2 = "reps 2";
//            String r1p3 = "reps 3";
//            String r1p4 = "reps 4";
//            respuestap1.add(r1p1);
//            respuestap1.add(r1p2);
//            respuestap1.add(r1p3);
//            respuestap1.add(r1p4);
//            p1.setListaRespuestas(respuestap1);
//            
//            Pregunta p2 = new PreguntaRespuestaUnica("Pregunta 2");
//            p2.setTipo(Strings.TIPO_UNICA);
//            List<String> respuestap2 = new ArrayList<>();
//            String r2p1 = "reps a";
//            String r2p2 = "reps b";
//            String r2p3 = "reps c";
//            String r2p4 = "reps d";
//            respuestap2.add(r2p1);
//            respuestap2.add(r2p2);
//            respuestap2.add(r2p3);
//            respuestap2.add(r2p4);
//            p2.setListaRespuestas(respuestap2);
//            
//            
//            Pregunta p3 = new PreguntaRespuestaMultiple("Pregunta 3");
//            p3.setTipo(Strings.TIPO_MULTIPLE);
//            List<String> respuestap3 = new ArrayList<>();
//            String r3p1 = "reps 1";
//            String r3p2 = "reps 2";
//            String r3p3 = "reps 3";
//            String r3p4 = "reps 4";
//            respuestap3.add(r3p1);
//            respuestap3.add(r3p2);
//            respuestap3.add(r3p3);
//            respuestap3.add(r3p4);
//            p3.setListaRespuestas(respuestap3);
//            
//            Pregunta p4 = new PreguntaAbierta("Pregunta 4");
//            p4.setTipo(Strings.TIPO_ABIERTA);
//            
//            Pregunta p5 = new PreguntaRespuestaUnica("Pregunta 5");
//            p5.setTipo(Strings.TIPO_UNICA);
//            List<String> respuestap5 = new ArrayList<>();
//            String r5p1 = "reps a";
//            String r5p2 = "reps b";
//            String r5p3 = "reps c";
//            String r5p4 = "reps d";
//            respuestap5.add(r5p1);
//            respuestap5.add(r5p2);
//            respuestap5.add(r5p3);
//            respuestap5.add(r5p4);
//            p5.setListaRespuestas(respuestap5);
//            
//            
//            lista.add(p1);
//            lista.add(p2);
//            lista.add(p3);
//            lista.add(p4);
//            lista.add(p5);
//
//            Encuesta encuesta = new Encuesta("pba", "Prueba encuesta",
//                    "Esto es una prueba para probar el panel de llena encuesta", "encuesta1", lista);
            String encuestaActual = jListEncuestasCompartidas.getSelectedValue().toString();
            
            Cliente cliente = new Cliente(Strings.PETICION_SOLICITA_ENCUESTA, encuestaActual, this);
//            
            
        } else if(e.getSource() == jbConfiguracion){
            JIFCambioContrasennaEncuestado jifCambio = new JIFCambioContrasennaEncuestado(this.encuestado);
            jifCambio.ocultarBarraTitulo();
            this.add(jifCambio, BorderLayout.CENTER);
        }else if(e.getSource() == jbCerrarSesion){
             Cliente cliente = new Cliente(Strings.PETICION_CERRAR_SESION, this.encuestado.getNickname());
             this.dispose();
        }

    }

}
