package cmsc142.project.sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileAccess {
	
	public HashMap<String, ArrayList<ArrayList<String[]>>> readScoreData(String fileName) throws IOException{
		File file = new File(fileName);
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		
		HashMap<String, ArrayList<ArrayList<String[]>>> highscores = new HashMap<>();
		
		while(bufferedReader.ready()){
			String line = bufferedReader.readLine();
			
			String[] tokens = line.split("\\s+");
			
			if(!highscores.containsKey(tokens[2])){
				ArrayList<ArrayList<String[]>> typeList = new ArrayList<>();
				for(int i = 0; i < 4; i++){
					typeList.add(new ArrayList<String[]>());
				}
				highscores.put(tokens[2], typeList);
			
			} 
			
			ArrayList<ArrayList<String[]>> typeList = highscores.get(tokens[2]);
			int index = 0;
			
			switch (tokens[3]) {
				case "N":
					index = 0;
					break;

				case "X":
					index = 1;
					break;
				
				case "Y":
					index = 2;
					break;
				
				case "XY":
					index = 3;
					break;
			}
			
			String[] names = {tokens[0], tokens[1]};
			typeList.get(index).add(names);

		}
		
		bufferedReader.close();
		return highscores;
	}
	
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
