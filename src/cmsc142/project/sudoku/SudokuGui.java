package cmsc142.project.sudoku;

import java.awt.Dimension;

import javax.swing.JFrame;

public class SudokuGui extends JFrame {
	private HighScorePanel highScorePanel;
	private MenuPanel menuPanel;
	
	public SudokuGui(){
		this.setSize(new Dimension(700, 700));
		this.setComponents();
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setComponents() {
		this.highScorePanel = new HighScorePanel();
		this.add(highScorePanel);
		
		this.menuPanel = new MenuPanel();
		this.add(menuPanel);
	}
}
