package main;

import domain.Administrador;
import domain.Encuesta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logic.Cliente;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import util.Strings;

/**
 *
 * @author adriansb3105
 */
public class PruebaCliente extends javax.swing.JFrame {

    
    private String peticion = Strings.PETICION_LOGIN_ADMIN;
    private String nick = "adriansb3105";
    private String contrasenna = "Serranobrenes";
    private Administrador administrador;
    
    public PruebaCliente() {
//        initComponents();
        
        try {
            InetAddress direccionIP = InetAddress.getByName("127.0.0.1");
            Socket socket = new Socket(direccionIP, 5700);
            PrintStream enviar = new PrintStream(socket.getOutputStream());
            BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /*Comienza el protocolo de comunicacion*/
            switch (this.peticion) {
                case Strings.PETICION_LOGIN_ADMIN:
                    enviar.println(this.peticion);
                    enviar.println(this.nick);
                    enviar.println(this.contrasenna);
                    String adminXML = recibir.readLine();
                    if (!adminXML.equals("null")) {
                        this.administrador = recibirPeticionLoginAdmin(adminXML);
//                        JIFAdministrador jifAdministrador = new JIFAdministrador(this.administrador);
                        
                        System.out.println(this.administrador);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
            recibir.close();

            socket.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ConnectException ce) {
            System.out.println("SERVIDOR EN MANTENIMIENTO");
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PruebaCliente();
//            }
//        });
//    }

    public Administrador recibirPeticionLoginAdmin(String adminXML) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            StringReader stringReader = new StringReader(adminXML);
            Document doc = saxBuilder.build(stringReader);
            Element rootAdmin = doc.getRootElement();
            
            Administrador admin = new Administrador(rootAdmin.getAttributeValue("nickname"),
                                                            rootAdmin.getChildText("nombre"),
                                                            rootAdmin.getChildText("contrasenna"),
                                                            rootAdmin.getChildText("correo"));
            admin.setPrimeraVez(Boolean.getBoolean(rootAdmin.getChildText("primeraVez")));
//            List<Encuesta> listaNombres = new ArrayList<>();
            List<String> listaEncuestas = new ArrayList<>();
            
//            System.out.println("Tam encuestas: "+rootAdmin.getChild("encuestas").getContentSize());
            
//            List hijos = rootAdmin.getChild("encuestas").getChildren();
            
            for (int i = 0; i < rootAdmin.getChild("encuestas").getChildren().size(); i++) {
                listaEncuestas.add(rootAdmin.getChild("encuestas").getChild("encuesta"+i).getValue());
            }
            
            admin.setEncuestasCreadas(listaEncuestas);
            
            return admin;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}