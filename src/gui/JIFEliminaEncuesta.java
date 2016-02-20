/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Administrador;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 *Clase que permite establecer conexión con el servidor para eliminar una encuesta
 * @author Daniel
 */
public class JIFEliminaEncuesta extends JInternalFrame implements ActionListener {

    private JLabel jlMensaje;
    private JLabel jlAviso;
    private JComboBox jcEncuestas;
    private JButton jbEliminar;
    private JButton jbCancelar;
    private JComponent barra;
    private Dimension dimensionBarra;
    private List<String> listaEncuestas;
    private GridBagConstraints gridBag;
    private Administrador administrador;
    
    /**
     * 
     * @param admin el administrador que intenta eliminar su encuesta
     */
    public JIFEliminaEncuesta(Administrador admin) {
        super();
        this.administrador = admin;
        this.listaEncuestas = this.administrador.getEncuestasCreadas();
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
        
        this.jlAviso = new JLabel(Strings.LABEL_AVISO);
        this.gridBag.fill = GridBagConstraints.HORIZONTAL;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 2;
        this.add(jlAviso, gridBag);
        
        this.jbEliminar = new JButton(Strings.BOTON_ELIMINAR);
        this.jbEliminar.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
        this.gridBag.ipadx = 40;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 3;
        this.add(jbEliminar, gridBag);
         
        this.jbCancelar = new JButton(Strings.CANCELAR);
        this.jbCancelar.addActionListener(this);
        this.gridBag.fill = GridBagConstraints.NONE;
         this.gridBag.ipadx = 30;
        this.gridBag.gridx = 0;
        this.gridBag.gridy = 4;
        this.add(jbCancelar, gridBag);
        
    }
    
    public void llenaCombo(){
        for (int i = 0; i < this.listaEncuestas.size(); i++) {
            this.jcEncuestas.addItem(this.listaEncuestas.get(i));
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbCancelar){
            this.dispose();
            updateUI();
        }
        if(e.getSource() == jbEliminar){
            String nombreEncuesta = jcEncuestas.getSelectedItem().toString();
            Cliente cliente = new Cliente(Strings.PETICION_ELIMINA_ENCUESTA, nombreEncuesta);
            
            this.administrador.eliminaEncuesta(nombreEncuesta);
            this.jcEncuestas.removeItem(nombreEncuesta);
            
        }
    }

}
