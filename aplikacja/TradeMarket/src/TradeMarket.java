import java.sql.Connection;
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
			Session session = connector.getSession();
			Gui window = new Gui();
			window.setTitle("TradeMarket Analizer");
			window.setVisible(true);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
		
		}
	}

}
