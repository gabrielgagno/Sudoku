package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class SudokuGui extends JFrame {
	private JPanel sudokuPanel;
	
	private JMenuBar menu;
	
	private JMenu fileMenu;
	
	private JMenuItem openFileMenu;
	
	private JTable sudokuTable;
	
	public SudokuGui(){
		this.setSize(new Dimension(700, 700));
		this.setComponents();
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setComponents() {
		
	}
	
	private void createHighScorePanel(){
		
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
