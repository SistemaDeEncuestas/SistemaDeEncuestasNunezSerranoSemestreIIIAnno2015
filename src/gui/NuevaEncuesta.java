package gui;

import domain.Encuesta;
import domain.Pregunta;
import domain.PreguntaAbierta;
import domain.PreguntaRespuestaMultiple;
import domain.PreguntaRespuestaUnica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
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
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import logic.Cliente;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class NuevaEncuesta extends JInternalFrame implements ActionListener {

    private JPanel jpEstatico;
    private JPanel jpDinamico;
    private JLabel jlTitulo;
    private JLabel jlDescripcion;
    private JTextField jtTitulo;
    private JTextArea jtDescripcion;
    private JButton jbPregunta;
    private JButton jbRespuesta;
    private JButton jbGuardar;
    private JButton jbCancelar;
    private JScrollPane scroll;
    private JScrollPane scrollDinamico;
    private ButtonGroup grupoRadio;
    private int posicionEnGrid;
    private JComponent barra;
    private Dimension dimensionBarra;
    private GridBagConstraints gridBagEstatico;
    private GridBagConstraints gridBagDinamico;

    private int posicionY;
    private int largo;

    private Encuesta miEncuesta;
    private List<Pregunta> listaPreguntas;

    private String titulo;
    private String descripcion;
    private String nombreCreador;
    private String nombreArchivo;
    Pregunta preguntaActual;
    private String tipoPregunta;

    public NuevaEncuesta(String nombreCreador) {

        super();
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();

        this.nombreCreador = nombreCreador;
        this.listaPreguntas = new ArrayList<>();
        this.titulo = "";
        this.descripcion = "";
        this.nombreArchivo = "";

        this.posicionEnGrid = 0;
        this.posicionY = 30;
        this.largo = 850;
        this.tipoPregunta = "";
        this.setLayout(new BorderLayout());

        initComponents();

        this.setPreferredSize(new Dimension(this.largo, 700));
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

    private void initComponents() {

        jpEstatico = new JPanel();
        jpEstatico.setLayout(new GridBagLayout());
        this.add(jpEstatico, BorderLayout.NORTH);
        this.gridBagEstatico = new GridBagConstraints();
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;

        this.gridBagEstatico.insets = new Insets(2, 10, 5, 10);
        jlTitulo = new JLabel(Strings.TITULO);
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

        jtTitulo = new JTextField();
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.ipadx = 200;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 1;
        jpEstatico.add(jtTitulo, gridBagEstatico);

        jlDescripcion = new JLabel(Strings.DESCRIPCION);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 2;
        jpEstatico.add(jlDescripcion, gridBagEstatico);

        jtDescripcion = new JTextArea();
        jtDescripcion.setLineWrap(true);
        scroll = new JScrollPane(jtDescripcion);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.ipady = 40;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 3;
        jpEstatico.add(scroll, gridBagEstatico);

        jbPregunta = new JButton(Strings.PREGUNTA);
        this.gridBagEstatico.fill = GridBagConstraints.NONE;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.ipady = 0;
        this.gridBagEstatico.ipadx = 30;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 4;
        this.jbPregunta.addActionListener(this);
        jpEstatico.add(jbPregunta, gridBagEstatico);

        this.jbRespuesta = new JButton(Strings.RESPUESTA);
        this.jbRespuesta.setEnabled(false);
        this.gridBagEstatico.fill = GridBagConstraints.NONE;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.ipady = 0;
        this.gridBagEstatico.ipadx = 30;
        this.gridBagEstatico.gridx = 1;
        this.gridBagEstatico.gridy = 4;
        this.jbRespuesta.addActionListener(this);
        jpEstatico.add(jbRespuesta, gridBagEstatico);

        this.jpDinamico = new JPanel(new GridBagLayout());
//        this.jpDinamico.setBackground(Color.black);
        this.scrollDinamico = new JScrollPane(jpDinamico);
        this.scrollDinamico.setAutoscrolls(true);
        this.add(scrollDinamico, BorderLayout.CENTER);
        this.gridBagDinamico = new GridBagConstraints();
        this.gridBagDinamico.fill = GridBagConstraints.NONE;
        this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;

        this.gridBagDinamico.insets = new Insets(10, 10, 10, 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbPregunta) {

            this.jbRespuesta.setEnabled(true);
            this.grupoRadio = new ButtonGroup();
            String[] tiposPregunta = {Strings.TIPO_1, Strings.TIPO_2, Strings.TIPO_3};

            Object tipoSeleccionado = JOptionPane.showInputDialog(this, Strings.OPTION_PREGUNTA,
                    Strings.OPTION_TITULO, JOptionPane.DEFAULT_OPTION, null, tiposPregunta, Strings.TIPO_1);

            if (tipoSeleccionado != null) {
                this.tipoPregunta = tipoSeleccionado.toString();
                if (this.tipoPregunta.equals(Strings.TIPO_1)) {
                    preguntaActual = new PreguntaRespuestaUnica();
                    preguntaActual.setTipo(Strings.TIPO_UNICA);
                } else if (this.tipoPregunta.equals(Strings.TIPO_2)) {
                    preguntaActual = new PreguntaRespuestaMultiple();
                    preguntaActual.setTipo(Strings.TIPO_MULTIPLE);
                } else {
                    preguntaActual = new PreguntaAbierta();
                    preguntaActual.setTipo(Strings.TIPO_ABIERTA);
                }

                String pregunta = JOptionPane.showInputDialog(rootPane, "Ingrese una Pregunta", "Nueva Pregunta",
                        JOptionPane.QUESTION_MESSAGE);

                if (!pregunta.trim().equals("")) {
                    preguntaActual.setEnunciado(pregunta);
                    this.listaPreguntas.add(preguntaActual);

                    JLabel jlPregunta = new JLabel(pregunta);
                    this.gridBagDinamico.fill = GridBagConstraints.NONE;
                    this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                    this.gridBagDinamico.ipadx = 0;
                    this.gridBagDinamico.ipady = 0;
                    this.gridBagDinamico.gridx = 0;
                    this.gridBagDinamico.gridy = this.posicionEnGrid;
                    this.posicionEnGrid++;
                    jpDinamico.add(jlPregunta, gridBagDinamico);

                    this.updateUI();
                    this.ocultarBarraTitulo();

                } else {
                    JOptionPane.showMessageDialog(rootPane, "No debe dejar el espacio en blanco",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        } else if (e.getSource() == jbRespuesta) {
//            List<String> listaRespuestas = new ArrayList<>();

            if (this.tipoPregunta.equals(Strings.TIPO_3)) {
                this.jbRespuesta.setEnabled(false);
                JTextArea textoRespuesta = new JTextArea();
                textoRespuesta.setLineWrap(true);
                JScrollPane scrollTexto = new JScrollPane(textoRespuesta);
                this.gridBagDinamico.fill = GridBagConstraints.NONE;
                this.gridBagDinamico.anchor = GridBagConstraints.WEST;
                this.gridBagDinamico.ipady = 40;
                this.gridBagDinamico.ipadx = 89;
                this.gridBagDinamico.gridx = 0;
                this.gridBagDinamico.gridy = this.posicionEnGrid;
                this.posicionEnGrid++;
                jpDinamico.add(scrollTexto, gridBagDinamico);
            } else {

                String respuesta = JOptionPane.showInputDialog(rootPane, "Ingrese una respuesta", "Respuesta:",
                        JOptionPane.QUESTION_MESSAGE);

                if (respuesta != null) {

                    if (!respuesta.trim().equals("")) {

                        this.preguntaActual.addRespuesta(respuesta);
                        if (this.tipoPregunta.equals(Strings.TIPO_1)) {

                            JRadioButton boton = new JRadioButton(respuesta);
                            this.gridBagDinamico.fill = GridBagConstraints.HORIZONTAL;
                            this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                            this.gridBagDinamico.ipady = 0;
                            this.gridBagDinamico.ipadx = 0;
                            this.gridBagDinamico.gridx = 0;
                            this.gridBagDinamico.gridy = this.posicionEnGrid;
                            this.posicionEnGrid++;
                            grupoRadio.add(boton);

                            this.jpDinamico.add(boton, gridBagDinamico);
                        } else if (this.tipoPregunta.equals(Strings.TIPO_2)) {

                            JCheckBox botonCheck = new JCheckBox(respuesta);
                            this.gridBagDinamico.fill = GridBagConstraints.HORIZONTAL;
                            this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
                            this.gridBagDinamico.ipady = 0;
                            this.gridBagDinamico.ipadx = 0;
                            this.gridBagDinamico.gridx = 0;
                            this.gridBagDinamico.gridy = this.posicionEnGrid;
                            this.posicionEnGrid++;
                            this.jpDinamico.add(botonCheck, gridBagEstatico);

                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "No debe dejar el espacio en blanco",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            this.updateUI();
            this.ocultarBarraTitulo();
        } else if (e.getSource() == jbGuardar) {

            String tituloEncuesta = this.jtTitulo.getText().trim();
            String descripcionEncuesta = this.jtDescripcion.getText().trim();

            if (!tituloEncuesta.isEmpty() && !descripcionEncuesta.isEmpty()) {
                descripcionEncuesta = descripcionEncuesta.replaceAll("\n", "&");
                this.nombreArchivo = JOptionPane.showInputDialog(rootPane, "Ingrese un nombre para el archivo", "Guardar como:",
                        JOptionPane.QUESTION_MESSAGE);
                this.miEncuesta = new Encuesta(this.nombreCreador, tituloEncuesta,
                        descripcionEncuesta, this.nombreArchivo, this.listaPreguntas);
                System.out.println(this.miEncuesta);
                Cliente cliente = new Cliente(Strings.PETICION_CREAR_ENCUESTA, this.miEncuesta);

                // llamar a cliente
            } else {
                JOptionPane.showMessageDialog(rootPane, "No debe dejar espacios en blanco", Strings.ERROR, JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == jbCancelar) {
            this.dispose();
            updateUI();
        }

    }

}
