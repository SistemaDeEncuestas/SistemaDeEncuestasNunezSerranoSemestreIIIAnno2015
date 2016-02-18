package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logic.Cliente;
import security.Encriptar;
import util.Strings;

/**
 * @author adriansb3105
 */
public class Login extends JDialog implements ActionListener {

    private JTabbedPane panelDePestanas;
    private JPanel panelAdmin;
    private JPanel panelEncuestado;
    private JLabel jlNickAdmin;
    private JLabel jlContraseniaAdmin;
    private JLabel jlNickEncuestado;
    private JLabel jlContraseniaEncuestado;
    private JTextField jtfNickAdmin;
    private JTextField jtfNickEncuestado;
    private JPasswordField jpfContraseniaAdmin;
    private JPasswordField jpfContraseniaEncuestado;
    private JButton jbIngresoAdmin;
    private JButton jbIngresoEncuestado;
    private JButton jbRegistroEncuestado;
    private GridBagConstraints grid;
    private JDesktopPane escritorio;

    public Login(JDesktopPane escritorio) {
        super();
        this.escritorio = escritorio;
        this.setTitle(Strings.LOGINTITULO);
        this.setSize(new Dimension(400, 400));
        this.setResizable(false);
        this.setModal(true);
        init();

    }

    private void init() {

        this.panelDePestanas = new JTabbedPane();
        this.panelDePestanas.setBounds(0, 0, 400, 400);
        this.panelAdmin = new JPanel();
        this.panelAdmin.setLayout(new GridBagLayout());
        this.panelEncuestado = new JPanel();
        this.panelEncuestado.setLayout(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.grid.gridwidth = 1;
        this.grid.fill = GridBagConstraints.HORIZONTAL;

        /*Componentes del panel de admin*/
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlNickAdmin = new JLabel(Strings.LOGINNICKADMIN, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.panelAdmin.add(this.jlNickAdmin, grid);

        this.grid.insets = new Insets(10, 20, 30, 20);
        this.jtfNickAdmin = new JTextField();
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.panelAdmin.add(this.jtfNickAdmin, grid);

        this.grid.insets = new Insets(30, 20, 10, 20);
        this.jlContraseniaAdmin = new JLabel(Strings.LOGINCONTRASENIA, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.panelAdmin.add(this.jlContraseniaAdmin, grid);

        this.grid.insets = new Insets(10, 20, 30, 20);
        this.jpfContraseniaAdmin = new JPasswordField();
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.panelAdmin.add(this.jpfContraseniaAdmin, grid);

        this.grid.insets = new Insets(30, 20, 10, 20);
        this.jbIngresoAdmin = new JButton(Strings.LOGININGRESAR);
        this.grid.gridx = 0;
        this.grid.gridy = 4;
        this.jbIngresoAdmin.addActionListener(this);
        this.panelAdmin.add(this.jbIngresoAdmin, grid);

        /*Componentes del panel de usuario*/
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlNickEncuestado = new JLabel(Strings.LOGINNICKUSUARIO, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.panelEncuestado.add(this.jlNickEncuestado, grid);

        this.grid.insets = new Insets(10, 20, 30, 20);
        this.jtfNickEncuestado = new JTextField();
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.panelEncuestado.add(this.jtfNickEncuestado, grid);

        this.grid.insets = new Insets(30, 20, 10, 20);
        this.jlContraseniaEncuestado = new JLabel(Strings.LOGINCONTRASENIA, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.panelEncuestado.add(this.jlContraseniaEncuestado, grid);

        this.grid.insets = new Insets(10, 20, 30, 20);
        this.jpfContraseniaEncuestado = new JPasswordField();
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.panelEncuestado.add(this.jpfContraseniaEncuestado, grid);

        this.grid.insets = new Insets(30, 20, 20, 20);
        this.jbIngresoEncuestado = new JButton(Strings.LOGININGRESAR);
        this.grid.gridx = 0;
        this.grid.gridy = 4;
        this.jbIngresoEncuestado.addActionListener(this);
        this.panelEncuestado.add(this.jbIngresoEncuestado, grid);

        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jbRegistroEncuestado = new JButton(Strings.REGISTRAR);
        this.grid.gridx = 0;
        this.grid.gridy = 5;
        this.jbRegistroEncuestado.addActionListener(this);
        this.panelEncuestado.add(this.jbRegistroEncuestado, grid);

        this.panelDePestanas.addTab("Administrador", this.panelAdmin);
        this.panelDePestanas.addTab("Usuario", this.panelEncuestado);
        this.add(this.panelDePestanas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.jbIngresoAdmin) {

            if (!this.jtfNickAdmin.getText().trim().isEmpty() && this.jpfContraseniaAdmin.getPassword().length != 0) {

                String pass = Encriptar.password(this.jpfContraseniaAdmin.getPassword(), Encriptar.SHA256);
                Cliente cliente = new Cliente(this.escritorio, this, Strings.PETICION_LOGIN_ADMIN, this.jtfNickAdmin.getText(), pass);

            } else {
                JOptionPane.showMessageDialog(null, Strings.ERRORCAMPOVACIO, Strings.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == this.jbIngresoEncuestado) {
            if (!this.jtfNickEncuestado.getText().trim().isEmpty() && this.jpfContraseniaEncuestado.getPassword().length != 0) {

                String pass = Encriptar.password(this.jpfContraseniaEncuestado.getPassword(), Encriptar.SHA256);
                Cliente cliente = new Cliente(this.escritorio, this, Strings.PETICION_LOGIN_USER, this.jtfNickEncuestado.getText(), pass);
                
            } else {
                JOptionPane.showMessageDialog(null, Strings.ERRORCAMPOVACIO, Strings.ERROR, JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == this.jbRegistroEncuestado) {

            RegistroDeEncuestado registroDeEncuestado = new RegistroDeEncuestado(this, escritorio);
            registroDeEncuestado.setLocationRelativeTo(null);
            this.dispose();
        }
    }

}
