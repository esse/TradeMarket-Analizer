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

public class ChartDrawer {
	    public static BufferedImage createImage(ArrayList<HashMap<Date, Float>> map) {
	    	
	        //         Create a simple XY chart
	        XYSeries nasdaq = new XYSeries("Nasdaq");
	        XYSeries dax = new XYSeries("Dax");
	        XYSeries nikkei = new XYSeries("Nikkei");
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

	        return chart.createBufferedImage(708,376);
	    }
	}
