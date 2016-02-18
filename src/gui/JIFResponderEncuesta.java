/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Encuesta;
import domain.Pregunta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.RadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFResponderEncuesta extends JInternalFrame implements ActionListener {

    private Encuesta encuestaActual;

    private JComponent barra;
    private Dimension dimensionBarra;

    private JPanel jpEstatico;
    private GridBagConstraints gridBagEstatico;
    private JLabel jlTitulo;
    private JTextArea jtaDescripcion;
    private JScrollPane scroll;
    private JButton jbGuardar;
    private JButton jbCancelar;
    private JPanel jpDinamico;
    private JScrollPane scrollDinamico;
    private GridBagConstraints gridBagDinamico;

    private int posicionEnGrid;

    public JIFResponderEncuesta(Encuesta encuesta) {

        super();
        this.posicionEnGrid = 0;
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.encuestaActual = encuesta;
        this.setLayout(new BorderLayout());
        initComponentes();
        this.setPreferredSize(new Dimension(850, 700));
        this.setVisible(true);
        this.setAutoscrolls(true);

    }

    public void ocultarBarraTitulo() {
        barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimensionBarra = barra.getPreferredSize();
        barra.setSize(0, 0);
        barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    private void initComponentes() {

        jpEstatico = new JPanel();
        jpEstatico.setLayout(new GridBagLayout());
        this.add(jpEstatico, BorderLayout.NORTH);
        this.gridBagEstatico = new GridBagConstraints();
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;

        this.gridBagEstatico.insets = new Insets(2, 10, 5, 10);
        jlTitulo = new JLabel(this.encuestaActual.getTitulo());
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.ipadx = 200;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 0;
        jpEstatico.add(jlTitulo, gridBagEstatico);

        jbGuardar = new JButton(Strings.GUARDAR);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.ipadx = 30;
        this.gridBagEstatico.gridx = 3;
        this.gridBagEstatico.gridy = 0;
        jbGuardar.addActionListener(this);
        jpEstatico.add(jbGuardar, gridBagEstatico);

        jbCancelar = new JButton(Strings.CANCELAR);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.gridx = 4;
        this.gridBagEstatico.gridy = 0;
        jbCancelar.addActionListener(this);
        jpEstatico.add(jbCancelar, gridBagEstatico);

        jtaDescripcion = new JTextArea(this.encuestaActual.getDescripcion());
        jtaDescripcion.setLineWrap(true);
        jtaDescripcion.setEditable(false);
        scroll = new JScrollPane(jtaDescripcion);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.ipady = 40;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 1;
        jpEstatico.add(scroll, gridBagEstatico);

        this.jpDinamico = new JPanel(new GridBagLayout());
        this.jpDinamico.setBackground(Color.white);
        this.scrollDinamico = new JScrollPane(jpDinamico);
        this.scrollDinamico.setAutoscrolls(true);
        this.add(scrollDinamico, BorderLayout.CENTER);
        this.gridBagDinamico = new GridBagConstraints();
        this.gridBagDinamico.fill = GridBagConstraints.NONE;
        this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;

        this.gridBagDinamico.insets = new Insets(10, 10, 10,0);

        llenaContenido();
    }

    public void llenaContenido() {
        for (int i = 0; i < this.encuestaActual.getPreguntas().size(); i++) {

            Pregunta preguntaActual = this.encuestaActual.getPreguntas().get(i);

            JLabel enunciado = new JLabel(preguntaActual.getEnunciado());

            this.gridBagDinamico.fill = GridBagConstraints.NONE;
            this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
            this.gridBagDinamico.ipadx = 0;
            this.gridBagDinamico.ipady = 0;
            this.gridBagDinamico.gridx = 0;
            this.gridBagDinamico.gridy = this.posicionEnGrid;
            this.posicionEnGrid++;
            this.jpDinamico.add(enunciado, gridBagDinamico);

            String tipo = preguntaActual.getTipo();

            if (tipo.equals(Strings.TIPO_UNICA)) {

                ButtonGroup grupo = new ButtonGroup();

                for (int j = 0; j < preguntaActual.getListaRespuestas().size(); j++) {

                    JRadioButton radio = new JRadioButton(preguntaActual.getListaRespuestas().get(i));
                    radio.setBackground(Color.white);
                    this.gridBagDinamico.fill = GridBagConstraints.HORIZONTAL;
                    this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                    this.gridBagDinamico.ipady = 0;
                    this.gridBagDinamico.ipadx = 0;
                    this.gridBagDinamico.gridx = 0;
                    this.gridBagDinamico.gridy = this.posicionEnGrid;
                    this.posicionEnGrid++;
                    grupo.add(radio);
                    
                    this.jpDinamico.add(radio, gridBagDinamico);

                }

            } else if (tipo.equals(Strings.TIPO_MULTIPLE)) {

                for (int j = 0; j < preguntaActual.getListaRespuestas().size(); j++) {
                    
                    JCheckBox check = new JCheckBox(preguntaActual.getListaRespuestas().get(i));
                    check.setBackground(Color.white);
                     this.gridBagDinamico.fill = GridBagConstraints.HORIZONTAL;
                    this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                    this.gridBagDinamico.ipady = 0;
                    this.gridBagDinamico.ipadx = 0;
                    this.gridBagDinamico.gridx = 0;
                    this.gridBagDinamico.gridy = this.posicionEnGrid;
                    this.posicionEnGrid++;
                    
                    this.jpDinamico.add(check, gridBagDinamico);
                    
                }
                
                
            } else if (tipo.equals(Strings.TIPO_ABIERTA)) {
                JTextArea jtaRespuesta = new JTextArea();
                jtaRespuesta.setLineWrap(true);

                JScrollPane scrollRespuesta = new JScrollPane(jtaRespuesta);
                this.gridBagDinamico.fill = GridBagConstraints.NONE;
                this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                this.gridBagDinamico.ipadx = 0;
                this.gridBagDinamico.ipady = 0;
                this.gridBagDinamico.gridx = 0;
                this.gridBagDinamico.gridy = this.posicionEnGrid;
                this.posicionEnGrid++;
                jpDinamico.add(scrollRespuesta, gridBagDinamico);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancelar) {
            this.dispose();
            updateUI();
        } else if (e.getSource() == jbGuardar) {

        }
    }

}
