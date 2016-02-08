/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Daniel
 */
public class JIFEncuestrado extends JInternalFrame {

    private JPanel jpArriba;
    private JPanel jpIzquierda; 
    private JPanel jpDerecha; 
    private JPanel jpEntrada;
    private JPanel jpHistorial;
    private JLabel jlNombre;
    private JLabel jlNickname; 
    private JLabel jlCorreo;
    private JLabel jlLIsta;
    private JLabel jlBandeja;
    private JLabel jlHistorial;
    private Border tituloArriba;
    private Border bandejaDeEntrada;
    private Border historial;
    

    public JIFEncuestrado() {

        super("Bienvenido", true, true, true);
        this.setLayout(new BorderLayout());
        
        initComponents();

        this.setVisible(true);
        this.setSize(800, 600);

    }

    private void initComponents() {

        jpArriba = new JPanel();
        tituloArriba = BorderFactory.createTitledBorder(null, "Datos de usuario", TitledBorder.CENTER, TitledBorder.CENTER);
        jpArriba.setBorder(tituloArriba);
        jpArriba.setLayout(new BoxLayout(jpArriba, BoxLayout.Y_AXIS));
        this.add(jpArriba, BorderLayout.NORTH);

        jlNombre = new JLabel("Nombre");
        jpArriba.add(jlNombre);
        jlNickname = new JLabel("Nickname");
        jpArriba.add(jlNickname);
        jlCorreo = new JLabel("Correo electronico");
        jpArriba.add(jlCorreo);
        jlLIsta = new JLabel("Lista"); 
        jpArriba.add(jlLIsta);

        jpIzquierda = new JPanel();
        jpIzquierda.setBackground(Color.blue);
        jpIzquierda.setLayout(new BoxLayout(jpIzquierda, BoxLayout.Y_AXIS));
        this.add(jpIzquierda, BorderLayout.WEST);

        jpEntrada = new JPanel();
        bandejaDeEntrada = BorderFactory.createTitledBorder(null, "Bandeja de entrada", TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        jlBandeja = new JLabel("  Lista de encuestas  ");
        jpEntrada.add(jlBandeja);
        jpIzquierda.add(jpEntrada);
        
        jpHistorial = new JPanel();
        historial = BorderFactory.createTitledBorder(null, "Historial", TitledBorder.CENTER, TitledBorder.CENTER);
        jpHistorial.setBorder(historial);
        jpIzquierda.add(jpHistorial);

        jpDerecha = new JPanel();
        jpDerecha.setBackground(Color.GRAY);
        this.add(jpDerecha, BorderLayout.CENTER);

    }

}
