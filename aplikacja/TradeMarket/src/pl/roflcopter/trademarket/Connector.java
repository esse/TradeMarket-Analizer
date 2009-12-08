package pl.roflcopter.trademarket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Singleton class

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
	
	public void createSession() {
		Configuration configuration = new Configuration();
        configuration.configure();
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("trademarket.props"));			
		} catch (FileNotFoundException e) {
		prop.put("hibernate.connection.username", "essemarket");
		prop.put("hibernate.connection.url", "jdbc:mysql://roflcopter.pl:3306/essemarket");
		prop.put("hibernate.connection.password", "tFu2wxwDMy8LsU9b");
		System.out.println("Config file not found - using defaults");
		} catch (IOException e) {
		e.printStackTrace();
		}
		configuration.addProperties(prop);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		
	}
	
	public Session getSession() {
		if (session == null) {
			createSession();			
		}
		return session;
	}
	
	
	public void closeSession() {
		session.flush();
		session.close();
		session = null;
	}
	
	
	

}
