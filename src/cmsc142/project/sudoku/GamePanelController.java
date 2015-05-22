package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GamePanelController implements ActionListener, KeyListener{
	GamePanel gamePanel;
	private List<SudokuBoard> sudokuBoards;
	private SudokuBoard currentBoard;
	private HashSet<Point> errorCells;
	int currentPuzzle=0;
	String currentType;
	
	public GamePanelController(){
		this.gamePanel = new GamePanel();
		gamePanel.getBackMenuButton().addActionListener(this);
		currentType = (String) gamePanel.getTypeComboBox().getSelectedItem();
		gamePanel.getTypeComboBox().addActionListener(this);
		gamePanel.getSudokuTable().addKeyListener(this);
		
        gamePanel.getSudokuTable().addKeyListener(this);
        gamePanel.getNextPuzzleButton().addActionListener(this);
        gamePanel.getPrevPuzzleButton().addActionListener(this);
        gamePanel.getCheckerButton().addActionListener(this);
        gamePanel.validate();
        gamePanel.repaint();						
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == gamePanel.getNextPuzzleButton()){
			int response = JOptionPane.showConfirmDialog(gamePanel, (Object)new JLabel("Previous changes will not be saved. Do you really want to proceed to the next puzzle?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.OK_OPTION){
				currentPuzzle++;
				currentBoard = sudokuBoards.get(currentPuzzle);
				drawTable(currentBoard.getPuzzleSize());
				
				currentType = gamePanel.getTypeComboBox().getItemAt(0).toString();
				gamePanel.getTypeComboBox().setSelectedItem(currentType);
				
				if(currentPuzzle == sudokuBoards.size()-1) gamePanel.getNextPuzzleButton().setEnabled(false);
				if(currentPuzzle > 0) gamePanel.getPrevPuzzleButton().setEnabled(true);
			}
		} else if(event.getSource() == gamePanel.getPrevPuzzleButton()){	
			int response = JOptionPane.showConfirmDialog(gamePanel, (Object)new JLabel("Previous changes will not be saved. Do you really want to proceed to the previous puzzle?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.OK_OPTION){
				currentPuzzle--;
				currentBoard = sudokuBoards.get(currentPuzzle);
				drawTable(currentBoard.getPuzzleSize());
				
				currentType = gamePanel.getTypeComboBox().getItemAt(0).toString();
				gamePanel.getTypeComboBox().setSelectedItem(currentType);
				
				if(currentPuzzle <= 0) gamePanel.getPrevPuzzleButton().setEnabled(false);
				if(currentPuzzle < sudokuBoards.size()-1) gamePanel.getNextPuzzleButton().setEnabled(true);
				
			}
		} else if(event.getSource() == gamePanel.getTypeComboBox()){
			if(!gamePanel.getTypeComboBox().getSelectedItem().toString().equals(currentType)){
				int response = JOptionPane.showConfirmDialog(gamePanel, (Object)new JLabel("Previous changes will not be saved. Do you really want to change the puzzle type?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
				if(response == JOptionPane.OK_OPTION){
					currentType = (String) gamePanel.getTypeComboBox().getSelectedItem();
				} else if(response == JOptionPane.CANCEL_OPTION){
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
				}
		
			}
		} else if (event.getSource().equals(gamePanel.getCheckerButton())){
			boolean xSudoku = false;
			boolean ySudoku = false;
			
			switch (currentType) {
				case "X":
					xSudoku = true;
					ySudoku = false;
					break;
	
				case "Y":
					xSudoku = false;
					ySudoku = true;
					break;
				
				case "XY":
					xSudoku = true;
					ySudoku = true;
					break;
			}
			
			errorCells = SudokuUtils.checkPuzzle(currentBoard, xSudoku, ySudoku, true);
		}
	}
	
	public void drawTable(int puzzleSize){
		String data[][] = new String[puzzleSize][puzzleSize];
		String col[] = new String[puzzleSize];
		for(int i=0; i<puzzleSize; i++){
			for(int j=0; j<puzzleSize; j++){
				if(currentBoard.getPuzzle()[i][j] == 0){
					data[i][j] = "";
				}else{
					data[i][j] = String.valueOf(currentBoard.getPuzzle()[i][j]);
				}
			}
			col[i] = "";
		}
		
		DefaultTableModel model = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        gamePanel.getSudokuTable().setModel(model);
        gamePanel.getSudokuTable().setRowHeight((int) gamePanel.getSudokuTable().getPreferredSize().getWidth()/gamePanel.getSudokuTable().getRowCount());
        gamePanel.getSudokuTable().setCellSelectionEnabled(true);
        gamePanel.getSudokuTable().setColumnSelectionAllowed(false);
        gamePanel.getSudokuTable().setRowSelectionAllowed(false);
        CellRender cellRenderer = new CellRender();   // See below
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        gamePanel.getSudokuTable().setDefaultRenderer(Object.class, cellRenderer);
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getKeyCode() >= 49 && event.getKeyCode() < currentBoard.getPuzzleSize()+49){
			int rowIndex = gamePanel.getSudokuTable().getSelectedRow();
			int colIndex = gamePanel.getSudokuTable().getSelectedColumn();
			if(rowIndex >= 0 && colIndex >= 0 && currentBoard.getPuzzle()[rowIndex][colIndex] == 0){
				gamePanel.getSudokuTable().getModel().setValueAt(event.getKeyCode()-48, rowIndex, colIndex);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public class CellRender extends DefaultTableCellRenderer  { 
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column){ 
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		    
		    int fontSize = (int) ((gamePanel.getSudokuTable().getPreferredSize().getWidth()/gamePanel.getSudokuTable().getRowCount())*0.50);
		    if(currentBoard.getPuzzle()[row][column] != 0){
		    	c.setFont(new Font("Verdana", Font.BOLD, fontSize));
		    } else {
		    	c.setFont(new Font("Verdana", Font.PLAIN, fontSize));
		    }
		    
		    if(currentType.equals("Normal")){
			    if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
			    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
			        c.setBackground(new Color(210, 210, 210)); 
			    } else {
			    	c.setBackground(new Color(240,240,240));
			    }
		    }else if(currentType.equals("X")){
		    	if(row==column || column == currentBoard.getPuzzleSize()-1-row){	
					c.setBackground(new Color(0,255,0));
				}else if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
			    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
			        c.setBackground(new Color(210, 210, 210)); 
			    } 
		    	 else {
			    	c.setBackground(new Color(240,240,240));
			    }		
		    }else if(currentType.equals("Y")){
		    	int center = (int) (Math.sqrt(currentBoard.getPuzzleSize())+1);
		    	if( (row < center && (row==column || column == currentBoard.getPuzzleSize()-1-row)) || (row >= center && column == center)){
		    		c.setBackground(new Color(0,255,0));	
		    	}else if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
			    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
			        c.setBackground(new Color(210, 210, 210)); 
			    } 
		    	 else {
			    	c.setBackground(new Color(240,240,240));
			    }		
		    }else if(currentType.equals("XY")){
		    	int center = (int) (Math.sqrt(currentBoard.getPuzzleSize())+1);
		    	if( row==column || column == currentBoard.getPuzzleSize()-1-row || (column == center && row >= center)) {	
		    		c.setBackground(new Color(0,255,0));
		    	}else if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
			    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
			        c.setBackground(new Color(210, 210, 210)); 
			    } 
		    	 else {
			    	c.setBackground(new Color(240,240,240));
			    }		
		    }
		    
		    if(errorCells.contains(new Point(row, column))){
		    	c.setBackground(new Color(210, 0, 0));
		    } 
		    
		    gamePanel.getSudokuTable().repaint();
		    return c; 
		}

	}

	public void initialize(String filePath) {
		// TODO Auto-generated method stub
		FileAccess fileAccess = new FileAccess();
		try {
			sudokuBoards = fileAccess.readBoard(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.errorCells = new HashSet<>(); 
		
//		
		if(sudokuBoards.size() > 0){
			currentBoard = sudokuBoards.get(currentPuzzle);	
			int puzzleSize = currentBoard.getPuzzleSize();
			drawTable(puzzleSize);
			
			if(sudokuBoards.size() > 1) gamePanel.getNextPuzzleButton().setEnabled(true);
		}
        
	}

}
