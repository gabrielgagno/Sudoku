package cmsc142.project.sudoku;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SudokuGui extends JFrame {
	private JPanel activePanel;
	
	public SudokuGui(){
		this.activePanel = new JPanel();
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(activePanel);
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
	}
}
