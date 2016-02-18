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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Exportar;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFAbrirEncuesta extends JInternalFrame implements ActionListener {

    private Encuesta encuestaActual;

    private JComponent barra;
    private Dimension dimensionBarra;

    private JPanel jpContenedor;
    private JPanel jpEstatico;
    private GridBagConstraints gridBagEstatico;
    private JLabel jlTitulo;
    private JTextArea jtaDescripcion;
    private JScrollPane scroll;
    private JButton jbToPDF;
    private JButton jbCancelar;
    private JPanel jpDinamico;
    private JScrollPane scrollDinamico;
    private GridBagConstraints gridBagDinamico;

    private JInternalFrame jifAdmin;

    private int posicionEnGrid;

    public JIFAbrirEncuesta(JInternalFrame jifAdmin, Encuesta encuesta) {

        super();
        this.posicionEnGrid = 0;
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.jifAdmin = jifAdmin;
        this.encuestaActual = encuesta;

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

        this.jpContenedor = new JPanel();
        this.jpContenedor.setLayout(new BorderLayout());
        this.add(jpContenedor);

        jpEstatico = new JPanel();
        jpEstatico.setLayout(new GridBagLayout());
        this.jpContenedor.add(jpEstatico, BorderLayout.NORTH);
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

        jbToPDF = new JButton();
        jbToPDF.setIcon(new ImageIcon(getClass().getResource("/images/pdf.png")));
        jbToPDF.setToolTipText(Strings.A_PDF);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.ipadx = 30;
        this.gridBagEstatico.gridx = 3;
        this.gridBagEstatico.gridy = 0;
        jbToPDF.addActionListener(this);
        jpEstatico.add(jbToPDF, gridBagEstatico);

        jbCancelar = new JButton();
        jbCancelar.setIcon(new ImageIcon(getClass().getResource("/images/close.png")));
        jbCancelar.setToolTipText(Strings.CANCELAR);
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
        this.jpContenedor.add(scrollDinamico, BorderLayout.CENTER);
        this.gridBagDinamico = new GridBagConstraints();
        this.gridBagDinamico.fill = GridBagConstraints.NONE;
        this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;

        this.gridBagDinamico.insets = new Insets(10, 10, 10, 0);

        llenaContenido();
        this.jifAdmin.add(this, BorderLayout.CENTER);
    }

    public void llenaContenido() {
        for (int i = 0; i < this.encuestaActual.getPreguntas().size(); i++) {

            Pregunta preguntaActual = this.encuestaActual.getPreguntas().get(i);

            JLabel enunciado = new JLabel(preguntaActual.getEnunciado());

            this.gridBagDinamico.fill = GridBagConstraints.NONE;
            this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
            this.gridBagDinamico.weighty = 0;
            this.gridBagDinamico.weightx = 0;
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

                    JRadioButton radio = new JRadioButton(preguntaActual.getListaRespuestas().get(j));
                    radio.setBackground(Color.white);
                    this.gridBagDinamico.fill = GridBagConstraints.HORIZONTAL;
                    this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                    this.gridBagDinamico.weighty = 0;
                    this.gridBagDinamico.weightx = 0;
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

                    JCheckBox check = new JCheckBox(preguntaActual.getListaRespuestas().get(j));
                    check.setBackground(Color.white);
                    this.gridBagDinamico.fill = GridBagConstraints.HORIZONTAL;
                    this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                    this.gridBagDinamico.weighty = 0;
                    this.gridBagDinamico.weightx = 0;
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
                this.gridBagDinamico.weightx = 3;
                this.gridBagDinamico.weighty = 2;
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
        } else if (e.getSource() == jbToPDF) {
            Exportar exportar = new Exportar();
            boolean exportado = exportar.exportarAPDF(this.encuestaActual);
            if (exportado) {
                JOptionPane.showMessageDialog(rootPane, "Hemos exportado su encuesta con éxito",
                        "Listo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se pudo crear el archivo",
                        "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
