import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class TradeMarket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = null;
		try{
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			//Create new instance of Contact and set values in it by reading them from form object
			System.out.println("Inserting Record");
			Event event = new Event();
			Date date = new Date();
			event.setDescription("testowy rekord");
			event.setDate(date);
			session.save(event);
			System.out.println("done");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
//			 Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}

	}

}
