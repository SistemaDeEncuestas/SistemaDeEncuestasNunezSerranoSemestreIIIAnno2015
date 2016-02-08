package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import util.Strings;

/**
 * @author adriansb3105
 */
public class PantallaPrincipal extends JFrame implements ActionListener {

    private JDesktopPane escritorio;
    private Login login;
    private JMenuBar jMenuBar;
    private JMenu jmArchivo;
    private JMenuItem jmiInciarSesion;
    private JMenuItem jmiCerrarSesion;
    private JMenuItem jmiRegistrar;

    public PantallaPrincipal() {
        super(Strings.PANTALLAPRINCIPALTITULO);

        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        init();
    }

    private void init() {
        this.escritorio = new JDesktopPane();
        jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);

        this.jmArchivo = new JMenu("Archivo");
        this.jMenuBar.add(jmArchivo);

        this.jmiRegistrar = new JMenuItem("Registra Usuario");
        this.jmiRegistrar.addActionListener(this);
        this.jmArchivo.add(jmiRegistrar);
        this.jmiInciarSesion = new JMenuItem("Iniciar Sesión");
        this.jmiInciarSesion.addActionListener(this);
        this.jmArchivo.add(jmiInciarSesion);
        this.jmiCerrarSesion = new JMenuItem("Cerrar Sesión");
        this.jmiCerrarSesion.addActionListener(this);
        this.jmArchivo.add(jmiCerrarSesion);

        this.login = new Login(this.escritorio);

        this.escritorio.setBounds(0, 10, this.getWidth(), this.getHeight());
//        escritorio.setBackground(Color.GRAY);
        this.login.setLocationRelativeTo(null);

        this.add(escritorio);
        this.login.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmiRegistrar) {
            RegistroDeEncuestado registroDeEncuestado = new RegistroDeEncuestado(login, escritorio);
            registroDeEncuestado.setLocationRelativeTo(null);
        } else if (e.getSource() == jmiInciarSesion) {
            this.login = new Login(this.escritorio);
            this.login.setLocationRelativeTo(null);
            this.login.setVisible(true);

        } else if (e.getSource() == jmiCerrarSesion) {
            //TODO
        }

    }

}
