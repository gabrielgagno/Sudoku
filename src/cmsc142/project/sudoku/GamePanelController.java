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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GamePanelController implements ActionListener, KeyListener{
	GamePanel gamePanel;
	private List<SudokuBoard> sudokuBoards;
	private SudokuBoard currentBoard;
	private HashSet<Point> errorCells;
	int currentPuzzle=0;
	String currentType;
	int tickCount = 0;
	private boolean isSpecialSudokuActivated;
	private int[][] currentStateOfTable;
	public GamePanelController(){
		this.gamePanel = new GamePanel();
		this.errorCells = new HashSet<>(); 
		this.currentStateOfTable = new int[currentBoard.puzzleSize][currentBoard.puzzleSize];
		Timer timer = new Timer(1000, this);
		gamePanel.setTimer(timer);
		
		gamePanel.getBackMenuButton().addActionListener(this);
		currentType = (String) gamePanel.getTypeComboBox().getSelectedItem();
		gamePanel.getTypeComboBox().addActionListener(this);
		
		gamePanel.getActivateSpecialButton().addActionListener(this);
        gamePanel.getSudokuTable().addKeyListener(this);
        gamePanel.getSolverButton().addActionListener(this);
        gamePanel.getNextPuzzleButton().addActionListener(this);
        gamePanel.getPrevPuzzleButton().addActionListener(this);
        gamePanel.getCheckerButton().addActionListener(this);
        gamePanel.getResetButton().addActionListener(this);
        gamePanel.validate();
        gamePanel.repaint();						
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(gamePanel.getSolverButton())){
			String[] optionLabels = { "Solve the current state.", "Solve the original state.", "Let me solve the puzzle!"};    
			int response = JOptionPane.showOptionDialog(gamePanel, "Do you want to solve the puzzle using the current state or the original state?",  "Solve Puzzle Confirmation",  JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionLabels, optionLabels[0]);
			
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
			
			if(response == JOptionPane.OK_OPTION || response == JOptionPane.NO_OPTION){
				SudokuBoard board = currentBoard;
				
				if(response == JOptionPane.YES_OPTION){
					board = new SudokuBoard(currentBoard.getPuzzleSize(), getCurrentPuzzle());
					SudokuUtils.solveUsingBacktracking(board, xSudoku, ySudoku);
				}
				
				List<int[][]> solution =  null;
				if(xSudoku){
					if(ySudoku){
						solution = currentBoard.getxYSolution();
					} else {
						solution = currentBoard.getxSolution();
					}
				} else {
					if(ySudoku){
						solution = currentBoard.getySolution();
					} else {
						solution = currentBoard.getNormalSolution();
					}
				}				
				
				drawTable(solution.get(0));
			}
			
		} else if(event.getSource() == gamePanel.getNextPuzzleButton()){
			int response = JOptionPane.showConfirmDialog(gamePanel, (Object)new JLabel("Previous changes will not be saved. Do you really want to proceed to the next puzzle?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
			if(response == JOptionPane.OK_OPTION){
				currentPuzzle++;
				currentBoard = sudokuBoards.get(currentPuzzle);
				drawTable(currentBoard.getPuzzle());
				
				this.errorCells = new HashSet<>();
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
				drawTable(currentBoard.getPuzzle());
				
				this.errorCells = new HashSet<>(); 
				currentType = gamePanel.getTypeComboBox().getItemAt(0).toString();
				gamePanel.getTypeComboBox().setSelectedItem(currentType);
				
				if(currentPuzzle <= 0) gamePanel.getPrevPuzzleButton().setEnabled(false);
				if(currentPuzzle < sudokuBoards.size()-1) gamePanel.getNextPuzzleButton().setEnabled(true);			
			}
		} else if(event.getSource() == gamePanel.getTypeComboBox()){
			if(gamePanel.getTypeComboBox().getSelectedItem().toString().equals("X")){
				if(SudokuUtils.checkPuzzle(currentBoard, true, false, false).size() != 0){
					JOptionPane.showMessageDialog(gamePanel, "Puzzle not applicable for X Sudoku");
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
					return;
				}
				
				if(currentBoard.getxSolution().size() == 0){
					JOptionPane.showMessageDialog(gamePanel, "Puzzle has no solution for X Sudoku");
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
					return;
				}
			} else if(gamePanel.getTypeComboBox().getSelectedItem().toString().equals("Y")){
				if(currentBoard.getPuzzleSize()%2 == 0 || SudokuUtils.checkPuzzle(currentBoard, false, true, false).size() != 0){
					JOptionPane.showMessageDialog(gamePanel, "Puzzle not applicable for Y Sudoku");
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
					return;
				}
				
				if(currentBoard.getySolution().size() == 0){
					JOptionPane.showMessageDialog(gamePanel, "Puzzle has no solution for Y Sudoku");
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
					return;
				}
				
			} else if(gamePanel.getTypeComboBox().getSelectedItem().toString().equals("XY")){
				if(currentBoard.getPuzzleSize()%2 == 0 || SudokuUtils.checkPuzzle(currentBoard, true, true, false).size() != 0){
					JOptionPane.showMessageDialog(gamePanel, "Puzzle not applicable for XY Sudoku");
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
					return;
				}
				
				if(currentBoard.getxYSolution().size() == 0){
					JOptionPane.showMessageDialog(gamePanel, "Puzzle has no solution for XY Sudoku");
					gamePanel.getTypeComboBox().setSelectedItem(currentType);
					return;
				}
			}
		
			if(!gamePanel.getTypeComboBox().getSelectedItem().toString().equals(currentType)){
				int response = JOptionPane.showConfirmDialog(gamePanel, (Object)new JLabel("Previous changes will not be saved. Do you really want to change the puzzle type?"), "Warning!", JOptionPane.OK_CANCEL_OPTION);
				if(response == JOptionPane.OK_OPTION){
					currentType = (String) gamePanel.getTypeComboBox().getSelectedItem();

					drawTable(currentBoard.getPuzzle());
					
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
			
			SudokuBoard currentStateOfBoard = new SudokuBoard(currentBoard.getPuzzleSize(), getCurrentPuzzle());
			errorCells = SudokuUtils.checkPuzzle(currentStateOfBoard, xSudoku, ySudoku, true);
		} else if(event.getSource() == gamePanel.getResetButton()){
			drawTable(currentBoard.getPuzzle());
		} else if (event.getSource().equals(gamePanel.getActivateSpecialButton())){
			isSpecialSudokuActivated = !isSpecialSudokuActivated;
			ImageIcon imageIcon = new ImageIcon("resources/load.png");
			gamePanel.getSudokuTable().setValueAt(imageIcon, 0, 0);
		}
		
		if(event.getSource() == gamePanel.getTimer()){
			tickCount++;
			gamePanel.getTimerLabel().setText(gamePanel.computeDuration(tickCount));
		}
	}
	
	private int[][] getCurrentPuzzle(){
		int[][] puzzle = new int[currentBoard.getPuzzleSize()][currentBoard.getPuzzleSize()];
		
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				String element = (String) gamePanel.getSudokuTable().getValueAt(i, j);
				if(element.equals("")){
					element = "0";
				}
				puzzle[i][j] = Integer.parseInt(element);
			}
		}
		
		return puzzle;
	}
	
	public void drawTable(int[][] puzzle){
		int puzzleSize = puzzle.length;
		String data[][] = new String[puzzleSize][puzzleSize];
		String header[] = new String[puzzleSize];
		for(int i = 0; i < puzzleSize; i++){
			for(int j = 0;  j < puzzleSize; j++){
				if(puzzle[i][j] == 0){
					data[i][j] = "";
				} else{
					data[i][j] = String.valueOf(puzzle[i][j]);
				}
			}
			header[i] = "";
		}
		
		DefaultTableModel model = new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
            	if(isSpecialSudokuActivated){
            		return Icon.class;
            	} else {
            		return String.class;
            	}
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
		int keyCode = event.getKeyCode();
		
		if(keyCode >= 49 && keyCode < currentBoard.getPuzzleSize()+49){
			int rowIndex = gamePanel.getSudokuTable().getSelectedRow();
			int colIndex = gamePanel.getSudokuTable().getSelectedColumn();
			if(rowIndex >= 0 && colIndex >= 0 && currentBoard.getPuzzle()[rowIndex][colIndex] == 0){
				currentStateOfTable[rowIndex][colIndex] = keyCode;
				
				if(isSpecialSudokuActivated){
					gamePanel.getSudokuTable().getModel().setValueAt(new ImageIcon("resources/images/instructors/" + (keyCode-48) + ".png"), rowIndex, colIndex);
				} else {
					gamePanel.getSudokuTable().getModel().setValueAt(String.valueOf(keyCode-48), rowIndex, colIndex);
				}
			}
			
			if(currentType.equals("Normal")){
				errorCells = SudokuUtils.checkPuzzle(currentBoard, false, false, true);
			}else if(currentType.equals("X")){
				errorCells = SudokuUtils.checkPuzzle(currentBoard, true, false, true);
			}else if(currentType.equals("Y")){
				errorCells = SudokuUtils.checkPuzzle(currentBoard, false, true, true);
			}else if(currentType.equals("XY")){
				errorCells = SudokuUtils.checkPuzzle(currentBoard, true, true, true);
			}
			if(errorCells.isEmpty()){
				int [][] checkComplete = this.getCurrentPuzzle();
				boolean flag = true;
				for(int i=0;i<checkComplete.length;i++){
					for(int j=0;j<checkComplete[i].length;j++){
						if(checkComplete[i][j]==0) flag = false;
					}
				}
				if(flag){
					String[] options = {"OK"};
					JPanel panel = new JPanel();
					JLabel label = new JLabel("Enter Your name : ");
					JTextField txt = new JTextField(10);
					panel.add(label);
					panel.add(txt);
					gamePanel.getSudokuTable().removeKeyListener(this);
					JOptionPane.showOptionDialog(null, panel, "You solved the puzzle in " + tickCount + " seconds!", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
					
					FileAccess fileAccess = new FileAccess();
					try {
						fileAccess.writeScore(new String[]{txt.getText(), tickCount + "", currentBoard.getPuzzleSize() + "", currentType}, "resources/highscores.dat");
					} catch (IOException e) {
						System.out.println("[ Error writing high socres! ]");
					}
					
				}
			}
		}else if(keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE){
			int rowIndex = gamePanel.getSudokuTable().getSelectedRow();
			int colIndex = gamePanel.getSudokuTable().getSelectedColumn();
			if(rowIndex >= 0 && colIndex >= 0 && currentBoard.getPuzzle()[rowIndex][colIndex] == 0){
				gamePanel.getSudokuTable().getModel().setValueAt("", rowIndex, colIndex);
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
		    
	        if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
		    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
		        c.setBackground(new Color(210, 210, 210)); 
		    } else {
		    	c.setBackground(new Color(240,240,240));
		    }
	        
		    if(currentType.equals("X") || currentType.equals("XY")){
		    	if(row==column || column == currentBoard.getPuzzleSize()-1-row){	
					c.setBackground(new Color(0,255,0));
		    	}
			}
		    if(currentType.equals("Y") || currentType.equals("XY")){
		    	int center = (int) (Math.sqrt(currentBoard.getPuzzleSize())+1);
		    	if( (row < center && (row==column || column == currentBoard.getPuzzleSize()-1-row)) || (row >= center && column == center)){
		    		c.setBackground(new Color(0,255,0));	
		    	}
			}
		    
		    if(errorCells.contains(new Point(row, column))){
		    	c.setBackground(new Color(210, 0, 0));
		    } 
		    
//		    JLabel label = (JLabel) c;
//	    	System.out.println(table.getValueAt(0, 0).getClass());
		    gamePanel.getSudokuTable().repaint();
		    
		    return c; 
		}
	}
	
	
	public void initialize(String filePath)  {
		FileAccess fileAccess = new FileAccess();
		try {
			sudokuBoards = fileAccess.readBoard(filePath);
			SudokuUtils.findSolutions(sudokuBoards);
		} catch (IOException e) {
			System.out.println("[ Error reading file! ]");
		}
		
		if(sudokuBoards.size() > 0){
			currentBoard = sudokuBoards.get(currentPuzzle);	
			int puzzleSize = currentBoard.getPuzzleSize();
			drawTable(currentBoard.getPuzzle());
			
			if(sudokuBoards.size() > 1) gamePanel.getNextPuzzleButton().setEnabled(true);
			gamePanel.getTimer().start();
		}
        
	}
}
