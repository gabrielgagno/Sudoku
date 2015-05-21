package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SudokuGui extends JFrame {
	private JPanel sudokuPanel;
	
	private JMenuBar menu;
	
	private JMenu fileMenu;
	
	private JMenuItem openFileMenu;
	
	private JTable sudokuTable;
	
	private JPanel highScorePanel;
	private JLabel highScoreLabel;
	private JLabel highScoreSize;
	private JLabel highScoreType;
	private JTable highScoreTable;
	
	public SudokuGui(){
		this.setSize(new Dimension(700, 700));
		this.setComponents();
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setComponents() {
		createHighScorePanel();
		this.add(highScorePanel);
	}
	
	private void createHighScorePanel(){
		this.highScorePanel = new JPanel();
		this.highScorePanel.setPreferredSize(new Dimension(600, 600));
		
		this.highScoreLabel = new JLabel("Sudoku Highscores");
		this.highScoreSize = new JLabel("Puzzle Size");
		this.highScoreType = new JLabel("Puzzle Type");
		
		
		
		this.highScorePanel.setBackground(Color.YELLOW);
	}

	public JPanel getSudokuPanel() {
		return sudokuPanel;
	}

	public void setSudokuPanel(JPanel sudokuPanel) {
		this.sudokuPanel = sudokuPanel;
	}

	public JMenuBar getMenu() {
		return this.menu;
	}

	public void setMenu(JMenuBar menu) {
		this.menu = menu;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}

	public JMenuItem getOpenFileMenu() {
		return openFileMenu;
	}

	public void setOpenFileMenu(JMenuItem openFileMenu) {
		this.openFileMenu = openFileMenu;
	}

	public JTable getSudokuTable() {
		return sudokuTable;
	}

	public void setSudokuTable(JTable sudokuTable) {
		this.sudokuTable = sudokuTable;
	}
	
	public void initializeTable(int row, int column){
		this.sudokuTable = new JTable(row, column);
		this.sudokuTable.setPreferredSize(new Dimension(400, 400));
		this.sudokuTable.setRowHeight((int) this.sudokuTable.getPreferredSize().getWidth()/this.sudokuTable.getRowCount());
		this.sudokuPanel.add(sudokuTable);
	}
}
