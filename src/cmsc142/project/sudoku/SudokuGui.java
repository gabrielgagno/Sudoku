package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

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
		this.menu = new JMenuBar();
		this.fileMenu = new JMenu("File");
		this.fileMenu.setMnemonic(KeyEvent.VK_F);
		this.openFileMenu = new JMenuItem("Open File...");
		
		this.setJMenuBar(menu);
		
		this.sudokuPanel = new JPanel();
		this.initializeTable(4, 4);
		
		this.menu.add(fileMenu);
		this.fileMenu.add(openFileMenu);
		this.add(sudokuPanel);
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
//		this.sudokuTable.setFont(new Font("Serif", Font.BOLD, 50));
		this.sudokuPanel.add(sudokuTable);
	}
}
