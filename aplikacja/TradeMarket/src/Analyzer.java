import index.Index;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Analyzer {
	
	private Date date;
	
	private Date finish;

	private Integer period;
	
	public Analyzer(Date date, Integer period, Date finish) {
		setDate(date);
		setFinish(finish);
		setPeriod(7);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriod() {
		return period;
	}
	
	public void analyze() {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int i = 0;
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Connector connector = Connector.getConnector();      
        String query;
//        String[] indexes = { "Nasdaq", "Nikkei", "Dax" };
        while(i < 23) {
        	cal.add(Calendar.DATE, +1);
            System.out.println(formatter.format(cal.getTime()));
            query = "from Nasdaq where date = '" + formatter.format(cal.getTime()) + "'";
            List<Index> list = connector.getSession().createQuery(query).list();
            System.out.println(list.get(0));
            i++;
        }
        
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	}

	public Date getFinish() {
		return finish;
	}

}
