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
import javax.swing.JList;
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
public class JIFAdministrador extends JInternalFrame implements ActionListener {

    private Administrador administrador;
    private JPanel jPanelIzquierda;
    private JPanel jpDatos;
    private JPanel jpEntrada;
    private JButton jbEncuestas;
    private JButton jbNuevo;
    private JButton jbEditar;
    private JButton jbEliminar;
    private JButton jbEnviar;
    private JButton jbEnviarACorreo;
    private JButton jbNuevoAdmin;
    private JButton jbEstadisticas;
    private Border bordeDatos;
    private Border bandejaDeEntrada;
    private JLabel jlNombre;
    private JLabel jlNickname;
    private JLabel jlCorreo;
    private JToolBar jToolBar;
    private JScrollPane scroll;
    private JDesktopPane escritorio;
    private JList jListEncuestas;

    public JIFAdministrador(JDesktopPane escritorio, Administrador administrador) {
        super("Bienvenido " + administrador.getNickname(), true, true, true);
        this.setLayout(new BorderLayout());
        this.administrador = administrador;
        this.setBackground(Color.GRAY);
        this.setSize(850, 600);
        this.escritorio = escritorio;
        init();
        this.setVisible(true);
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
        jbEnviar.setIcon(new ImageIcon(getClass().getResource("/images/enviar.png")));
        jbEnviar.setToolTipText(Strings.ENVIAR);
        jbEnviar.addActionListener(this);
        jToolBar.add(jbEnviar);
        jbEnviarACorreo = new JButton();
        jbEnviarACorreo.setIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        jbEnviarACorreo.setToolTipText(Strings.A_CORREO);
        jbEnviarACorreo.addActionListener(this);
        jToolBar.add(jbEnviarACorreo);
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

        jToolBar.setFloatable(false);
        jToolBar.setRollover(true);
        this.add(jToolBar, BorderLayout.NORTH);

        jPanelIzquierda = new JPanel();
        jPanelIzquierda.setLayout(new BorderLayout());
        jPanelIzquierda.setBackground(new java.awt.Color(192, 192, 192));
        this.add(jPanelIzquierda, BorderLayout.WEST);

        jpDatos = new JPanel();
        bordeDatos = BorderFactory.createTitledBorder(null, Strings.BORDE_DATOS, TitledBorder.CENTER, TitledBorder.CENTER);
        jpDatos.setBorder(bordeDatos);
        jpDatos.setLayout(new BoxLayout(jpDatos, BoxLayout.Y_AXIS));

        jPanelIzquierda.add(jpDatos, BorderLayout.NORTH);

        jlNombre = new JLabel(this.administrador.getNombre());
        jpDatos.add(jlNombre);
        jlNickname = new JLabel(this.administrador.getNickname());
        jpDatos.add(jlNickname);
        jlCorreo = new JLabel(this.administrador.getCorreoElectronico());
        jpDatos.add(jlCorreo);

        jpEntrada = new JPanel();
        bandejaDeEntrada = BorderFactory.createTitledBorder(null, Strings.BORDE_BANDEJA_ADMIN, TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        jpEntrada.setLayout(new BoxLayout(jpEntrada, BoxLayout.Y_AXIS));

        jListEncuestas = new JList();
        jListEncuestas.setBackground(new java.awt.Color(192, 192, 192));
        jListEncuestas.setListData(llenaLista());
        jpEntrada.add(jListEncuestas);

        jPanelIzquierda.add(jpEntrada, BorderLayout.CENTER);

        this.escritorio.add(this);

    }

    public String[] llenaLista() {
        String[] encuestas = new String[this.administrador.getEncuestasCreadas().size()];

        for (int i = 0; i < this.administrador.getEncuestasCreadas().size(); i++) {
            encuestas[i] = this.administrador.getEncuestasCreadas().get(i);

        }

        return encuestas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbEncuestas) {

            JIFEscogeEncuesta jifEscogeEncuesta = new JIFEscogeEncuesta(this, this.administrador.getEncuestasCreadas(), false);
            jifEscogeEncuesta.ocultarBarraTitulo();
            this.add(jifEscogeEncuesta, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbNuevo) {

            JIFNuevaEncuesta jifNuevaEncuesta = new JIFNuevaEncuesta(this.administrador.getNickname());
            jifNuevaEncuesta.ocultarBarraTitulo();
            this.add(jifNuevaEncuesta, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbEditar) {

            JIFEscogeEncuesta jifEscoge = new JIFEscogeEncuesta(this, this.administrador.getEncuestasCreadas(), true);
            jifEscoge.ocultarBarraTitulo();
            this.add(jifEscoge, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbEliminar) {

            JIFEliminaEncuesta jifElimina = new JIFEliminaEncuesta(this.administrador.getEncuestasCreadas());
            jifElimina.ocultarBarraTitulo();
            this.add(jifElimina, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbEnviar) {
            JIFEnviarEncuesta enviarEncuesta = new JIFEnviarEncuesta();
            enviarEncuesta.ocultarBarraTitulo();
            this.add(enviarEncuesta, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbNuevoAdmin) {

            JIFCreaAdministrador creaAdmin = new JIFCreaAdministrador();
            creaAdmin.ocultarBarraTitulo();
            this.add(creaAdmin, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbEnviarACorreo) {

            // enviar la listade usuarios y mis encuestas como parametro
            JIFEnviarCorreos enviarCorreos = new JIFEnviarCorreos();
            enviarCorreos.ocultarBarraTitulo();
            this.add(enviarCorreos, BorderLayout.CENTER);
            updateUI();

        } else if (e.getSource() == jbEstadisticas) {
            JIFEstadisticas estadisticas = new JIFEstadisticas(this.administrador.getEncuestasCreadas(), this.administrador.getNickname());
            estadisticas.ocultarBarraTitulo();
            this.add(estadisticas, BorderLayout.CENTER);
            updateUI();
        }
    }

}
