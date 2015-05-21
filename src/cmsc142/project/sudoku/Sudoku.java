package cmsc142.project.sudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Sudoku {

	private static final String OUTPUT_FILE = "resources/solution.out";
	private static final String INPUT_FILE = "resources/input.in";

	public static void main(String[] args) {
		SudokuGuiController sudokuGuiController = new SudokuGuiController();
		
		FileAccess fileAccess = new FileAccess();
		try {
			HashMap<String, ArrayList<ArrayList<String[]>>> highscores = fileAccess.readScoreData("resources/highscores.dat");
			
			Object[] keys = highscores.keySet().toArray();
			
			for (int i = 0; i < keys.length; i++) {
				System.out.println(keys[i]);
				ArrayList<ArrayList<String[]>> highScorePerPuzzleType = highscores.get(keys[i]);
				
				for(int j = 0; j < highScorePerPuzzleType.size(); j++){
					System.out.println("Puzzle Type " + j);
					for(int k = 0; k < highScorePerPuzzleType.get(j).size(); k++){
						System.out.println(highScorePerPuzzleType.get(j).get(k));
					}
				}
			}
			
		} catch (IOException e) {
			System.out.println("Error reading file! " + e.getMessage());
		}
		
//		try {
//			ArrayList<SudokuBoard> puzzles = fileAccess.readBoard(INPUT_FILE);
//
//			SudokuUtils.findSolutions(puzzles);
//			fileAccess.writeSolutions(puzzles, OUTPUT_FILE);
//
////			for (int i = 0; i < puzzles.size(); i++) {
////				puzzles.get(i).printAllSolutions();
////			}
//		} catch (IOException e) {
//			System.out.println("[ Error accessing file! ]" + e.getMessage());
//		}

	}

}