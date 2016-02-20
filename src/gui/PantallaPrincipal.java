package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import util.Strings;

/**
 *
 * @author adriansb3105
 */
public class PantallaPrincipal extends JFrame implements ActionListener {

    private JDesktopPane escritorio;
    private Login login;
    private JMenuBar jMenuBar;
    private JMenu jmArchivo;
    private JMenuItem jmiInciarSesion;
    private JMenuItem jmiRegistrar;

    public PantallaPrincipal() {
        super(Strings.PANTALLAPRINCIPALTITULO);

        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        init();
    }

    private void init() {
        this.escritorio = new JDesktopPane();
        jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);

        this.jmArchivo = new JMenu(Strings.JMENU_NOMBRE);
        this.jMenuBar.add(jmArchivo);

        this.jmiRegistrar = new JMenuItem(Strings.ITEM_REGISTRARSE);
        this.jmiRegistrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        this.jmiRegistrar.addActionListener(this);
        this.jmArchivo.add(jmiRegistrar);
        this.jmiInciarSesion = new JMenuItem(Strings.ITEM_INICIAR);
        this.jmiInciarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_MASK));
        this.jmiInciarSesion.addActionListener(this);
        this.jmArchivo.add(jmiInciarSesion);

        this.login = new Login(this.escritorio);
        this.escritorio.setBounds(0, 10, this.getWidth(), this.getHeight());
        this.login.setLocationRelativeTo(null);
        this.add(escritorio);
        this.setVisible(true);
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

        }

    }

}
