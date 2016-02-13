package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author adriansb3105
 */
public class Cliente extends Thread {

    private int puerto;
    private boolean flag;

    public Cliente(int puerto) {
        super("Client thread");
        this.puerto = puerto;

    }

    @Override
    public void run() {
       
//            while (true) {
                try {
                    
                    InetAddress direccionIP = InetAddress.getByName("127.0.0.1");
                    
                    Socket socket = new Socket(direccionIP, this.puerto);
                    
                    PrintStream enviar = new PrintStream(socket.getOutputStream());
                    
                    BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
                    
                    
                    Scanner escaner = new Scanner(System.in);
                    System.out.println("ingrese texto");
                    String texto = escaner.nextLine();
                    enviar.println(texto);
                    
                    
                    System.out.println("El servidor me envia: " + recibir.readLine());

                    

                    //Protocolo de comunicacion!!!!!!!!!!!!!
                    String respuestaServidor = recibir.readLine();
                    System.out.println("Adrian dice: " + respuestaServidor);
                    if (respuestaServidor.equals("Listo")) {
                        Element elementoEstudiante = new Element("estudiante");
                        elementoEstudiante.setAttribute("cedula", "cedula1");

                        Element elementoNombre = new Element("nombre");
                        elementoNombre.addContent("nombre1");

                        Element elementoEdad = new Element("edad");
                        elementoEdad.addContent("10");

                        elementoEstudiante.addContent(elementoNombre);
                        elementoEstudiante.addContent(elementoEdad);

                        XMLOutputter ouput = new XMLOutputter(Format.getCompactFormat());
                        String xmlStringEstudianteElemento = ouput.outputString(elementoEstudiante);

                        xmlStringEstudianteElemento = xmlStringEstudianteElemento.replace("\n", "");
                        enviar.println(xmlStringEstudianteElemento);

                    }
                    socket.close();

                } catch (UnknownHostException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    
                }catch(ConnectException ess){
                    System.out.println("SERVIDOR EN MANTENIMIENTO");
                    System.exit(0);
                } 
                catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                } 

//            } 

//            }

        }

    }

