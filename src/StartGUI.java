import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


/**
 * Class to create a MainFrame and set the window settings
 * */
public class StartGUI implements ActionListener {
	
	JFrame frame;
	Menu menu;
	
	public StartGUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setSize(705, 705);
		frame.setTitle("MURDER MADNESS");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),
						  (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
		frame.setMinimumSize(new Dimension(700, 700));
		
		menu = new Menu(frame);
		menu.setLocation(0,0);
		menu.setSize(frame.getSize());
		menu.setBackground(new Color(102, 204, 255));
		menu.setVisible(true);
		frame.add(menu);
		
	}
	
	public static void main(String[] args) {
		new StartGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
