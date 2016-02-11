package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JLabel jlTitulo;
    private JLabel jlDescripcion;
    private JTextField jtTitulo;
    private JTextArea jtDescripcion;
    private JButton jbPregunta;
    private JScrollPane scroll;
    private int posicionX;
    private int posicionY;
    

    public NuevaEncuesta() {

        super();
        this.posicionX = 10;
        this.posicionY = 30;

        initComponents();

        this.setLayout(null);
        this.setVisible(true);

    }

    private void initComponents() {

        
        
        jlTitulo = new JLabel(Strings.TITULO);
        jlTitulo.setBounds(this.posicionX, this.posicionY, 200, 20);
        this.add(jlTitulo);
        this.posicionY += 40;

        jtTitulo = new JTextField();
        jtTitulo.setBounds(this.posicionX, this.posicionY, 400, 20);
        this.add(jtTitulo);
        this.posicionY += 60;

        jlDescripcion = new JLabel(Strings.DESCRIPCION);
        jlDescripcion.setBounds(this.posicionX, this.posicionY, 200, 20);
        this.add(jlDescripcion);
        this.posicionY += 40;

        jtDescripcion = new JTextArea();
        jtDescripcion.setLineWrap(true);
        scroll = new JScrollPane(jtDescripcion);
        scroll.setBounds(this.posicionX, this.posicionY, 400, 100);
        this.add(scroll);
        this.posicionY += 130;

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

                        if (tipo.equals(Strings.TIPO_1)) {

                            String respuesta = JOptionPane.showInputDialog("Ingrese una respuesta", null);
                            JRadioButton boton = new JRadioButton(respuesta);
                            boton.setBounds(posicionX + 30, posicionY, 300, 20);

                            grupoRadio.add(boton);
                            add(boton);
                            posicionY += 40;

                        } else if (tipo.equals(Strings.TIPO_2)) {

                            String respuesta = JOptionPane.showInputDialog("Ingrese una respuesta", null);
                            JCheckBox boton = new JCheckBox(respuesta);
                            boton.setBounds(posicionX + 30, posicionY, 300, 20);
                            grupoCheck.add(boton);
                            add(boton);
                            posicionY += 40;
                             

                        } else if (tipo.equals(Strings.TIPO_3)) {

                        }

                    }
                });
                this.add(jbRespuesta);

            } else {
                System.out.println("Canceló");
            }

        }

    }

}
