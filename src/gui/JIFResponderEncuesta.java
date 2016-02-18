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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
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
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Strings;

/**
 * @author adriansb3105
 * @author Daniel
 */
public class JIFResponderEncuesta extends JInternalFrame implements ActionListener {

    private List listaComponentes;
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

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(850, 700));
        this.setAutoscrolls(true);

        this.posicionEnGrid = 0;
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.encuestaActual = encuesta;

        initComponentes();

        this.setVisible(true);
    }

    public void ocultarBarraTitulo() {
        barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimensionBarra = barra.getPreferredSize();
        barra.setSize(0, 0);
        barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    private void initComponentes() {
        this.listaComponentes = new ArrayList<>();
        this.jpEstatico = new JPanel();
        this.jpEstatico.setLayout(new GridBagLayout());
        this.add(this.jpEstatico, BorderLayout.NORTH);
        this.gridBagEstatico = new GridBagConstraints();
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;

        this.gridBagEstatico.insets = new Insets(2, 10, 5, 10);
        this.jlTitulo = new JLabel(this.encuestaActual.getTitulo());
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.ipadx = 200;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 0;
        this.jpEstatico.add(this.jlTitulo, this.gridBagEstatico);

        this.jbGuardar = new JButton(Strings.GUARDAR);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.ipadx = 30;
        this.gridBagEstatico.gridx = 3;
        this.gridBagEstatico.gridy = 0;
        this.jbGuardar.addActionListener(this);
        this.jpEstatico.add(this.jbGuardar, this.gridBagEstatico);

        this.jbCancelar = new JButton(Strings.CANCELAR);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 1;
        this.gridBagEstatico.gridx = 4;
        this.gridBagEstatico.gridy = 0;
        this.jbCancelar.addActionListener(this);
        this.jpEstatico.add(this.jbCancelar, this.gridBagEstatico);

        this.jtaDescripcion = new JTextArea(this.encuestaActual.getDescripcion());
        this.jtaDescripcion.setLineWrap(true);
        this.jtaDescripcion.setEditable(false);
        this.scroll = new JScrollPane(this.jtaDescripcion);
        this.gridBagEstatico.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagEstatico.anchor = GridBagConstraints.WEST;
        this.gridBagEstatico.gridwidth = 2;
        this.gridBagEstatico.ipady = 40;
        this.gridBagEstatico.gridx = 0;
        this.gridBagEstatico.gridy = 1;
        this.jpEstatico.add(this.scroll, this.gridBagEstatico);

        this.jpDinamico = new JPanel(new GridBagLayout());
        this.jpDinamico.setBackground(Color.white);
        this.scrollDinamico = new JScrollPane(jpDinamico);
        this.scrollDinamico.setAutoscrolls(true);
        this.add(this.scrollDinamico, BorderLayout.CENTER);
        this.gridBagDinamico = new GridBagConstraints();
        this.gridBagDinamico.fill = GridBagConstraints.NONE;
        this.gridBagDinamico.anchor = GridBagConstraints.NORTHWEST;
        this.gridBagDinamico.insets = new Insets(10, 10, 10, 0);

        llenaContenido();
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

            switch (tipo) {
                case Strings.TIPO_UNICA:
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
                    this.listaComponentes.add(grupo);
                    break;

                case Strings.TIPO_MULTIPLE:
                    List<JCheckBox> listaCheck = new ArrayList<>();
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
                        listaCheck.add(check);
                    }
                    this.listaComponentes.add(listaCheck);
                    break;

                default:
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
                    this.jpDinamico.add(scrollRespuesta, this.gridBagDinamico);
                    this.listaComponentes.add(jtaRespuesta);
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbCancelar) {
            this.dispose();
            updateUI();
        } else if (e.getSource() == this.jbGuardar) {

            for (int k = 0; k < this.encuestaActual.getPreguntas().size(); k++) {

                List<String> listaRespuestas = new ArrayList<>();

                switch (this.encuestaActual.getPreguntas().get(k).getTipo()) {

                    case Strings.TIPO_UNICA:
                        ButtonGroup grupo = (ButtonGroup) listaComponentes.get(k);
                        for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();

                            if (button.isSelected()) {
                                listaRespuestas.add(button.getText());
                            }
                        }
                        break;

                    case Strings.TIPO_MULTIPLE:
                        List<JCheckBox> listaCheck = (List<JCheckBox>) this.listaComponentes.get(k);

                        for (int i = 0; i < listaCheck.size(); i++) {
                            if (listaCheck.get(i).isSelected()) {
                                Object[] check = listaCheck.get(i).getSelectedObjects();
                                listaRespuestas.add(check[0].toString());
                            }
                        }
                        break;

                    default:
                        JTextArea texto = (JTextArea) listaComponentes.get(k);

                        listaRespuestas.add(texto.getText());
                        break;
                }

                this.encuestaActual.getPreguntas().get(k).setListaRespuestas(listaRespuestas);
            }
        }
    }

}
