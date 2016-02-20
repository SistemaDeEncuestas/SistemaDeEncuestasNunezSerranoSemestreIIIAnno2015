/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Administrador;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import logic.Cliente;
import util.Strings;

/**
 * Internal que me permite establecer la conexion con el servidor para poder
 * enviar encuestas a correos electrónicos
 *
 * @author Daniel
 */
public class JIFEnviarCorreos extends JInternalFrame implements ActionListener {

    private JLabel jlUsuarios;
    private JTextField jtUsuarios;
    private JButton jbAgregar;
    private JList jListaSeleccionados;
    private DefaultListModel listModel;
    private JButton jbEliminar;
    private JButton jbCancelar;
    private JLabel jlEncuesta;
    private JComboBox jComboEncuestas;
    private JButton jbEnviar;
    private List<String> listaSeleccionados;
    private GridBagConstraints gridBag;
    private Administrador administrador;
    private JComponent barra;
    private Dimension dimensionBarra;

    /**
     * Internal que me permite establecer la conexion con el servidor para poder
     * enviar encuestas a correos electrónicos
     *
     * @param administrador
     */
    public JIFEnviarCorreos(Administrador administrador) {

        super();
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.listaSeleccionados = new ArrayList<>();
        this.administrador = administrador;
        this.setLayout(new GridBagLayout());
        this.gridBag = new GridBagConstraints();
        this.gridBag.gridwidth = 2;
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;

        initComponents();
        this.setVisible(true);

    }
    
    /**
     * Metodo que me permite ocultar la barra del titulo del internal, para
     * evitar que este se mueva
     */
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
        this.gridBag.ipadx = 0;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 0;
        this.add(this.jlUsuarios, gridBag);

        this.jtUsuarios = new JTextField();
        this.gridBag.fill = GridBagConstraints.CENTER;
        this.gridBag.weightx = 2;
        this.gridBag.ipadx = 120;

        this.gridBag.gridx = 0;
        this.gridBag.gridy = 1;
        this.add(this.jtUsuarios, gridBag);

        this.jbAgregar = new JButton(Strings.BOTON_ANNADIR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbAgregar.addActionListener(this);
        this.gridBag.weightx = 1;
        this.gridBag.ipadx = 0;
        this.gridBag.gridx = 2;
        this.gridBag.gridy = 1;
        this.add(this.jbAgregar, gridBag);

        this.listModel = new DefaultListModel();
        this.jListaSeleccionados = new JList(listModel);
        this.jListaSeleccionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.weightx = 2;
        this.gridBag.ipadx = 0;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(this.jListaSeleccionados, gridBag);

        this.jbEliminar = new JButton(Strings.BOTON_ELIMINAR);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.jbEliminar.addActionListener(this);
        this.gridBag.weightx = 1;
        this.gridBag.ipadx = 0;
        this.gridBag.gridx = 2;
        this.gridBag.gridy = 2;
        this.add(this.jbEliminar, gridBag);
        if (this.listModel.isEmpty()) {
            this.jbEliminar.setEnabled(false);
        }

        this.jlEncuesta = new JLabel(Strings.LABEL_ENCUESTA, SwingConstants.CENTER);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.ipadx = 0;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(this.jlEncuesta, gridBag);

        this.jComboEncuestas = new JComboBox();
        this.gridBag.fill = GridBagConstraints.CENTER;
        llenaComboBoxEncuestas();
        this.gridBag.ipadx = 120;
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

    public void llenaComboBoxEncuestas() {

        for (int i = 0; i < this.administrador.getEncuestasCreadas().size(); i++) {
            this.jComboEncuestas.addItem(this.administrador.getEncuestasCreadas().get(i));

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbCancelar) {
            this.dispose();
            updateUI();
        } else if (e.getSource() == jbAgregar) {

            String userSeleccionado = this.jtUsuarios.getText();
            if (!userSeleccionado.isEmpty()) {
                if (userSeleccionado.contains("@")) {
                    this.listModel.addElement(userSeleccionado);
                    this.listaSeleccionados.add(userSeleccionado);
                    this.jtUsuarios.setText("");

                    if (!this.listModel.isEmpty()) {
                        this.jbEliminar.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "inserte un correo electrónico válido",
                            Strings.ERROR, JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "No debe dejar el espacio en blanco",
                        Strings.ERROR, JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == jbEliminar) {

            this.listModel.removeElement(this.jListaSeleccionados.getSelectedValue());
            this.listaSeleccionados.remove(this.jListaSeleccionados.getSelectedValue().toString());

            if (this.listModel.isEmpty()) {
                this.jbEliminar.setEnabled(false);
            }

        } else if (e.getSource() == jbEnviar) {
            /*llamo a cliente para realizar la conexion con el servidor y enviar los correos*/
            Cliente cliente = new Cliente(Strings.PETICION_ENVIAR_CORREO, this.listaSeleccionados,
                    this.jComboEncuestas.getSelectedItem().toString());
        }

    }

}
