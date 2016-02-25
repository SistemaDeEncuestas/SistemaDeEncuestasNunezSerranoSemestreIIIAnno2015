package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author adriansb3105
 */
public class Graficos extends JInternalFrame implements ActionListener{

    private JInternalFrame JIFAdministrador;
    private JPanel panel;
    private JComponent barra;
    private Dimension dimensionBarra;
    private String pregunta;
    private JButton btnCerrar;
    private JInternalFrame JIFEstadisticas;
    
    public Graficos(JInternalFrame JIFEstadisticas, JInternalFrame JIFAdministrador, List lista, String tipo, String pregunta) {
        super();
        
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "La pregunta seleccionada aún no contiene respuestas");
        }else{
            this.JIFEstadisticas = JIFEstadisticas;
            this.JIFEstadisticas.dispose();
            this.ocultarBarraTitulo();
            this.pregunta = pregunta;
            this.JIFAdministrador = JIFAdministrador;
            this.panel = new JPanel();
            this.panel.setLayout(new BorderLayout());
        
            if (tipo.equals("Barras")) {
                barras(lista);
            }else{
                pastel(lista);
            }
            
            this.btnCerrar = new JButton("Cerrar");
            this.btnCerrar.addActionListener(this);
            this.panel.add(this.btnCerrar, BorderLayout.SOUTH);
        
            this.getContentPane().add(this.panel);
            this.JIFAdministrador.add(this, BorderLayout.CENTER);
            this.setVisible(true);
        }
    }
    
    private void ocultarBarraTitulo() {
        barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimensionBarra = barra.getPreferredSize();
        barra.setSize(0, 0);
        barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }
    
    private void barras(List lista){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.setValue(8, "Mujeres", "Lunes");
        dataset.setValue(7, "Hombres", "Lunes");
        dataset.setValue(9, "Mujeres", "Martes");
        dataset.setValue(4, "Hombres", "Martes");
        dataset.setValue(7, "Mujeres", "Miercoles");
        dataset.setValue(4, "Hombres", "Miercoles");
        dataset.setValue(3, "Mujeres", "Jueves");
        dataset.setValue(5, "Hombres", "Jueves");
        dataset.setValue(9, "Mujeres", "Viernes");
        dataset.setValue(2, "Hombres", "Viernes");
        
        JFreeChart chartBar = ChartFactory.createBarChart("Participacion por genero", "Genero", "Dias", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        chartBar.setBackgroundPaint(Color.cyan);
        chartBar.getTitle().setPaint(Color.black);
        
        CategoryPlot p = chartBar.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        
        ChartPanel chartPanel2 = new ChartPanel(chartBar);
        this.panel.add(chartPanel2);
    }
    
    private void pastel(List lista){
        
        int porcentaje = (100/lista.size());
        
        DefaultPieDataset data = new DefaultPieDataset();
        
        for (int i = 0; i < lista.size();) {
            Object respuesta = lista.get(i);
            lista.remove(i);
            int cont = 1;
            
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(j).equals(respuesta)) {
                    lista.remove(j);
                    cont++;
                }
            }
            
            int cantidad = porcentaje*cont;
            data.setValue(respuesta.toString(), cantidad);
        }
        
        JFreeChart chartPie = ChartFactory.createPieChart(this.pregunta, data, true, true, false);
        
        ChartPanel chartPanel = new ChartPanel(chartPie);
        
        this.panel.add(chartPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCerrar) {
            this.dispose();
            updateUI();
        }
    }
}