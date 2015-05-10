package cmsc142.project.sudoku;

import java.io.IOException;
import java.util.ArrayList;

public class Sudoku {
	
	public static void main(String[] args){
		FileAccess fileAccess = new FileAccess("resources/input.in");
		
		try {
			ArrayList<SudokuBoard> puzzles = fileAccess.readBoard();
			
			SudokuUtils.findSolutions(puzzles);
			for(int i = 0; i < puzzles.size(); i++){
				System.out.println("---------------------------------------------------------------");
				System.out.println("Puzzle #" + (i+1));
				System.out.println("Normal Solutions");
				puzzles.get(i).printSolutions(puzzles.get(i).getNormalSolution());
				
				System.out.println("X Solutions");
				puzzles.get(i).printSolutions(puzzles.get(i).getxSolution());
				
				System.out.println("Y Solutions");
				puzzles.get(i).printSolutions(puzzles.get(i).getySolution());
				
				System.out.println("XY Solutions");
				puzzles.get(i).printSolutions(puzzles.get(i).getxYSolution());
				
				System.out.println();
			}
			
//			for(int i = 0; i < puzzles.size(); i++){
//				int puzzleSize = puzzles.get(i).getSize();
//				int puzzle[][] = puzzles.get(i).getPuzzle();
//				for(int j = 0; j < puzzleSize ; j++){
//					for(int k = 0; k < puzzleSize; k++){
//						System.out.print(puzzle[j][k] + " ");
//					}
//					System.out.println();
//				}
//			}
			
			
			
		} catch (IOException e) {
			System.out.println("Error reading file! " + e.getMessage() + "\n");
		}
		
	}
	
}
