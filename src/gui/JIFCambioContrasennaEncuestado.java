/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Encuestado;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import logic.Cliente;
import security.Encriptar;
import util.Strings;

/**
 * Clase que me permite cambiar la contraseña del encuestado cuando el
 * encuestado quiera hacerlo
 *
 * @author Daniel
 */
public class JIFCambioContrasennaEncuestado extends JInternalFrame implements ActionListener {

    private JLabel jlContrasenna;
    private JLabel jlVerificaContrasenna;
    private JLabel jlNuevaContrasenna;
    private JPasswordField jpfContrasenna;
    private JPasswordField jpfVerificarContrasenna;
    private JPasswordField jpfNuevaContrasenna;
    private JButton jbAceptar;
    private JButton jbCancelar;
    private GridBagConstraints gridBag;
    private JComponent barra;
    private Dimension dimensionBarra;
    private Encuestado encuestado;

    /**
     *
     * @param encuestado el encuestado al que se le va a cambiar la contraseña
     */
    public JIFCambioContrasennaEncuestado(Encuestado encuestado) {
        super("Por favor, cambie su contraseña", true, false);

        this.encuestado = encuestado;

        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();

        this.setLayout(new GridBagLayout());
        this.gridBag = new GridBagConstraints();
        initComponents();
        this.setSize(400, 400);

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

    private void initComponents() {
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.insets = new Insets(20, 0, 0, 0);
        this.jlContrasenna = new JLabel(Strings.LOGINCONTRASENIA);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 0;
        this.add(this.jlContrasenna, gridBag);

        this.jpfContrasenna = new JPasswordField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 1;
        this.add(this.jpfContrasenna, gridBag);

        this.jlVerificaContrasenna = new JLabel(Strings.REGISTROVERIFICACONTRASENIA);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(this.jlVerificaContrasenna, gridBag);

        this.jpfVerificarContrasenna = new JPasswordField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(this.jpfVerificarContrasenna, gridBag);

        this.jlNuevaContrasenna = new JLabel(Strings.REGISTRO_NUEVA_CONTRASENNA);
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 4;
        this.add(this.jlNuevaContrasenna, gridBag);

        this.jpfNuevaContrasenna = new JPasswordField();
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 5;
        this.add(this.jpfNuevaContrasenna, gridBag);

        this.jbAceptar = new JButton(Strings.GUARDAR);
        this.jbAceptar.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
        this.gridBag.anchor = GridBagConstraints.CENTER;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 6;
        this.add(this.jbAceptar, gridBag);

        this.jbCancelar = new JButton(Strings.CANCELAR);
        this.jbCancelar.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
        this.gridBag.anchor = GridBagConstraints.CENTER;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 7;
        this.add(this.jbCancelar, gridBag);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbAceptar) {
            String contrasenna = Encriptar.password(jpfContrasenna.getPassword(), Encriptar.SHA256);
            String verificaContrasenna = Encriptar.password(jpfVerificarContrasenna.getPassword(), Encriptar.SHA256);

            if (contrasenna.equals(verificaContrasenna)) {
                if (contrasenna.equals(this.encuestado.getContrasenna())) {
                    String nuevaContrasenna = Encriptar.password(jpfNuevaContrasenna.getPassword(), Encriptar.SHA256);
                    this.encuestado.setContrasenna(nuevaContrasenna);

                    Cliente cliente = new Cliente(Strings.PETICION_CAMBIAR_CONTRASENNA_ENCUESTADO, this.encuestado);

                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(rootPane, Strings.ERROR_CONTRASENNA_INVALIDA, Strings.ERROR,
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, Strings.ERROR_CONTRASENNA_DIFERENTE, Strings.ERROR,
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == jbCancelar) {
            this.dispose();
            updateUI();
        }

    }

}
