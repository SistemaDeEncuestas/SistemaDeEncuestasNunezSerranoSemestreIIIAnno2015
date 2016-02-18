/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Encuestado;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFEncuestado extends JInternalFrame {

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
    private Encuestado encuestado;
    private JList jListEncuestasCompartidas;
    private JDesktopPane escritorio;
    

    public JIFEncuestado(JDesktopPane escritorio,Encuestado encuestado) {

        super("Bienvenido "+encuestado.getNickname(), true, true, true);
        this.setLayout(new BorderLayout());
        this.escritorio = escritorio;
        this.encuestado = encuestado;
        initComponents();
         this.setSize(800, 600);
         this.escritorio.add(this);
        this.setVisible(true);
       

    }

    private void initComponents() {

        jpArriba = new JPanel();
        tituloArriba = BorderFactory.createTitledBorder(null, Strings.BORDE_DATOS, TitledBorder.CENTER, TitledBorder.CENTER);
        jpArriba.setBorder(tituloArriba);
        jpArriba.setLayout(new BoxLayout(jpArriba, BoxLayout.Y_AXIS));
        this.add(jpArriba, BorderLayout.NORTH);

        jlNombre = new JLabel(this.encuestado.getNombre());
        jpArriba.add(jlNombre);
        jlNickname = new JLabel(this.encuestado.getNickname());
        jpArriba.add(jlNickname);
        jlCorreo = new JLabel(this.encuestado.getCorreoElectronico());
        jpArriba.add(jlCorreo);
        jlLIsta = new JLabel("Encuestas ("+ this.encuestado.getListaEncuestas().size()+")"); 
        jpArriba.add(jlLIsta);

        jpIzquierda = new JPanel();
        jpIzquierda.setBackground(Color.blue);
        jpIzquierda.setLayout(new BoxLayout(jpIzquierda, BoxLayout.Y_AXIS));
        this.add(jpIzquierda, BorderLayout.WEST);

        jpEntrada = new JPanel();
        bandejaDeEntrada = BorderFactory.createTitledBorder(null,Strings.BORDE_BANDEJA_USUARIO, TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        jlBandeja = new JLabel("  Lista de encuestas  ");
        jpEntrada.add(jlBandeja);
       
        
        
        jListEncuestasCompartidas = new JList();
        jListEncuestasCompartidas.setBackground(new java.awt.Color(192, 192, 192));
        jListEncuestasCompartidas.setListData(llenaLista());
        jpEntrada.add(jListEncuestasCompartidas);
        jpIzquierda.add(jpEntrada);
        
        jpHistorial = new JPanel();
        historial = BorderFactory.createTitledBorder(null, Strings.BORDE_HISTORIAL, TitledBorder.CENTER, TitledBorder.CENTER);
        jpHistorial.setBorder(historial);
        jpIzquierda.add(jpHistorial);

        jpDerecha = new JPanel();
        jpDerecha.setBackground(Color.GRAY);
        this.add(jpDerecha, BorderLayout.CENTER);

    }
    public String[] llenaLista() {
        String[] encuestas = new String[this.encuestado.getListaEncuestas().size()];

        for (int i = 0; i < this.encuestado.getListaEncuestas().size(); i++) {
            encuestas[i] = this.encuestado.getListaEncuestas().get(i);

        }

        return encuestas;
    }

}
