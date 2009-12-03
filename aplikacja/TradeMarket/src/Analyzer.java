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
	
	public void analyze(ArrayList<HashMap<Date, Float>> map, HashMap<Date, ArrayList<Event>> events, Float corelation, javax.swing.tree.DefaultMutableTreeNode node, boolean three) {
            
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
//		String finals = "";
		SimpleDateFormat formatter;
		javax.swing.tree.DefaultMutableTreeNode addedNode;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		boolean created = false;
		ArrayList<Event> evnlist;
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
        	if (three) {
        		if (java.lang.Math.abs(vd - vn) < corelation) {
//        			finals = finals + "dax i nasdaq - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vd - vn) + "\n";
        			addedNode = new javax.swing.tree.DefaultMutableTreeNode("dax i nasdaq - Data: " + formatter.format(date) + ", współczynnik: " + java.lang.Math.abs(vd - vn));
        			node.insert(addedNode, i);
        			int j = 0;
        			evnlist = events.get(date);
        			for (java.util.Iterator<Event> w = evnlist.iterator(); w.hasNext();) {
        				Event evn = w.next();
        				addedNode.insert(new javax.swing.tree.DefaultMutableTreeNode(evn.getDescription()), j);
        				j++;
        	        }
        			i++;
        		}	
        		if (java.lang.Math.abs(vd - vnk) < corelation) {
//        			finals = finals + "dax i nikkei - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vd - vnk) + "\n";
        			addedNode = new javax.swing.tree.DefaultMutableTreeNode("dax i nikkei - Data: " + formatter.format(date) + ", współczynnik: " + java.lang.Math.abs(vd - vnk));
        			node.insert(addedNode, i);
        			int j = 0;
        			evnlist = events.get(date);
        			for (java.util.Iterator<Event> w = evnlist.iterator(); w.hasNext();) {
        				Event evn = w.next();
        				addedNode.insert(new javax.swing.tree.DefaultMutableTreeNode(evn.getDescription()), j);
        				j++;
        	        }
        			i++;	
    			}
        		if (java.lang.Math.abs(vnk - vn) < corelation) {
//        			finals = finals + "nasdaq i nikkei - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vn - vnk) + "\n";
        			addedNode = new javax.swing.tree.DefaultMutableTreeNode("nasdaq i nikkei - Data: " + formatter.format(date) + ", współczynnik: " + java.lang.Math.abs(vn - vnk));
        			node.insert(addedNode, i);
        			int j = 0;
        			evnlist = events.get(date);
        			for (java.util.Iterator<Event> w = evnlist.iterator(); w.hasNext();) {
        				Event evn = w.next();
        				addedNode.insert(new javax.swing.tree.DefaultMutableTreeNode(evn.getDescription()), j);
        				j++;
        	        }
        			i++;	
    			}
        	}
        		 
    			if (java.lang.Math.abs(vd - vnk) < corelation && java.lang.Math.abs(vnk - vn) < corelation) {
//    				finals = finals + "nasdaq, nikkei i dax - Data: " + date + ", współczynnik: " + java.lang.Math.abs(vd - vnk) + "," + java.lang.Math.abs(vnk - vn) + "\n";
    				addedNode = new javax.swing.tree.DefaultMutableTreeNode("nasdaq, nikkei i dax - Data: " + formatter.format(date) + ", współczynnik: " + java.lang.Math.abs(vd - vnk) + "," + java.lang.Math.abs(vnk - vn));
    				node.insert(addedNode, i);
    				int j = 0;
        			evnlist = events.get(date);
        			for (java.util.Iterator<Event> w = evnlist.iterator(); w.hasNext();) {
        				Event evn = w.next();
        				addedNode.insert(new javax.swing.tree.DefaultMutableTreeNode(evn.getDescription()), j);
        				j++;
        	        }
        			i++;
    			}
//                	System.out.println(date + ": " + nikkeiMap.get(date));
                
            }
//        return finals;
        }
        
        
            
//            System.out.println(list.get(0));
        
        
        

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public Date getFinish() {
		return finish;
	}

}
