package cmsc142.project.sudoku;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SudokuGui extends JFrame {
	
	public SudokuGui(){
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
