
package gui;

import domain.Administrador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFAdministrador extends JInternalFrame implements ActionListener, Runnable{

    private Administrador administrador;
    private JPanel jPanelIzquierda;
    private JPanel jpDatos;
    private JPanel jpEntrada;
    private JPanel jpHistorial;
    private JPanel jpContenido;
    private JButton jbEncuestas;
    private JButton jbNuevo;
    private JButton jbEditar;
    private JButton jbEliminar;
    private JButton jbEnviar;
    private JButton jbNuevoAdmin;
    private JButton jbToPdf;
    private JButton jbEstadisticas;
    private Border bordeDatos;
    private Border bandejaDeEntrada;
    private Border historial;
    private JLabel jlNombre;
    private JLabel jlNickname;
    private JLabel jlCorreo;
    private JLabel jlListaEncuestas;
    private JToolBar jToolBar;
     private JScrollPane scroll ;
     private JDesktopPane escritorio;

    public JIFAdministrador(JDesktopPane escritorio, Administrador administrador) {
        super("Bienvenido", true, true, true);
        this.setLayout(new BorderLayout());
        this.administrador = administrador;
        this.setBackground(Color.GRAY);
        this.setSize(850, 600);
        this.escritorio = escritorio;
    }

    private void init() {

        jToolBar = new JToolBar();
        jToolBar.setSize(800, 600);
        jToolBar.setLayout(new FlowLayout(0, 10, 5));

        jbEncuestas = new JButton();
        jbEncuestas.setIcon(new ImageIcon(getClass().getResource("/images/encuestas.png")));
        jbEncuestas.setToolTipText(Strings.ENCUESTAS);
        jbEncuestas.addActionListener(this);
        jToolBar.add(jbEncuestas);
        jbNuevo = new JButton();
        jbNuevo.setIcon(new ImageIcon(getClass().getResource("/images/nueva.png")));
        jbNuevo.setToolTipText(Strings.NUEVA_ENCUESTA);
        jbNuevo.addActionListener(this);
        jToolBar.add(jbNuevo);
        jbEditar = new JButton();
        jbEditar.setIcon(new ImageIcon(getClass().getResource("/images/editar.png")));
        jbEditar.setToolTipText(Strings.EDITAR_ENCUESTA);
        jbEditar.addActionListener(this);
        jToolBar.add(jbEditar);
        jbEliminar = new JButton();
        jbEliminar.setIcon(new ImageIcon(getClass().getResource("/images/eliminar.png")));
        jbEliminar.setToolTipText(Strings.ELIMINAR_ENCUESTA);
        jbEliminar.addActionListener(this);
        jToolBar.add(jbEliminar);
        jbEnviar = new JButton();
        jbEnviar.setIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        jbEnviar.setToolTipText(Strings.A_CORREO);
        jbEnviar.addActionListener(this);
        jToolBar.add(jbEnviar);
        jbNuevoAdmin = new JButton();
        jbNuevoAdmin.setIcon(new ImageIcon(getClass().getResource("/images/admins.png")));
        jbNuevoAdmin.setToolTipText(Strings.NUEVO_ADMIN);
        jbNuevoAdmin.addActionListener(this);
        jToolBar.add(jbNuevoAdmin);
        jbEstadisticas = new JButton();
        jbEstadisticas.setIcon(new ImageIcon(getClass().getResource("/images/graficos.png")));
        jbEstadisticas.setToolTipText(Strings.ESTADISTICAS);
        jbEstadisticas.addActionListener(this);
        jToolBar.add(jbEstadisticas);
        jbToPdf = new JButton();
        jbToPdf.setIcon(new ImageIcon(getClass().getResource("/images/pdf.png")));
        jbToPdf.setToolTipText(Strings.A_PDF);
        jbToPdf.addActionListener(this);
        jToolBar.add(jbToPdf);

        jToolBar.setFloatable(false);
        jToolBar.setRollover(true);
        this.add(jToolBar, BorderLayout.NORTH);

        jPanelIzquierda = new JPanel();
        jPanelIzquierda.setLayout(new BorderLayout());
        this.add(jPanelIzquierda, BorderLayout.WEST);

        jpDatos = new JPanel();
        bordeDatos = BorderFactory.createTitledBorder(null, Strings.BORDE_DATOS, TitledBorder.CENTER, TitledBorder.CENTER);
        jpDatos.setBorder(bordeDatos);
        jpDatos.setLayout(new BoxLayout(jpDatos, BoxLayout.Y_AXIS));

        jPanelIzquierda.add(jpDatos, BorderLayout.NORTH);

        jlNombre = new JLabel("Nombre");
        jpDatos.add(jlNombre);
        jlNickname = new JLabel("Nickname");
        jpDatos.add(jlNickname);
        jlCorreo = new JLabel("Correo electronico");
        jpDatos.add(jlCorreo);

        jpEntrada = new JPanel();
        bandejaDeEntrada = BorderFactory.createTitledBorder(null, Strings.BORDE_BANDEJA, TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        jpEntrada.setLayout(new BoxLayout(jpEntrada, BoxLayout.Y_AXIS));

        jlListaEncuestas = new JLabel("  Lista de encuestas  ");
        jpEntrada.add(jlListaEncuestas);

        jPanelIzquierda.add(jpEntrada, BorderLayout.CENTER);

//        jpContenido = new JPanel();
//        jpContenido.setBackground(Color.GRAY);
//        jpContenido.setLayout(new BorderLayout());
//        this.add(jpContenido, BorderLayout.CENTER);

        this.escritorio.add(this);
        this.setVisible(true);
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbNuevo) {

            NuevaEncuesta jifNuevaEncuesta = new NuevaEncuesta();
            jifNuevaEncuesta.ocultarBarraTitulo();
            this.add(jifNuevaEncuesta, BorderLayout.CENTER);
            updateUI();

        } else if(e.getSource() == jbNuevoAdmin){ 
            
            JIFCreaAdministrador creaAdmin = new JIFCreaAdministrador();
            creaAdmin.ocultarBarraTitulo();
            this.add(creaAdmin, BorderLayout.CENTER);
            updateUI();
        } else if(e.getSource() == jbEnviar){
            // enviar la listade usuarios y mis encuestas como parametro
            JIFEnviarCorreos enviarCorreos = new JIFEnviarCorreos();
            enviarCorreos.ocultarBarraTitulo();
            this.add(enviarCorreos, BorderLayout.CENTER);
            updateUI();
        } else if(e.getSource() == jbEliminar){
            JIFEliminaEncuesta jifElimina = new JIFEliminaEncuesta();
            jifElimina.ocultarBarraTitulo();
            this.add(jifElimina, BorderLayout.CENTER);
            updateUI();
        }
    }

    @Override
    public void run() {
        init();
    }

}
