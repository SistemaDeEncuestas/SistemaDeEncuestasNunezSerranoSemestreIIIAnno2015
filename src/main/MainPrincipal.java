package main;

import data.AdministradorData;
import domain.Administrador;
import gui.PantallaPrincipal;
import java.awt.Frame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdom.JDOMException;

/**
 *
 * @author adriansb3105
 */
public class MainPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

////        JFrame.setDefaultLookAndFeelDecorated(true);
        try {

            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        
//        Administrador a = new Administrador("carlos", "chas", "sss", "carlos@gmail.com");
//        Administrador b = new Administrador("Daniel", "dsasss", "dd", "daniel_nuso@hotmail.com");
//         
//        try {
//            AdministradorData adminData = new AdministradorData("Administrador.xml");
//            adminData.insertar(a);
//            adminData.insertar(b);
//        } catch (JDOMException ex) {
//            Logger.getLogger(MainPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MainPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
}
