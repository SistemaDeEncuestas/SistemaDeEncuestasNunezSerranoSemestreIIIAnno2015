package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import util.Strings;

/**
 * @author adriansb3105
 */
public class RegistroDeEncuestado extends JDialog implements ActionListener{

    private JPanel panel;
    private JLabel jlNombre;
    private JLabel jlNombreUsuario;
    private JLabel jlCorreo;
    private JLabel jlContrasenia;
    private JLabel jlContraseniaVerificar;
    private JTextField jtfNombre;
    private JTextField jtfNombreUsuario;
    private JTextField jtfCorreo;
    private JPasswordField jpfContrasenia;
    private JPasswordField jpfContraseniaVerificar;
    private JButton jbRegistro;
    private JDialog login;
    private GridBagConstraints grid;
    
    public RegistroDeEncuestado(JDialog login) {
        super();

        this.setTitle(Strings.REGISTROTITULO);
        this.setSize(new Dimension(400, 600));
        this.setResizable(false);
        this.setModal(true);

        init();
        
        this.login = login;
        this.login.dispose();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {

        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.grid.gridwidth = 1;
        this.grid.fill = GridBagConstraints.HORIZONTAL;

        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlNombre = new JLabel(Strings.REGISTRONOMBRE, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.panel.add(this.jlNombre, grid);
        
        this.grid.insets = new Insets(10, 20, 20, 20);
        this.jtfNombre = new JTextField();
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.panel.add(this.jtfNombre, grid);
        
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlNombreUsuario = new JLabel(Strings.REGISTRONOMBREUSUARIO, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.panel.add(this.jlNombreUsuario, grid);
        
        this.grid.insets = new Insets(10, 20, 20, 20);
        this.jtfNombreUsuario = new JTextField();
        this.grid.gridx = 0;
        this.grid.gridy = 3;
        this.panel.add(this.jtfNombreUsuario, grid);
        
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlCorreo = new JLabel(Strings.REGISTROCORREO, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 4;
        this.panel.add(this.jlCorreo, grid);
        
        this.grid.insets = new Insets(10, 20, 20, 20);
        this.jtfCorreo = new JTextField();
        this.grid.gridx = 0;
        this.grid.gridy = 5;
        this.panel.add(this.jtfCorreo, grid);
        
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlContrasenia = new JLabel(Strings.REGISTROCONTRASENIA, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 6;
        this.panel.add(this.jlContrasenia, grid);
        
        this.grid.insets = new Insets(10, 20, 20, 20);
        this.jpfContrasenia = new JPasswordField();
        this.grid.gridx = 0;
        this.grid.gridy = 7;
        this.panel.add(this.jpfContrasenia, grid);
        
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jlContraseniaVerificar = new JLabel(Strings.REGISTROVERIFICACONTRASENIA, SwingConstants.CENTER);
        this.grid.gridx = 0;
        this.grid.gridy = 8;
        this.panel.add(this.jlContraseniaVerificar, grid);
        
        this.grid.insets = new Insets(10, 20, 20, 20);
        this.jpfContraseniaVerificar = new JPasswordField();
        this.grid.gridx = 0;
        this.grid.gridy = 9;
        this.panel.add(this.jpfContraseniaVerificar, grid);
        
        this.grid.insets = new Insets(20, 20, 10, 20);
        this.jbRegistro = new JButton(Strings.REGISTRAR);
        this.grid.gridx = 0;
        this.grid.gridy = 10;
        this.jbRegistro.addActionListener(this);
        this.panel.add(this.jbRegistro, grid);
        
        this.add(this.panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.jbRegistro) {
            Login loginUsuario = new Login();
            this.dispose();
            loginUsuario.setLocationRelativeTo(null);
            loginUsuario.setVisible(true);
        }
    }
}
