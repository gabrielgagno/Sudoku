package cmsc142.project.sudoku;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SudokuGui extends JFrame {
	private JPanel activePanel;
	
	public SudokuGui(){
		this.activePanel = new JPanel();
		this.setSize(new Dimension(600, 600));
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		 Toolkit toolkit = Toolkit.getDefaultToolkit();
//		  Image image = toolkit.getImage("resources/images/instructors/1.png");
//		  Cursor c = toolkit.createCustomCursor(image , new Point(getContentPane().getX(),
//		     getContentPane().getY()), "img");
//		  setCursor (c);
//		
		centerFrame();
		this.add(activePanel);

	}
	
	private void centerFrame() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	
	}

	public JPanel getActivePanel() {
		return activePanel;
	}


	public void setActivePanel(JPanel activePanel) {
		this.activePanel = activePanel;
	}
	
	public void changePanel(JPanel newPanel){
		this.remove(this.activePanel);
		this.activePanel = newPanel;
		this.add(this.activePanel);
		this.pack();
		this.validate();
		this.repaint();
	}
}
