package gui;

import java.awt.Color;
import javax.swing.JFrame;
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
public class Graficos extends JFrame{

    private JPanel panel;

    public Graficos() {
        super();
        
        this.setTitle("Graficos en java");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciar();
    }
    
    private void iniciar(){
        this.panel = new JPanel();
        this.getContentPane().add(this.panel);
        
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
        
        
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("C", 40);
        data.setValue("Java", 45);
        data.setValue("Python", 15);
        
        
        JFreeChart chartBar = ChartFactory.createBarChart("Participacion por genero", "Genero", "Dias", dataset, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart chartPie = ChartFactory.createPieChart("Participacion por genero", data, true, true, false);
        
        
        chartBar.setBackgroundPaint(Color.cyan);
        chartBar.getTitle().setPaint(Color.black);
        
        CategoryPlot p = chartBar.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        
        ChartPanel chartPanel = new ChartPanel(chartPie);
//        ChartPanel chartPanel = new ChartPanel(chartBar);
        this.panel.add(chartPanel);
    }
    
//    public static void main(String[] args){
//        Graficos e = new Graficos();
//        e.setVisible(true);
//    }
}