package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author adriansb3105
 */
public class ServidorInterfaz extends JFrame{

    private JPanel jPanel;
    private JMenuBar jmbBarraMenu;
    private JMenu jmMenu;
    private JMenuItem jmiIniciar;
    private JMenuItem jmiDetener;
    private JLabel jlEstado;

    public ServidorInterfaz() {
        super("Servidor");
        
        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        init();
    }
    
    private void init(){
        
        this.jPanel = new JPanel();
        this.jPanel.setLayout(new BorderLayout());
        
        
        this.jmbBarraMenu = new JMenuBar();
        
        this.jPanel.add(this.jmbBarraMenu);
        
        
        this.add(this.jPanel);
    }
    
}
