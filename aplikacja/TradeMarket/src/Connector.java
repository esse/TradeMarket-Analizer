import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Connector {
	
	private Connector()
	{
		
	}
	
	private static Connector ref;
	Session session = null;
	
	public static Connector getConnector() {
		if (ref == null) {
			ref = new Connector();
		}
		return ref;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
			throw new CloneNotSupportedException();		
	}
	
	public Session getSession() {
		return session;
	}
	
	public Session createSession() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		return session;
	}
	
	public void closeSession() {
		session.flush();
		session.close();		
	}
	
	
	

}
