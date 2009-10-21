import java.util.Date;
import java.util.List;
import org.hibernate.Session;


public class TradeMarket {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		
		try{
			Connector connector = Connector.getConnector();
			Session session = connector.createSession();
			MainGui window = new MainGui();
			window.setVisible(true);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
		
		}
	}

}
