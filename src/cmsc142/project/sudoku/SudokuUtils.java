package cmsc142.project.sudoku;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SudokuUtils {

	public static void checkGrid(List<Integer> candidates, int[][] puzzle,
			int puzzleSize, int row, int column) {
		int subGridSize = (int) Math.sqrt(puzzleSize);
		int gridRow = (int) (row / subGridSize) * (subGridSize);
		int gridColumn = (int) (column / subGridSize) * (subGridSize);

		for (int i = gridRow; i < gridRow + subGridSize; i++) {
			for (int j = gridColumn; j < gridColumn + subGridSize; j++) {
				if (puzzle[i][j] != 0 && !(i == row || j == column)) {
					candidates.remove(new Integer(puzzle[i][j]));
				}
			}
		}

	}

	public static void checkRow(List<Integer> candidates, int[][] puzzle,
			int puzzleSize, int row) {
		for (int i = 0; i < puzzleSize; i++) {
			if(puzzle[row][i] != 0){
				candidates.remove(new Integer(puzzle[row][i]));
			}
		}
	}

	public static void checkColumn(List<Integer> candidates, int[][] puzzle,
			int puzzleSize, int column) {
		for (int i = 0; i < puzzleSize; i++) {
			if(puzzle[i][column] != 0){
				candidates.remove(new Integer(puzzle[i][column]));
			}
		}
	}
	
	public static void checkX(List<Integer> candidates, int[][] puzzle,int puzzleSize, int row, int column) {
		if (row == column || column == (puzzleSize-1)-row) {
			for (int i = 0; i < puzzleSize; i++) {
				for (int j = 0; j < puzzleSize; j++) {
					if(puzzle[i][j] != 0)
						if (i == j || j == (puzzleSize-1)-i)
							candidates.remove(new Integer(puzzle[i][j]));
				}
			}
		}
	}

	private static void solveUsingBacktracking(SudokuBoard sudokuBoard){
		int puzzleSize = sudokuBoard.getSize();
		int start = 0;
		int move = 0;
		int noOfSolutions = 0;
		
		int[][] puzzle = sudokuBoard.getPuzzle();
		int[][] tempPuzzle = new int[puzzleSize][puzzleSize];
		int[] nopts = new int[(puzzleSize*puzzleSize)+2]; 							//array top of stacks
		
		List<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>(); 	//array stacks of options
		
		for(int i = 0; i < (puzzleSize*puzzleSize)+2; i++){
			options.add(new ArrayList<Integer>());
		}
		
		nopts[start]= 1;
		
		for(int i=0; i<puzzleSize; i++){
			for(int j=0; j<puzzleSize; j++){
				tempPuzzle[i][j] = puzzle[i][j];
			}
		}
		// Backtracking Algorithm	
		// while with possible solution
		while (nopts[start] > 0) {
			System.out.println("-----------------------");
			System.out.println("nopts[start] > 0, proceed");
			if(nopts[move] > 0) {	
				move++;
				System.out.println("nopts[move] > 0");
				System.out.printf("MOVE++\nMove = %d\n", move);
				int row = (move-1)/puzzleSize;
				int column = (move-1)%puzzleSize;

				nopts[move] = 0; //initialize new arr_size
				
				if(move == (puzzleSize*puzzleSize)+1){
					noOfSolutions++;
					System.out.println("Solution found!");				
					
					for(int i = 1; i < move; i++){
						System.out.print( options.get(i).get(nopts[i]) + " ");
						if(i%puzzleSize == 0){
							System.out.println();
						}
					}
				} 
				
				// Populate
				else {
					System.out.println("No solution yet, populate");
					if(puzzle[row][column] == 0){
						System.out.println("not a fix value");
						populateOptions(options.get(move), puzzleSize);
						
						checkGrid(options.get(move), tempPuzzle, puzzleSize, row, column);
						checkRow(options.get(move), tempPuzzle, puzzleSize, row);
						checkColumn(options.get(move), tempPuzzle, puzzleSize, column);
						System.out.println("Possible values: " + options.get(move));
						
						nopts[move] = options.get(move).size()-2;
						
//						tempPuzzle[row][column] = puzzle[row][column];
//						System.out.println("R, C "+ row + ", " + column);
//						if(nopts[move] > 0){
							tempPuzzle[row][column] = options.get(move).get(nopts[move]).intValue();
//						}
						System.out.println("After populating");
						for(int i=0; i<puzzleSize; i++){
							System.out.println(Arrays.toString(tempPuzzle[i]));
						}		
					}
					
					// If there is fixed value in the cell
					else{
						System.out.println("fix");
						System.out.println("Skipping " + row + " " + column + " = " + puzzle[row][column]);
						nopts[move] = 1;
						options.get(move).clear();
						options.get(move).add(0);
						options.get(move).add(new Integer(puzzle[row][column]));
						tempPuzzle[row][column] = options.get(move).get(nopts[move]).intValue();
						System.out.println("After skipping...");
						for(int i=0; i<puzzleSize; i++){
							System.out.println(Arrays.toString(tempPuzzle[i]));
						}		
					}
				}
			}

			// Backtrack
			else {
				if(move <= (puzzleSize*puzzleSize)){
					System.out.println("nopts[move] <= 0");
					System.out.println("Backtracking");
					int row = (int)((move-1)/puzzleSize);
					int column = (int)((move-1)%puzzleSize);
					
					tempPuzzle[row][column] = puzzle[row][column];
				}
				
				move--;
				System.out.printf("MOVE--\nMove = %d\n", move);
				int row = (move-1)/puzzleSize;
				int column = (move-1)%puzzleSize;
				
				nopts[move]--;
				System.out.println("------------" + puzzle[row][column]);
				if(puzzle[row][column] == 0){
					System.out.println(options.get(move).get(nopts[move]));
					tempPuzzle[row][column] = options.get(move).get(nopts[move]).intValue();
				} else{
					tempPuzzle[row][column] = puzzle[row][column];
				}
				
				System.out.println("After backtracking");
				for(int i=0; i<puzzleSize; i++){
					System.out.println(Arrays.toString(tempPuzzle[i]));
				}		

			}
		}
	}

	private static void populateOptions(List<Integer> options, int puzzleSize) {
		// Populate first the stack with 1...puzzle_size
		options.clear();
		
		for (int i = 0; i <= puzzleSize; i++) {
			options.add(new Integer(i));
		}
		
		options.add(0);

	}

	public static void findSolutions(List<SudokuBoard> sudokuBoards) {
		for(int i = 0; i < sudokuBoards.size(); i++){
			solveUsingBacktracking(sudokuBoards.get(i));
		}
	}
}
