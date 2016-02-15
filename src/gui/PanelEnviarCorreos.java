/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Administrador;
import domain.Encuesta;
import domain.Encuestado;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class PanelEnviarCorreos extends JPanel implements ActionListener {

    private JLabel jlUsuarios;
    private JComboBox jComboUsuarios;
    private JButton jbAgregar;
    private JList jListaSeleccionados;
    private JButton jbEliminar;
    private JLabel jlEncuesta;
    private JComboBox jComboEncuestas;
    private JButton jbEnviar;
    private List<Encuestado> listaSeleccionados;
    private String nombreEncuesta;
    private GridBagConstraints gridBag;
    private Encuestado[] encuestados;
    private Administrador administrador;
//
//    public PanelEnviarCorreos(Administrador administrador, Encuestado[] encuestados) {
//
//        super();
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

    public PanelEnviarCorreos() {

        super();
        this.listaSeleccionados = new ArrayList<>();

        this.setLayout(new GridBagLayout());
        this.gridBag = new GridBagConstraints();
//        this.gridBag.gridwidth = 2;
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;

        initComponents();
        this.setVisible(true);

    }

    public void initComponents() {

        this.gridBag.insets = new Insets(50, 30, 0, 30);
        this.jlUsuarios = new JLabel(Strings.LABEL_ENCUESTADOS, SwingConstants.CENTER);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 0;
        this.add(this.jlUsuarios, gridBag);

        this.jComboUsuarios = new JComboBox();
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
         this.gridBag.weightx = 2;
        llenaComboBoxUsuarios();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 1;
        this.add(this.jComboUsuarios, gridBag);

        this.jbAgregar = new JButton(Strings.BOTON_ANNADIR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbAgregar.addActionListener(this);
        this.gridBag.gridx = 1;
        this.gridBag.gridy = 1;
        this.add(this.jbAgregar, gridBag);

        this.jListaSeleccionados = new JList();
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
         this.gridBag.weightx = 2;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(this.jListaSeleccionados, gridBag);

        this.jbEliminar = new JButton(Strings.BOTON_ELIMINAR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbEliminar.addActionListener(this);
        this.gridBag.gridx = 1;
        this.gridBag.gridy = 2;
        this.add(this.jbEliminar, gridBag);

        this.jlEncuesta = new JLabel(Strings.LABEL_ENCUESTA, SwingConstants.CENTER);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(this.jlEncuesta, gridBag);

        this.jComboEncuestas = new JComboBox();
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        llenaComboBoxEncuestas();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 4;
        this.add(this.jComboEncuestas, gridBag);

        this.jbEnviar = new JButton(Strings.BOTON_ENVIAR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbEnviar.addActionListener(this);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 5;
        this.add(this.jbEnviar, gridBag);

    }

    private void llenaComboBoxUsuarios() {

    }

    public void llenaComboBoxEncuestas() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
