package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
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
		this.menu = new JMenuBar();
		this.fileMenu = new JMenu("File");
		this.fileMenu.setMnemonic(KeyEvent.VK_F);
		this.openFileMenu = new JMenuItem("Open File...");
		
		this.setJMenuBar(menu);
		
		this.sudokuPanel = new JPanel();
		this.initializeTable(4, 4);
		sudokuTable.setFont(new Font("Verdana", Font.BOLD, 50));
		
		JTextField textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.BOLD, 50));
		textField.setBorder(new LineBorder(Color.YELLOW));
		DefaultCellEditor dce = new DefaultCellEditor( textField );
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
		dcr.setFont(new Font("Verdana", Font.BOLD, 50));
		dcr.setBackground(Color.YELLOW);
		sudokuTable.getColumnModel().getColumn(1).setCellEditor(dce);
		sudokuTable.getColumnModel().getColumn(1).setCellRenderer(dcr);
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
		this.sudokuPanel.add(sudokuTable);
	}
}
