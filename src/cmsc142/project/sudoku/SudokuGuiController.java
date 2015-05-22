package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SudokuGuiController implements ActionListener, KeyListener{
	private SudokuGui sudokuGui;
	private StartPanelController startPanelController;
	private HighScorePanelController highScorePanelController;
	private GamePanelController gamePanelController;
	
	public SudokuGuiController(){
		this.sudokuGui = new SudokuGui();
		
		this.highScorePanelController = new HighScorePanelController(sudokuGui);
		this.startPanelController = new StartPanelController(sudokuGui);
		this.gamePanelController = new GamePanelController(sudokuGui);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == sudokuGui.getOpenFileMenu()){
//			JFileChooser fileChooser = new JFileChooser();
//			int response = fileChooser.showOpenDialog(this.sudokuGui);
//			if(response == JFileChooser.APPROVE_OPTION){
//				File file = fileChooser.getSelectedFile();
//				FileAccess fileAccess = new FileAccess();
//				
//				try {
//					errorCells.add(new Point(1, 2));
//					
//					sudokuBoards = fileAccess.readBoard(file.getAbsolutePath());
//					
//					currentBoard = sudokuBoards.get(0);
//					
//					sudokuGui.getSudokuPanel().remove(sudokuGui.getSudokuTable());
//					
//					int puzzleSize = currentBoard.getPuzzleSize();
//					sudokuGui.initializeTable(puzzleSize, puzzleSize);
//					
//					String data[][] = new String[puzzleSize][puzzleSize];
//					String col[] = new String[puzzleSize];
//					for(int i=0; i<puzzleSize; i++){
//						for(int j=0; j<puzzleSize; j++){
//							if(currentBoard.getPuzzle()[i][j] == 0){
//								data[i][j] = "";
//							}else{
//								data[i][j] = String.valueOf(currentBoard.getPuzzle()[i][j]);
//							}
//						}
//						col[i] = "";
//					}
//					
//					DefaultTableModel model = new DefaultTableModel(data, col) {
//			            @Override
//			            public boolean isCellEditable(int row, int col) {
//			                return false;
//			            }
//			        };
//			        
//			        sudokuGui.getSudokuTable().setModel(model);
//			        sudokuGui.getSudokuTable().setCellSelectionEnabled(true);
//			        sudokuGui.getSudokuTable().setColumnSelectionAllowed(false);
//			        sudokuGui.getSudokuTable().setRowSelectionAllowed(false);
//			        CellRender cellRenderer = new CellRender();   // See below
//			        sudokuGui.getSudokuTable().setDefaultRenderer(Object.class, cellRenderer);
//			        
//			        sudokuGui.getSudokuTable().addKeyListener(this);
//					sudokuGui.getSudokuPanel().validate();
//					sudokuGui.getSudokuPanel().repaint();						
//				} catch (IOException e) {
//					System.out.println("[ Error accessing file! ]" + e.getMessage());
//				}
//
//			}
//		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
//		if(event.getKeyCode() <= 57 && event.getKeyCode() > 48 && sudokuGui.getSudokuTable().getSelectedRowCount() == 1 && sudokuGui.getSudokuTable().getSelectedColumnCount() == 1){
//			int row = sudokuGui.getSudokuTable().getSelectedRow();
//			int column = sudokuGui.getSudokuTable().getSelectedColumn();
//			if(currentBoard.getPuzzle()[row][column] == 0){
//				sudokuGui.getSudokuTable().setValueAt(event.getKeyChar(), row, column);
//			}
//		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
	}
	
//	public class CellRender extends DefaultTableCellRenderer  { 
//	    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column){ 
//		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
//		    errorCells.add(new Point(table.getSelectedRow(), table.getSelectedColumn()));
//		    if(errorCells.contains(new Point(row, column))){
//		    	c.setBackground(new Color(210, 0, 0));
//		    } else if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
//		    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
//		        c.setBackground(new Color(210, 210, 210)); 
//		    } else {
//		    	c.setBackground(new Color(240,240,240));
//		    }
//		    return c; 
//		}
//
//	} 	
}
