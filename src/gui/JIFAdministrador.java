/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Daniel
 */
public class JIFAdministrador extends JInternalFrame implements ActionListener {

    private JPanel jPanelIzquierda;
    private JPanel jpDatos;
    private JPanel jpEntrada;
    private JPanel jpHistorial;
    private JPanel jpContenido;
    private JButton jbEncuestas;
    private JButton jbNuevo;
    private JButton jbEditar;
    private JButton jbEliminar;
    private JButton jbEnviar;
    private JButton jbNuevoAdmin;
    private JButton jbToPdf;
    private JButton jbEstadisticas;
    private Border bordeDatos;
    private Border bandejaDeEntrada;
    private Border historial;
    private JLabel jlNombre;
    private JLabel jlNickname;
    private JLabel jlCorreo;
    private JLabel jlListaEncuestas;
    private JToolBar jToolBar;

    public JIFAdministrador() {
        super("Bienvenido", true, true, true);

        this.setLayout(new BorderLayout());
        init();
        this.setVisible(true);
        this.setSize(800, 600);

    }

    private void init() {

        jToolBar = new JToolBar();
        jToolBar.setSize(800, 600);
        jToolBar.setLayout(new FlowLayout(0, 10, 5));

        jbEncuestas = new JButton();
        jbEncuestas.setIcon(new ImageIcon(getClass().getResource("/images/encuestas.png")));
        jbEncuestas.setToolTipText("Tus encuestas");
        jbEncuestas.addActionListener(this);
        jToolBar.add(jbEncuestas);
        jbNuevo = new JButton();
        jbNuevo.setIcon(new ImageIcon(getClass().getResource("/images/nueva.png")));
        jbNuevo.setToolTipText("Nueva encuesta");
        jbNuevo.addActionListener(this);
        jToolBar.add(jbNuevo);
        jbEditar = new JButton();
        jbEditar.setIcon(new ImageIcon(getClass().getResource("/images/editar.png")));
        jbEditar.setToolTipText("Editar encuesta");
        jbEditar.addActionListener(this);
        jToolBar.add(jbEditar);
        jbEliminar = new JButton();
        jbEliminar.setIcon(new ImageIcon(getClass().getResource("/images/eliminar.png")));
        jbEliminar.setToolTipText("Eliminar encuesta");
        jbEliminar.addActionListener(this);
        jToolBar.add(jbEliminar);
        jbEnviar = new JButton();
        jbEnviar.setIcon(new ImageIcon(getClass().getResource("/images/mail.png")));
        jbEnviar.setToolTipText("Enviar a correo electrónico");
        jbEnviar.addActionListener(this);
        jToolBar.add(jbEnviar);
        jbNuevoAdmin = new JButton();
        jbNuevoAdmin.setIcon(new ImageIcon(getClass().getResource("/images/admins.png")));
        jbNuevoAdmin.setToolTipText("Crear nueva cuenta de administrador");
        jbNuevoAdmin.addActionListener(this);
        jToolBar.add(jbNuevoAdmin);
        jbEstadisticas = new JButton();
        jbEstadisticas.setIcon(new ImageIcon(getClass().getResource("/images/graficos.png")));
        jbEstadisticas.setToolTipText("Mostrar estadísticas");
        jbEstadisticas.addActionListener(this);
        jToolBar.add(jbEstadisticas);
        jbToPdf = new JButton();
        jbToPdf.setIcon(new ImageIcon(getClass().getResource("/images/pdf.png")));
        jbToPdf.setToolTipText("Exportar a pdf");
        jbToPdf.addActionListener(this);
        jToolBar.add(jbToPdf);

        jToolBar.setFloatable(false);
        jToolBar.setRollover(true);
        this.add(jToolBar, BorderLayout.NORTH);

        jPanelIzquierda = new JPanel();
        jPanelIzquierda.setLayout(new BorderLayout());
        this.add(jPanelIzquierda, BorderLayout.WEST);

        jpDatos = new JPanel();
        bordeDatos = BorderFactory.createTitledBorder(null, "Datos de usuario", TitledBorder.CENTER, TitledBorder.CENTER);
        jpDatos.setBorder(bordeDatos);
        jpDatos.setLayout(new BoxLayout(jpDatos, BoxLayout.Y_AXIS));

        jPanelIzquierda.add(jpDatos, BorderLayout.NORTH);

        jlNombre = new JLabel("Nombre");
        jpDatos.add(jlNombre);
        jlNickname = new JLabel("Nickname");
        jpDatos.add(jlNickname);
        jlCorreo = new JLabel("Correo electronico");
        jpDatos.add(jlCorreo);

        jpEntrada = new JPanel();
        bandejaDeEntrada = BorderFactory.createTitledBorder(null, "Bandeja de entrada", TitledBorder.CENTER, TitledBorder.CENTER);
        jpEntrada.setBorder(bandejaDeEntrada);
        jpEntrada.setLayout(new BoxLayout(jpEntrada, BoxLayout.Y_AXIS));

        jlListaEncuestas = new JLabel("  Lista de encuestas  ");
        jpEntrada.add(jlListaEncuestas);
        
        jPanelIzquierda.add(jpEntrada, BorderLayout.CENTER);

        jpHistorial = new JPanel();
        historial = BorderFactory.createTitledBorder(null, "Historial", TitledBorder.CENTER, TitledBorder.CENTER);
        jpHistorial.setBorder(historial);
        jpHistorial.setLayout(new BoxLayout(jpHistorial, BoxLayout.Y_AXIS));

        
        jPanelIzquierda.add(jpHistorial, BorderLayout.SOUTH);

        jpContenido = new JPanel();
        jpContenido.setBackground(Color.GRAY);
        this.add(jpContenido);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
