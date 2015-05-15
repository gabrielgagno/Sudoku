package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SudokuGuiController implements ActionListener {
	private SudokuGui sudokuGui;
	private List<SudokuBoard> sudokuBoards;
	
	public SudokuGuiController(){
		this.sudokuBoards = new ArrayList<>();
		this.sudokuGui = new SudokuGui();
		this.sudokuGui.getOpenFileMenu().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == sudokuGui.getOpenFileMenu()){
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(this.sudokuGui);
			if(response == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				FileAccess fileAccess = new FileAccess();
				
				try {
					sudokuBoards = fileAccess.readBoard(file.getAbsolutePath());
					
					SudokuBoard currentBoard = sudokuBoards.get(0);
					
					sudokuGui.getSudokuPanel().remove(sudokuGui.getSudokuTable());
					
					int puzzleSize = currentBoard.getPuzzleSize();
					sudokuGui.initializeTable(puzzleSize, puzzleSize);
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
			        sudokuGui.getSudokuTable().setModel(model);
			        sudokuGui.getSudokuTable().setCellSelectionEnabled(true);
			        sudokuGui.getSudokuTable().setColumnSelectionAllowed(false);
			        sudokuGui.getSudokuTable().setRowSelectionAllowed(false);
					
					sudokuGui.getSudokuPanel().validate();
					sudokuGui.getSudokuPanel().repaint();						
				} catch (IOException e) {
					System.out.println("[ Error accessing file! ]" + e.getMessage());
				}

			}
		}
	}
	
}
