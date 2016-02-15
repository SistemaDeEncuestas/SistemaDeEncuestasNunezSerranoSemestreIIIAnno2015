package gui;

import domain.Encuesta;
import domain.Pregunta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class NuevaEncuesta extends JPanel implements ActionListener {

//    private JPanel jpEstatico;
//    private JPanel jpDinamico;
    private JLabel jlTitulo;
    private JLabel jlDescripcion;
    private JTextField jtTitulo;
    private JTextArea jtDescripcion;
    private JButton jbPregunta;
    private JButton jbGuardar;
    private JButton jbCancelar;
    private JScrollPane scroll;
    private int posicionX;
    private int posicionY;
    private int largo;
    private Encuesta miEncuesta;
    private List<Pregunta> preguntas;

    public NuevaEncuesta() {

        super();
        this.posicionX = 10;
        this.posicionY = 30;
        this.largo = 850;
        this.preguntas = new ArrayList<>();
//      this.setBackground(Color.GRAY);
        initComponents();

        this.setPreferredSize(new Dimension(this.largo, 600));
        this.setLayout(null);
        this.setVisible(true);
        this.setAutoscrolls(true);

    }

    private void initComponents() {
//
//        jpEstatico = new JPanel();
//        jpEstatico.setLayout(null);
//        jpEstatico.setBackground(Color.red);
//        this.add(jpEstatico,BorderLayout.NORTH);

        jlTitulo = new JLabel(Strings.TITULO);
        jlTitulo.setBounds(this.posicionX, this.posicionY, 200, 20);
        this.add(jlTitulo);

        jbGuardar = new JButton(Strings.GUARDAR);
        jbGuardar.setBounds(430, this.posicionY, 120, 30);
        jbGuardar.addActionListener(this);
        this.add(jbGuardar);

        jbCancelar = new JButton(Strings.CANCELAR);
        jbCancelar.setBounds(560, this.posicionY, 120, 30);
        jbCancelar.addActionListener(this);
        this.add(jbCancelar);

        this.posicionY += 30;

        jtTitulo = new JTextField();
        jtTitulo.setBounds(this.posicionX, this.posicionY, 400, 20);
        this.add(jtTitulo);
        this.posicionY += 40;

        jlDescripcion = new JLabel(Strings.DESCRIPCION);
        jlDescripcion.setBounds(this.posicionX, this.posicionY, 200, 20);
        this.add(jlDescripcion);
        this.posicionY += 30;

        jtDescripcion = new JTextArea();
        jtDescripcion.setLineWrap(true);
        scroll = new JScrollPane(jtDescripcion);
        scroll.setBounds(this.posicionX, this.posicionY, 400, 80);
        this.add(scroll);
        this.posicionY += 90;

        jbPregunta = new JButton(Strings.PREGUNTA);
        jbPregunta.setBounds(this.posicionX, this.posicionY, 200, 20);
        jbPregunta.addActionListener(this);
        this.add(jbPregunta);
        this.posicionY += 40;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbPregunta) {
            ButtonGroup grupoRadio = new ButtonGroup();
            ButtonGroup grupoCheck = new ButtonGroup();

            String[] tiposPregunta = {Strings.TIPO_1, Strings.TIPO_2, Strings.TIPO_3};

            Object tipoSeleccionado = JOptionPane.showInputDialog(this, Strings.OPTION_PREGUNTA,
                    Strings.OPTION_TITULO, JOptionPane.DEFAULT_OPTION, null, tiposPregunta, Strings.TIPO_1);

            if (tipoSeleccionado != null) {
                String tipo = tipoSeleccionado.toString();

                JTextField jtPregunta = new JTextField();
                jtPregunta.setBounds(this.posicionX, this.posicionY, 400, 20);
                this.add(jtPregunta);
                JButton jbRespuesta = new JButton(Strings.RESPUESTA);
                jbRespuesta.setBounds(this.posicionX + 420, this.posicionY, 200, 30);
                this.posicionY += 40;

                jbRespuesta.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (tipo.equals(Strings.TIPO_3)) {
                            jbRespuesta.setEnabled(false);
                            JTextArea textoRespuesta = new JTextArea();
                            textoRespuesta.setLineWrap(true);
                            JScrollPane scrollTexto = new JScrollPane(textoRespuesta);
                            scrollTexto.setBounds(posicionX + 30, posicionY, 300, 100);
                            add(scrollTexto);
                            posicionY += 130;
                        } else {

                            String respuesta = JOptionPane.showInputDialog("Ingrese una respuesta", null);

                            if (respuesta != null) {
                                if (respuesta.trim().length() > largo) {
                                    largo = respuesta.length();
                                }
                                if (!respuesta.trim().equals("")) {

                                    if (tipo.equals(Strings.TIPO_1)) {

                                        JRadioButton boton = new JRadioButton(respuesta);
                                        boton.setBounds(posicionX + 30, posicionY, respuesta.length() * 40, 20);

                                        grupoRadio.add(boton);
                                        add(boton);
                                        posicionY += 40;
                                    } else if (tipo.equals(Strings.TIPO_2)) {

                                        JCheckBox boton = new JCheckBox(respuesta);
                                        boton.setBounds(posicionX + 30, posicionY, respuesta.length() * 40, 20);
//                                grupoCheck.add(boton);
                                        add(boton);
                                        posicionY += 40;

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No debe dejar el espacio en blanco",
                                            "Error", JOptionPane.ERROR_MESSAGE);

                                }
                            }
                        }

                    }

                });
                this.add(jbRespuesta);

                if (this.posicionY >= 600) {
                    this.setPreferredSize(new Dimension(this.largo, this.posicionY));
                }

                this.updateUI();

            }

        } else if (e.getSource() == jbGuardar) {

        } else if (e.getSource() == jbCancelar) {

        }

    }

}
