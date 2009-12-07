package pl.roflcopter.trademarket;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.labels.CustomXYToolTipGenerator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ChartDrawer implements Runnable {
		
		ArrayList<HashMap<Date, Float>> map;
		
		BufferedImage chartPlot;
		
		Thread fetcherThread;
		
		Gui window;
		
		public ChartDrawer(Gui _window, javax.swing.JLabel _label, Thread fetcherThread2) {
//			map = _map;
			window = _window;
			jLabel = _label;
			fetcherThread = fetcherThread2;
		}
//		
		javax.swing.JLabel jLabel;
		
	    public void go() throws InterruptedException {
	    	
	        //         Create a simple XY chart
	        XYSeries nasdaq = new XYSeries("Nasdaq");
	        XYSeries dax = new XYSeries("Dax");
	        XYSeries nikkei = new XYSeries("Nikkei");
	        fetcherThread.join();
	        map = window.getmaparray();
	        HashMap<Date, Float> nasdaqMap = map.get(0);
        	HashMap<Date, Float> daxMap = map.get(1);
    		HashMap<Date, Float> nikkeiMap = map.get(2);
	        for (Date date : nasdaqMap.keySet()) {
	        	nasdaq.add(date.getTime(), nasdaqMap.get(date));
	        }
	        for (Date date : daxMap.keySet()) {
	        	dax.add(date.getTime(), daxMap.get(date));
	        }
	        for (Date date : nikkeiMap.keySet()) {
	        	nikkei.add(date.getTime(), nikkeiMap.get(date));
	        }
	        //         Add the series to your data set
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(nasdaq);
	        dataset.addSeries(dax);
	        dataset.addSeries(nikkei);
	        
	       
	        //         Generate the graph
	        JFreeChart chart = ChartFactory.createXYLineChart("Indeksy giełdowe", // Title
	                "x-axis", // x-axis Label
	                "Wartość indeksu", // y-axis Label
	                dataset, // Dataset
	                PlotOrientation.VERTICAL, // Plot Orientation
	                true, // Show Legend
	                true, // Use tooltips
	                false // Configure chart to generate URLs?
	            );
	        final XYPlot plot = chart.getXYPlot();
	        final DateAxis axis2 = new DateAxis("Data");
	        plot.setDomainAxis(axis2);
	        jLabel.setText("");
	        jLabel.setIcon(new ImageIcon(chart.createBufferedImage(714,376)));
	    }
@Override
public void run() {
	try {
		go();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	}
