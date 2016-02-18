/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Encuesta;
import domain.Encuestado;
import domain.Pregunta;
import domain.PreguntaAbierta;
import domain.PreguntaRespuestaUnica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import util.Strings;

/**
 *
 * @author Daniel
 */
public class JIFEncuestado extends JInternalFrame implements ActionListener {

    private JPanel jpArriba;
    private JPanel jpIzquierda;
    private JPanel jpDerecha;
    private JPanel jpEntrada;
    private JPanel jpBoton;
    private JPanel jpHistorial;
    private JLabel jlNombre;
    private JLabel jlNickname;
    private JLabel jlCorreo;
    private JLabel jlLIsta;
    private JLabel jlBandeja;
    private JLabel jlHistorial;
    private JButton jbAbrir;
    private Border tituloArriba;
    private Border bandejaDeEntrada;
    private Border historial;
    private Encuestado encuestado;
    private JList jListEncuestasCompartidas;
    private JDesktopPane escritorio;

    public JIFEncuestado(JDesktopPane escritorio, Encuestado encuestado) {

        super("Bienvenido " + encuestado.getNickname(), true, true, true);
        this.setLayout(new BorderLayout());
        this.escritorio = escritorio;
        this.encuestado = encuestado;
        initComponents();
        this.setSize(800, 600);
        this.escritorio.add(this);
        this.setVisible(true);

    }

    private void initComponents() {

        jpArriba = new JPanel();
        tituloArriba = BorderFactory.createTitledBorder(null, Strings.BORDE_DATOS, TitledBorder.CENTER, TitledBorder.CENTER);
        jpArriba.setBorder(tituloArriba);
        jpArriba.setLayout(new BoxLayout(jpArriba, BoxLayout.Y_AXIS));
        this.add(jpArriba, BorderLayout.NORTH);

        jlNombre = new JLabel(this.encuestado.getNombre());
        jpArriba.add(jlNombre);
        jlNickname = new JLabel(this.encuestado.getNickname());
        jpArriba.add(jlNickname);
        jlCorreo = new JLabel(this.encuestado.getCorreoElectronico());
        jpArriba.add(jlCorreo);
        jlLIsta = new JLabel("Encuestas (" + this.encuestado.getListaEncuestas().size() + ")");
        jpArriba.add(jlLIsta);

        jpIzquierda = new JPanel();
        jpIzquierda.setBackground(Color.blue);
        jpIzquierda.setLayout(new BoxLayout(jpIzquierda, BoxLayout.Y_AXIS));
        this.add(jpIzquierda, BorderLayout.WEST);

        jpEntrada = new JPanel(new GridBagLayout());
        bandejaDeEntrada = BorderFactory.createTitledBorder(null, Strings.BORDE_BANDEJA_USUARIO, TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.NONE;
        grid.anchor = GridBagConstraints.NORTH;

        jlBandeja = new JLabel("  Lista de encuestas  ");
        grid.gridx = 0;
        grid.gridy = 0;
        jpEntrada.add(jlBandeja, grid);

        jListEncuestasCompartidas = new JList();
        jListEncuestasCompartidas.setBackground(new java.awt.Color(192, 192, 192));
        jListEncuestasCompartidas.setListData(llenaLista());
        grid.gridx = 0;
        grid.gridy = 1;
        jpEntrada.add(jListEncuestasCompartidas, grid);
        jpIzquierda.add(jpEntrada);

        jpBoton = new JPanel();
        jpIzquierda.add(jpBoton);

        jbAbrir = new JButton("Abrir");
        jbAbrir.addActionListener(this);
        jpBoton.add(jbAbrir);

        jpHistorial = new JPanel();
        historial = BorderFactory.createTitledBorder(null, Strings.BORDE_HISTORIAL, TitledBorder.CENTER, TitledBorder.CENTER);
        jpHistorial.setBorder(historial);
        jpIzquierda.add(jpHistorial);

//        jpDerecha = new JPanel();
//        jpDerecha.setBackground(Color.GRAY);
//        this.add(jpDerecha, BorderLayout.CENTER);

    }

    public String[] llenaLista() {
        String[] encuestas = new String[this.encuestado.getListaEncuestas().size()];

        for (int i = 0; i < this.encuestado.getListaEncuestas().size(); i++) {
            encuestas[i] = this.encuestado.getListaEncuestas().get(i);

        }

        return encuestas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbAbrir) {
            List<String> encuestaActual = jListEncuestasCompartidas.getSelectedValuesList();
            //LLamar a cliente y pedir la encuesta

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
            lista.add(p3 );

            Encuesta encuesta = new Encuesta("pba", "Prueba encuesta",
                    "Esto es una prueba para probar el panel de llenaencuesta", "encuesta1", lista);
            JIFResponderEncuesta responde = new JIFResponderEncuesta(encuesta);
            responde.ocultarBarraTitulo();
            this.add(responde, BorderLayout.CENTER);
            
        }

    }

}
