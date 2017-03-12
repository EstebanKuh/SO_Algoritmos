/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import business.Procesos.proceso;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a Gantt chart.
 * <P>
 * This demo is intended to show the conceptual approach rather than being a polished
 * implementation.
 *
 *
 */
public class GanttDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public GanttDemo1(final String title) {
        super(title);
    }
    
    public GanttDemo1(ArrayList<proceso> lista_imprimir, String algorithmType) {
        this("SO Algoritmo de Planificacion " + algorithmType);
        
        final IntervalCategoryDataset dataset = createDataset(lista_imprimir);
        final JFreeChart chart = createChart(dataset);
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample dataset for a Gantt chart.
     *
     * @return The dataset.
     */
    public static IntervalCategoryDataset createDataset(ArrayList<proceso> lista_imprimir) {

        String nameProcess;
        TimePeriod periodProcess;
        
        final TaskSeries s1 = new TaskSeries("Scheduled");
        
        for (int i = 0; i < lista_imprimir.size(); i++) {
            nameProcess = "Proceso " + lista_imprimir.get(i).getId_proceso();
            s1.add(new Task(nameProcess,
                    new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                                         date(5, Calendar.APRIL, 2001))));
        }
        
//        s1.add(new Task("Proceso 1",
//               new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
//                                    date(5, Calendar.APRIL, 2001))));
//        s1.add(new Task("Proceso 2",
//               new SimpleTimePeriod(date(5, Calendar.APRIL, 2001),
//                                    date(9, Calendar.APRIL, 2001))));
//        s1.add(new Task("Proceso 3",
//               new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
//                                    date(15, Calendar.MAY, 2001))));
        

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }
        
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
            "",  // chart title
            "Procesos",              // domain axis label
            "Tiempo",              // range axis label
            dataset,             // data
            false,                // include legend
            false,                // tooltips
            false                // urls
        );    
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;    
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {
        final GanttDemo1 demo = new GanttDemo1("Sistemas 0perativos");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}