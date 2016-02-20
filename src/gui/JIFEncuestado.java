package gui;

import domain.Encuestado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import logic.Cliente;
import util.Strings;

/**
 * Interfaz para el control de las funciones básicas de un usuario encuestado
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

    /**
     *
     * @param escritorio el componente donde será agregado el internal
     * @param encuestado el encuestado actual que tiene su sesión iniciada
     */
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
        jpArriba.setLayout(new BorderLayout());
        this.add(jpArriba, BorderLayout.NORTH);

        JPanel jpDatos = new JPanel();
        jpDatos.setLayout(new BoxLayout(jpDatos, BoxLayout.Y_AXIS));
        jpArriba.add(jpDatos, BorderLayout.NORTH);

        JPanel jpBotones = new JPanel();
        jpArriba.add(jpBotones, BorderLayout.WEST);

        jlNombre = new JLabel(this.encuestado.getNombre());
        jpDatos.add(jlNombre);
        jlNickname = new JLabel(this.encuestado.getNickname());
        jpDatos.add(jlNickname);
        jlCorreo = new JLabel(this.encuestado.getCorreoElectronico());
        jpDatos.add(jlCorreo);
        jlLIsta = new JLabel("Encuestas (" + this.encuestado.getListaEncuestas().size() + ")");
        jpDatos.add(jlLIsta);

        jbConfiguracion = new JButton();
        jbConfiguracion.setIcon(new ImageIcon(getClass().getResource("/images/settings.png")));
        jbConfiguracion.setToolTipText(Strings.CAMBIAR_CONTRASENNA);
        jbConfiguracion.addActionListener(this);
        jpBotones.add(jbConfiguracion);

        jbCerrarSesion = new JButton();
        jbCerrarSesion.setIcon(new ImageIcon(getClass().getResource("/images/close2.png")));
        jbCerrarSesion.setToolTipText(Strings.CERRAR_SESION);
        jbCerrarSesion.addActionListener(this);
        jpBotones.add(jbCerrarSesion);

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
        jListEncuestasCompartidas.setBackground(new java.awt.Color(205, 205, 205));
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
        /*Abrir una encuesta para llenar*/
        if (e.getSource() == jbAbrir) {

            Object objetoActual = jListEncuestasCompartidas.getSelectedValue();
            if (objetoActual != null) {
                String encuestaActual = objetoActual.toString();
                Cliente cliente = new Cliente(Strings.PETICION_SOLICITA_ENCUESTA, encuestaActual, this, this.encuestado);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Por favor, escoja una encuesta de su bandeja de entrada",
                        "Problema al abrir la encuesta", JOptionPane.INFORMATION_MESSAGE);
            }

            /*Cambiar la contraseña del usuario*/
        } else if (e.getSource() == jbConfiguracion) {
            JIFCambioContrasennaEncuestado jifCambio = new JIFCambioContrasennaEncuestado(this.encuestado);
            jifCambio.ocultarBarraTitulo();
            this.add(jifCambio, BorderLayout.CENTER);
        } else if (e.getSource() == jbCerrarSesion) {
            Cliente cliente = new Cliente(Strings.PETICION_CERRAR_SESION, this.encuestado.getNickname());
            this.dispose();
        }

    }

}
