package gui;

import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import util.Strings;

/**
 * @author adriansb3105
 */
public class PantallaPrincipal extends JFrame{
    
    public static JDesktopPane escritorio;
    private Login login;
    
    public PantallaPrincipal() {
        super(Strings.PANTALLAPRINCIPALTITULO);
        
        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        init();
    }

    private void init(){
        
        PantallaPrincipal.escritorio = new JDesktopPane();
        this.login = new Login();
        
        PantallaPrincipal.escritorio.setBounds(0, 10, this.getWidth(), this.getHeight());
        escritorio.setBackground(Color.red);
        this.login.setLocationRelativeTo(null);
        this.login.setVisible(true);
    }
    
}
