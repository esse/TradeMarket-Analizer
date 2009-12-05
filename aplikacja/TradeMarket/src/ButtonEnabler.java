
public class ButtonEnabler implements Runnable {

	public javax.swing.JButton button;
	public Thread first;
	public Thread second;
	
	public ButtonEnabler(javax.swing.JButton _button, Thread _first, Thread _second) {
		button = _button;
		first = _first;
		second = _second;		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		go();
	}
	
	public void go(){
		try {
		first.join();
		second.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		button.setEnabled(true);
		}
	}

}
