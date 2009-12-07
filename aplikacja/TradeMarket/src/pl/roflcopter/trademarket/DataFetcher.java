package pl.roflcopter.trademarket;

import pl.roflcopter.trademarket.index.Index;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class DataFetcher implements Runnable {

	
	String start;
	String finish;
	public ArrayList<HashMap<Date, Float>> mapArray;
	public HashMap<Date, ArrayList<Event>> eventsMap;
	Gui window;
	Thread drawingThread; Thread analyzerThread;
	
	
	public DataFetcher(String _start, String _finish, Gui _window) {
		start = _start;
		finish = _finish;
//		mapArray = _mapArray;
//		eventsMap = _eventsMap;
		window = _window;
	}
	
	@Override
	public void run() {
		go();
	}
	
	public void go() {
		Connector connector = Connector.getConnector();
        String query = "from Event where date >= '" + start + "'and date <= '" + finish + "'";
        String query2 = "from Nasdaq where date >= '" + start + "'and date <= '" + finish + "'";
        String query3 = "from Dax where date >= '" + start + "'and date <= '" + finish + "'";
        String query4 = "from Nikkei where date >= '" + start + "'and date <= '" + finish + "'";
		List<Event> event = connector.getSession().createQuery(query).list();
		List<Index> nasdaq = connector.getSession().createQuery(query2).list();
		List<Index> dax = connector.getSession().createQuery(query3).list();
		List<Index> nikkei = connector.getSession().createQuery(query4).list();
		HashMap<Date, Float> nasdaqMap = new HashMap<Date, Float>(nasdaq.size());
    	HashMap<Date, Float> daxMap = new HashMap<Date, Float>(dax.size());
    	HashMap<Date, Float> nikkeiMap = new HashMap<Date, Float>(nikkei.size());
    	eventsMap = new HashMap<Date, ArrayList<Event>>(event.size());
    	for (java.util.Iterator<Index> i = nasdaq.iterator(); i.hasNext();) {
			Index ind = i.next();
			nasdaqMap.put(ind.getDate(), ind.getValue());
        }
		for (java.util.Iterator<Index> i = dax.iterator(); i.hasNext();) {
			Index ind = i.next();
			daxMap.put(ind.getDate(), ind.getValue());
        }
		for (java.util.Iterator<Index> i = nikkei.iterator(); i.hasNext();) {
			Index ind = i.next();
			nikkeiMap.put(ind.getDate(), ind.getValue());
        }
		
		for (java.util.Iterator<Event> i = event.iterator(); i.hasNext();) {
			Event evn = i.next();
			if (eventsMap.containsKey(evn.getDate())) {
				ArrayList<Event> evnlist;
				evnlist = eventsMap.get(evn.getDate());
				evnlist.add(evn);
				
			} else {
				ArrayList<Event> evnlist = new ArrayList<Event>();
				evnlist.add(evn);
				eventsMap.put(evn.getDate(), evnlist);
			}
			
		}
		mapArray = new ArrayList<HashMap<Date, Float>>();
		mapArray.add(nasdaqMap);
		mapArray.add(daxMap);
		mapArray.add(nikkeiMap);
		window.seteventsMap(eventsMap);
		window.setmaparray(mapArray);
	}

}
