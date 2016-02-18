package main;

import gui.PantallaPrincipal;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author adriansb3105
 */
public class MainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");


        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();        
        
    }
}
