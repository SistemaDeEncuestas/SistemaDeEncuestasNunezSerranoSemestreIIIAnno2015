/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Encuesta;
import domain.Pregunta;
import domain.PreguntaAbierta;
import domain.PreguntaRespuestaUnica;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFEscogeEncuesta extends JInternalFrame implements ActionListener {

    private JLabel jlMensaje;

    private JComboBox jcEncuestas;
    private JButton jbAbrir;
    private JButton jbCancelar;
    private JComponent barra;
    private Dimension dimensionBarra;
    private List<String> listaEncuestas;
    private GridBagConstraints gridBag;
    private boolean flag;
    JInternalFrame jifAdmin;

    public JIFEscogeEncuesta(JInternalFrame jifAdmin, List<String> listaEncuestas, boolean flag) {
        super();
        this.flag = flag;
        this.jifAdmin = jifAdmin;
        this.listaEncuestas = listaEncuestas;
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.setLayout(new GridBagLayout());
        this.gridBag = new GridBagConstraints();
        initComponents();
        this.setVisible(true);

    }

    public void ocultarBarraTitulo() {
        barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimensionBarra = barra.getPreferredSize();
        barra.setSize(0, 0);
        barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void initComponents() {

        this.jlMensaje = new JLabel(Strings.LABEL_ESCOGE_ENCUESTA);
        this.gridBag.insets = new Insets(60, 10, 10, 10);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 0;
        this.add(jlMensaje, gridBag);

        this.jcEncuestas = new JComboBox();
        llenaCombo();
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 1;
        this.add(jcEncuestas, gridBag);

        this.jbAbrir = new JButton(Strings.BOTON_ABRIR);
        this.jbAbrir.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(jbAbrir, gridBag);

        this.jbCancelar = new JButton(Strings.CANCELAR);
        this.jbCancelar.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(jbCancelar, gridBag);

    }

    public void llenaCombo() {
        for (int i = 0; i < this.listaEncuestas.size(); i++) {
            this.jcEncuestas.addItem(this.listaEncuestas.get(i));

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancelar) {
            this.dispose();
            updateUI();
        } else if (e.getSource() == jbAbrir) {
            //TODO

            List<Pregunta> lista = new ArrayList<>();

            Pregunta p1 = new PreguntaRespuestaUnica("Pregunta 1");
            p1.setTipo(Strings.TIPO_UNICA);
            List<String> respuestap1 = new ArrayList<>();
            String r1p1 = "reps 1";
            String r1p2 = "reps 2";
            String r1p3 = "reps 3";
            String r1p4 = "reps 4";
            respuestap1.add(r1p1);
            respuestap1.add(r1p2);
            respuestap1.add(r1p3);
            respuestap1.add(r1p4);
            p1.setListaRespuestas(respuestap1);

            Pregunta p2 = new PreguntaRespuestaUnica("Pregunta 2");
            p1.setTipo(Strings.TIPO_UNICA);
            List<String> respuestap2 = new ArrayList<>();
            String r2p1 = "reps 1";
            String r2p2 = "reps 2";
            String r2p3 = "reps 3";
            String r2p4 = "reps 4";
            respuestap2.add(r2p1);
            respuestap2.add(r2p2);
            respuestap2.add(r2p3);
            respuestap2.add(r2p4);
            p2.setListaRespuestas(respuestap2);

            Pregunta p3 = new PreguntaAbierta("Pregunta 3");
            p3.setTipo(Strings.TIPO_ABIERTA);
            lista.add(p1);
            lista.add(p2);
            lista.add(p3);

            Encuesta encuesta = new Encuesta("pba", "Prueba encuesta",
                    "Esto es una prueba para probar el panel de llenaencuesta", "encuesta1", lista);
            System.out.println(encuesta);
            if (!this.flag) {
                JIFAbrirEncuesta abrir = new JIFAbrirEncuesta(this.jifAdmin, encuesta);
                abrir.ocultarBarraTitulo();
                this.dispose();
                this.jifAdmin.add(abrir, BorderLayout.CENTER);
            } else {

                JIFEditaEncuesta edita = new JIFEditaEncuesta(jifAdmin, encuesta);
                edita.ocultarBarraTitulo();
                this.dispose();
                this.jifAdmin.add(edita, BorderLayout.CENTER);

            }

        }
    }

}
