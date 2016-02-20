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
import logic.Cliente;
import util.Strings;

/**
 * Internal que me permite escoger el nombre de una encuesta de la lista de
 * encuestas que pertenecen al administrador y a partir de aqui establecer la
 * conexión con el servidor para llegar a abrir o editar dicha encuesta
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

    /**
     *
     * @param jifAdmin el internal donde se va a crear esta clase
     * @param listaEncuestas la lista de encuestas que el administrador en
     * sesión ha creado
     *
     * @param flag una bandera que me permite saber su voy a editar una encuesta
     * o si sólamente voy a abrirla después de escoger dicha encuesta
     */
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
        this.gridBag.ipadx = 50;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(jbAbrir, gridBag);

        this.jbCancelar = new JButton(Strings.CANCELAR);
        this.jbCancelar.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
        this.gridBag.ipadx = 30;
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
          

            String nombreEncuesta = jcEncuestas.getSelectedItem().toString();
            Cliente cliente = new Cliente(Strings.PETICION_GET_ENCUESTA, nombreEncuesta, this.jifAdmin, this.flag);
            this.dispose();

        }
    }

}
