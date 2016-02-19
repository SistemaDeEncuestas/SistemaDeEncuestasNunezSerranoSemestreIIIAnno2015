package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import logic.Cliente;
import util.Strings;

/**
 * @author adriansb3105
 */
public class JIFEstadisticas extends JInternalFrame implements ActionListener{

    private JButton jbGenerar;
    private JButton jbCancelar;
    private JComponent barra;
    private Dimension dimensionBarra;
    private GridBagConstraints gbc;
    private JLabel jlEncuesta;
    private JComboBox jcbEncuesta;
    private JLabel jlParte;
    private JComboBox jcbParte;
    private JLabel jlTipo;
    private JComboBox jcbTipo;
    private List<String> listaEncuestas;
    private String nickAdmin;
    private String nombreEncuesta;
    private String tipoGrafico;
    private String parte;
    
    public JIFEstadisticas(List<String> listaEncuestas, String nickAdmin) {
        super();
        
        this.setLayout(new GridBagLayout());
        this.listaEncuestas = listaEncuestas;
        this.nickAdmin = nickAdmin;
        this.dimensionBarra = null;
        this.barra = ((BasicInternalFrameUI) getUI()).getNorthPane();
        this.setLayout(new GridBagLayout());
        
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
    
    private void initComponents() {

        this.gbc = new GridBagConstraints();

        this.gbc.insets = new Insets(10, 10, 10, 10);
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.gridwidth = 5;
        
        this.jlEncuesta = new JLabel("Seleccione la encuesta que desea graficar", SwingConstants.CENTER);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.add(this.jlEncuesta, this.gbc);
        
        this.jcbEncuesta = new JComboBox();
        String[] encuestas = new String[this.listaEncuestas.size()+1];
        encuestas[0] = "Seleccione la encuesta";
        for (int i = 0; i < this.listaEncuestas.size(); i++) {
            encuestas[i+1] = this.listaEncuestas.get(i);
        }
        this.jcbEncuesta.setModel(new DefaultComboBoxModel(encuestas));
        this.jcbEncuesta.addActionListener(this);
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.add(this.jcbEncuesta, this.gbc);
        
        this.jlParte = new JLabel("Seleccione la parte que desea graficar (solo preguntas de respuesta única)", SwingConstants.CENTER);
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.add(this.jlParte, this.gbc);
        
        this.jcbParte = new JComboBox();
        this.jcbParte.setModel(new DefaultComboBoxModel(new String[1]));
        this.jcbParte.addActionListener(this);
        this.gbc.gridx = 0;
        this.gbc.gridy = 3;
        this.add(this.jcbParte, this.gbc);
        
        this.jlTipo = new JLabel("Indique que tipo de gráfico desea generar", SwingConstants.CENTER);
        this.gbc.gridx = 0;
        this.gbc.gridy = 4;
        this.add(this.jlTipo, this.gbc);
        
        this.jcbTipo = new JComboBox();
        this.jcbTipo.setModel(new DefaultComboBoxModel(new String[1]));
        this.jcbTipo.addActionListener(this);
        this.gbc.gridx = 0;
        this.gbc.gridy = 5;
        this.add(this.jcbTipo, this.gbc);
        
        
        this.gbc.insets = new Insets(30, 10, 20, 10);
        this.gbc.gridwidth = 1;
        
        this.jbGenerar = new JButton("Generar estadisticas");
        this.jbGenerar.addActionListener(this);
        this.gbc.gridx = 0;
        this.gbc.gridy = 6;
        this.add(this.jbGenerar, this.gbc);
        
        this.jbCancelar = new JButton("Cancelar");
        this.jbCancelar.addActionListener(this);
        this.gbc.gridx = 4;
        this.gbc.gridy = 6;
        this.add(this.jbCancelar, this.gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.jbGenerar) {
            System.out.println("generar");
        }
        
        if (e.getSource() == this.jbCancelar) {
            System.out.println("cancelar");
        }
        
        if (e.getSource() == this.jcbEncuesta) {
            if (!this.jcbEncuesta.getSelectedItem().toString().equals("Seleccione la encuesta")) {
                this.nombreEncuesta = this.jcbEncuesta.getSelectedItem().toString();
                
                System.out.println(this.nombreEncuesta);
                Cliente cliente = new Cliente(Strings.PETICION_NOMBRES_POR_ENCUESTA, this.nickAdmin, this.nombreEncuesta, Strings.listaPreguntas);
                System.out.println(Strings.listaPreguntas);
                
                String[] partes = new String[Strings.listaPreguntas.size()+1];
                partes[0] = "Seleccione la parte";
                for (int i = 0; i < Strings.listaPreguntas.size(); i++) {
                    partes[i+1] = Strings.listaPreguntas.get(i);
                }
                this.jcbParte.setModel(new DefaultComboBoxModel(partes));
            }
        }
        
        if (e.getSource() == this.jcbParte) {
            if (!this.jcbParte.getSelectedItem().toString().equals("Seleccione la parte")) {
                this.parte = this.jcbParte.getSelectedItem().toString();
                System.out.println(parte);
            }
        }
        
        if (e.getSource() == this.jcbTipo) {
            this.tipoGrafico = this.jcbTipo.getSelectedItem().toString();
            System.out.println(this.tipoGrafico);
        }
    }
    
}
