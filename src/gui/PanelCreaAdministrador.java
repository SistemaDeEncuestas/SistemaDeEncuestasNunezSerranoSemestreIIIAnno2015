/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Administrador;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class PanelCreaAdministrador extends JPanel implements ActionListener {

    private JLabel jlNombre;
    private JLabel jlNickName;
    private JLabel jlContrasenna;
    private JLabel jlCorreoElectronico;
    private JLabel jlVerificaContrasenna;
    private JTextField jtNombre;
    private JTextField jtNickname;
    private JTextField jtContrasenna;
    private JTextField jtCorreoElectronico;
    private JTextField jtVerificaContrasenna;

    private JButton jbRegistrar;
    private JLabel jlMensaje;
    private GridBagConstraints gridBag;

    public PanelCreaAdministrador() {

        this.setLayout(new GridBagLayout());
        this.gridBag = new GridBagConstraints();
        this.gridBag.gridwidth = 1;
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;

        initComponents();

        this.setSize(850, 600);

        this.setVisible(true);
    }

    public void initComponents() {

        this.gridBag.insets = new Insets(20, 0, 0, 0);
        this.jlNombre = new JLabel(Strings.REGISTRONOMBRE, SwingConstants.CENTER);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 0;
        this.add(this.jlNombre, gridBag);

        this.jtNombre = new JTextField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 1;
        this.add(this.jtNombre, gridBag);

        this.jlNickName = new JLabel(Strings.REGISTRONOMBREUSUARIO, SwingConstants.CENTER);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(this.jlNickName, gridBag);

        this.jtNickname = new JTextField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(this.jtNickname, gridBag);

        this.jlCorreoElectronico = new JLabel(Strings.REGISTROCORREO, SwingConstants.CENTER);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 4;
        this.add(this.jlCorreoElectronico, gridBag);

        this.jtCorreoElectronico = new JTextField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 5;
        this.add(this.jtCorreoElectronico, gridBag);

        this.jlContrasenna = new JLabel(Strings.REGISTRO_CONTRASENIA_TEMPORAL, SwingConstants.CENTER);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 6;
        this.add(this.jlContrasenna, gridBag);

        this.jtContrasenna = new JTextField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 7;
        this.add(this.jtContrasenna, gridBag);

        this.jlVerificaContrasenna = new JLabel(Strings.REGISTROVERIFICACONTRASENIA, SwingConstants.CENTER);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 8;
        this.add(this.jlVerificaContrasenna, gridBag);

        this.jtVerificaContrasenna = new JTextField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 9;
        this.add(this.jtVerificaContrasenna, gridBag);

        this.jbRegistrar = new JButton(Strings.REGISTRAR);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 10;
        this.jbRegistrar.addActionListener(this);
        this.add(this.jbRegistrar, gridBag);

        this.jlMensaje = new JLabel();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 11;
        this.add(this.jlMensaje, gridBag);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbRegistrar) {

            String nombre = jtNombre.getText();
            System.out.println(nombre);
            String nombreUsuario = jtNickname.getText();
            String contrasenna = jtContrasenna.getText();
            String verificaContrasenna = jtVerificaContrasenna.getText();
            String correoElectronico = jtCorreoElectronico.getText();

            if (!nombre.equals("") && !nombreUsuario.equals("") && !contrasenna.equals("")
                    && !verificaContrasenna.equals("") && !correoElectronico.equals("")) {
                
                if (contrasenna.equals(verificaContrasenna)) {

                    Administrador administrador = new Administrador(nombre, nombreUsuario, contrasenna, correoElectronico);
//                     llamar al server para insertar
//                    boolean insertado = true;
//                    if (!true) {
//                        jlMensaje.setText(Strings.ERROR_NOMBRE_USUARIO_OCUPADO);
//
//                    }
                } else {
                    jlMensaje.setText(Strings.ERROR_CONTRASENNA_DIFERENTE);
                }

            } else {
                jlMensaje.setText(Strings.ERRORCAMPOVACIO);
            }

        }

    }

}
