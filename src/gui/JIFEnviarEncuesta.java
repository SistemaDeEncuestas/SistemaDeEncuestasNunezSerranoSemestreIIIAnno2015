/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Administrador;
import domain.Encuestado;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFEnviarEncuesta extends JInternalFrame implements ActionListener {

    private JLabel jlUsuarios;
    private JComboBox jComboUsuarios;
    private JButton jbAgregar;
    private JList jListaSeleccionados;
    private JButton jbEliminar;
    private JButton jbCancelar;
    private JLabel jlEncuesta;
    private JComboBox jComboEncuestas;
    private JButton jbEnviar;
    private List<Encuestado> listaSeleccionados;
    private String nombreEncuesta;
    private GridBagConstraints gridBag;
    private Encuestado[] encuestados;
    private Administrador administrador;
    private JComponent barra;
    private Dimension dimensionBarra;
//
//    public JIFEnviarEncuesta (Administrador administrador, Encuestado[] encuestados) {
//
//        super();
//     this.dimensionBarra = null;
//        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
//        this.listaSeleccionados = new ArrayList<>();
//        this.encuestados = encuestados;
//        this.administrador = administrador;
//        this.setLayout(new GridBagLayout());
//        this.gridBag = new GridBagConstraints();
//        this.gridBag.gridwidth = 2;
//        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
//
//        initComponents();
//        this.setVisible(true);
//
//    }

    public JIFEnviarEncuesta() {

        super();
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.listaSeleccionados = new ArrayList<>();

        this.setLayout(new GridBagLayout());
        this.gridBag = new GridBagConstraints();
//        this.gridBag.gridwidth = 2;
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;

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

        this.gridBag.insets = new Insets(50, 10, 0, 10);
        this.jlUsuarios = new JLabel(Strings.LABEL_ENCUESTADOS, SwingConstants.CENTER);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
         this.gridBag.ipadx =0;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 0;
        this.add(this.jlUsuarios, gridBag);

        this.jComboUsuarios = new JComboBox();
        this.gridBag.fill = GridBagConstraints.CENTER;
        this.gridBag.weightx = 2;
        this.gridBag.ipadx =120;
        llenaComboBoxUsuarios();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 1;
        this.add(this.jComboUsuarios, gridBag);

        this.jbAgregar = new JButton(Strings.BOTON_ANNADIR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbAgregar.addActionListener(this);
        this.gridBag.ipadx =0;
        this.gridBag.gridx = 1;
        this.gridBag.gridy = 1;
        this.add(this.jbAgregar, gridBag);

        this.jListaSeleccionados = new JList();
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.weightx = 2;
        this.gridBag.ipadx =0;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(this.jListaSeleccionados, gridBag);

        this.jbEliminar = new JButton(Strings.BOTON_ELIMINAR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbEliminar.addActionListener(this);
        this.gridBag.ipadx =0;
        this.gridBag.gridx = 1;
        this.gridBag.gridy = 2;
        this.add(this.jbEliminar, gridBag);

        this.jlEncuesta = new JLabel(Strings.LABEL_ENCUESTA, SwingConstants.CENTER);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.ipadx =0;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(this.jlEncuesta, gridBag);

        this.jComboEncuestas = new JComboBox();
        this.gridBag.fill = GridBagConstraints.CENTER;
        llenaComboBoxEncuestas();
        this.gridBag.ipadx =120;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 4;
        this.add(this.jComboEncuestas, gridBag);

        this.jbEnviar = new JButton(Strings.BOTON_ENVIAR);
        this.gridBag.fill = GridBagConstraints.NONE;
          this.gridBag.anchor = GridBagConstraints.CENTER;
        this.jbEnviar.addActionListener(this);
        
        this.gridBag.ipadx = 45;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 5;
        this.add(this.jbEnviar, gridBag);
        
        this.jbCancelar = new JButton(Strings.CANCELAR);
        this.gridBag.fill = GridBagConstraints.NONE;
         this.gridBag.anchor = GridBagConstraints.CENTER;
        this.jbCancelar.addActionListener(this);
         this.gridBag.ipadx = 35;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 6;
        this.add(this.jbCancelar, gridBag);
        

    }

    private void llenaComboBoxUsuarios() {

    }

    public void llenaComboBoxEncuestas() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == jbCancelar){
            this.dispose();
            updateUI();
        }
        
    }

}
