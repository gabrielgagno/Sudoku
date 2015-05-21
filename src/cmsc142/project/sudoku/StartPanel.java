package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private JLabel sudoku;
	private JButton newGame;
	private JButton highScore;
	private JButton exit;
	
	public StartPanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setComponents();
		this.setVisible(true);
		this.setFocusable(true);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 40));
	}
	
	private void setComponents(){
		sudoku = new JLabel("Sudoku");
		newGame = new JButton("New Game");
		highScore = new JButton("High Score");
		exit = new JButton("Exit");
		
		sudoku.setFont(new Font("Serif", Font.BOLD, 100));
		sudoku.setPreferredSize(new Dimension(400,250));
		newGame.setPreferredSize(new Dimension(200,50));
		highScore.setPreferredSize(new Dimension(200,50));
		exit.setPreferredSize(new Dimension(200,50));
		
		this.add(sudoku);
		this.add(newGame);
		this.add(highScore);
		this.add(exit);
		
		
	}
}

