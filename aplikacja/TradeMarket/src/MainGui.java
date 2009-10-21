import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.*;

import java.awt.*;
import java.util.List;

public class MainGui extends JFrame implements ActionListener{
	JTextArea text;
	public MainGui() {
		this.setSize(300, 300);
		text = new JTextArea(10,20);
		text.setLineWrap(true);
		
		JButton button = new JButton("Pobierz z bazy");
		button.addActionListener(this);
		JPanel panel = new JPanel();
		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroller);
		
		this.getContentPane().add(BorderLayout.CENTER, panel);
		this.getContentPane().add(BorderLayout.SOUTH, button);
		
	}
	
	public void actionPerformed(ActionEvent ev) {
		Connector connector = Connector.getConnector();
		List list = connector.getSession().createQuery("from Event where date = '2008-10-06'").list();
		Event event = (Event) list.get(0);
		text.append(event.getDescription());
	}

}
