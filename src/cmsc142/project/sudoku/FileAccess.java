package cmsc142.project.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {
	private File file;
	
	public FileAccess(String fileName) {
		file = new File(fileName);
	}
	
	public ArrayList<SudokuBoard> readBoard() throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		int subGridSize;
		int noOfPuzzle;
		
		String line = bufferedReader.readLine();
		noOfPuzzle = Integer.parseInt(line);
		
		ArrayList<SudokuBoard> puzzles = new ArrayList<>();
		
		while((noOfPuzzle--) != 0 || bufferedReader.ready()){
			line = bufferedReader.readLine();
			subGridSize = Integer.parseInt(line);
			
			int[][] puzzle = new int[subGridSize*subGridSize][subGridSize*subGridSize];
			
			for(int i = 0; i < subGridSize*subGridSize; i++){
				line = bufferedReader.readLine();
				String[] tokens = line.split("\\s+");
				
				for(int j = 0; j < tokens.length; j++){
					puzzle[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			puzzles.add(new SudokuBoard(subGridSize*subGridSize, puzzle));
		}
		
		bufferedReader.close();
		return puzzles;
		
	}
	
	public void writeSolutions(List<SudokuBoard> sudokuBoards){
		
	}
	
}
