import index.Index;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Analyzer {
	
	private Date start;
	
	private Date finish;

	private Integer period;
	
	public Analyzer(Date start, Integer period, Date finish) {
		setStart(start);
		setFinish(finish);
		setPeriod(7);
	}

	public void setStart(Date date) {
		this.start = date;
	}

	public Date getStart() {
		return start;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriod() {
		return period;
	}
	
	public String analyze(ArrayList<HashMap<Date, Float>> map) {
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Connector connector = Connector.getConnector();      
//        String query;
//        String[] indexes = { "Nasdaq", "Nikkei", "Dax" };
        HashMap<Date, Float> nasdaqMap = map.get(0);
    	HashMap<Date, Float> daxMap = map.get(1);
		HashMap<Date, Float> nikkeiMap = map.get(2);
		float nq = 1;
		float vn = 0;
		float vd = 0;
		float vnk = 0;
		float dq = 1;
		float nkq = 1;
//		float nq3;
		float i = 0;
		String finals = "";
        for (Date date : nasdaqMap.keySet()) {
//        	nasdaq.add(date.getTime(), nasdaqMap.get(date));
//        	System.out.println(date + ": " + nasdaqMap.get(date));
        	if (nasdaqMap.get(date) != null) {
        		vn = nasdaqMap.get(date) / nq;
        		
        		nq = nasdaqMap.get(date);
        		}
        	if (daxMap.get(date) != null) {
        		vd = daxMap.get(date) / dq;
        		dq = daxMap.get(date);
        	}
        	if (nikkeiMap.get(date) != null) {
        		vnk = nikkeiMap.get(date) / nkq;
    			nkq = nikkeiMap.get(date);
        	}
        		if (java.lang.Math.abs(vd - vn) < 1) {
        			finals = finals + "dax i nasdaq - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vd - vn) + "\n";
        		}	
        		if (java.lang.Math.abs(vd - vnk) < 1) {
        			finals = finals + "dax i nikkei - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vd - vnk) + "\n";
        				
    			}
        		if (java.lang.Math.abs(vnk - vn) < 1) {
        			finals = finals + "nasdaq i nikkei - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vn - vnk) + "\n";
        				
    			}
        		
        		 
    			if (java.lang.Math.abs(vd - vnk) < 0.9 && java.lang.Math.abs(vnk - vn) < 0.9) {
    				finals = finals + "nasdaq, nikkei i dax - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vd - vnk) + "," + java.lang.Math.abs(vnk - vn) + "\n";
    			}
//                	System.out.println(date + ": " + nikkeiMap.get(date));
                
            }
        return finals;
        }
        
        
            
//            System.out.println(list.get(0));
        
        
        

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public Date getFinish() {
		return finish;
	}

}
