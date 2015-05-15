package cmsc142.project.sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {
	
	public ArrayList<SudokuBoard> readBoard(String fileName) throws IOException{
		File file = new File(fileName);
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		
		ArrayList<SudokuBoard> puzzles = new ArrayList<>();
		
		int subGridSize;
		int noOfPuzzle;
		
		String line = bufferedReader.readLine();
		
		noOfPuzzle = Integer.parseInt(line);
		
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
	
	public void writeSolutions(List<SudokuBoard> sudokuBoards, String fileName) throws IOException{
		File file = new File(fileName);
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		
		bufferedWriter.write(String.format("%d\n", sudokuBoards.size()));
		
		for(int i = 0; i < sudokuBoards.size(); i++){
			bufferedWriter.write(String.format("Puzzle Size %d\n", sudokuBoards.get(i).getPuzzleSize()));
			bufferedWriter.write(String.format("Total # of Solutions %d\n", sudokuBoards.get(i).getTotalNoOfSolutions()));
			
			bufferedWriter.write(String.format("# of Solutions for Normal Sudoku %d\n", sudokuBoards.get(i).getNormalSolution().size()));
			for(int j = 0; j < sudokuBoards.get(i).getNormalSolution().size(); j++){
				bufferedWriter.write(SudokuUtils.cellsToString(sudokuBoards.get(i).getNormalSolution().get(j)));
			}
			
			bufferedWriter.write(String.format("# of Solutions for X Sudoku %d\n", sudokuBoards.get(i).getxSolution().size()));
			for(int j = 0; j < sudokuBoards.get(i).getxSolution().size(); j++){
				bufferedWriter.write(SudokuUtils.cellsToString(sudokuBoards.get(i).getxSolution().get(j)));
			}
			
			bufferedWriter.write(String.format("# of Solutions for Y Sudoku %d\n", sudokuBoards.get(i).getySolution().size()));
			for(int j = 0; j < sudokuBoards.get(i).getySolution().size(); j++){
				bufferedWriter.write(SudokuUtils.cellsToString(sudokuBoards.get(i).getySolution().get(j)));
			}
			
			bufferedWriter.write(String.format("# of Solutions for XY Sudoku %d\n", sudokuBoards.get(i).getxYSolution().size()));
			for(int j = 0; j < sudokuBoards.get(i).getxYSolution().size(); j++){
				bufferedWriter.write(SudokuUtils.cellsToString(sudokuBoards.get(i).getxYSolution().get(j)));
			}
			
		}
		
		bufferedWriter.close();
	}
}
