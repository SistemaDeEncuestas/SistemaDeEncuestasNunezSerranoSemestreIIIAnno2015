/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFEliminaEncuesta extends JInternalFrame implements ActionListener {

    private JLabel jlMensaje;
    private JLabel jlAviso;
    private JComboBox jcEncuestas;
    private JButton jbEliminar;
    private JComponent barra;
    private Dimension dimensionBarra;

    public JIFEliminaEncuesta() {
        super();

        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.setLayout(new FlowLayout());

        initComponents();
        this.setVisible(true);

    }

    public void ocultarBarraTitulo() {
        barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimensionBarra = barra.getPreferredSize();
        barra.setSize(0, 0);
        barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void initComponents() {
        
        this.jlMensaje = new JLabel(Strings.LABEL_ESCOGE_ENCUESTA);
        
        this.add(jlMensaje);
        
        this.jcEncuestas = new JComboBox();
        llenaCombo();
        
        this.add(jcEncuestas);
        
        this.jlAviso = new JLabel(Strings.LABEL_AVISO);
        
        this.add(jlAviso);
        
        this.jbEliminar = new JButton(Strings.BOTON_ELIMINAR);
        this.jbEliminar.addActionListener(this);
        
        this.add(jbEliminar);
        
    }
    
    public void llenaCombo(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
