package cmsc142.project.sudoku;


import java.util.ArrayList;
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

	public static void checkRow(List<Integer> candidates, int[][] puzzle, int puzzleSize, int row, int column) {
		for (int i = 0; i < puzzleSize; i++) {
			if (puzzle[row][i] != 0  && i != column) {
				candidates.remove(new Integer(puzzle[row][i]));
			}
		}
	}

	public static void checkColumn(List<Integer> candidates, int[][] puzzle, int puzzleSize, int row, int column) {
		for (int i = 0; i < puzzleSize; i++) {
			if (puzzle[i][column] != 0  && i != row) {
				candidates.remove(new Integer(puzzle[i][column]));
			}
		}
	}
	
	public static void checkX(List<Integer> candidates, int[][] puzzle,int puzzleSize, int row, int column) {
		if((row == column || column == (puzzleSize-1)-row)) {
			for (int i = 0; i < puzzleSize; i++) {
				for (int j = 0; j < puzzleSize; j++) {
					if(puzzle[i][j] != 0  && !(i == row || j == column) && ((i == j && row == column) || (j == (puzzleSize-1)-i && column == (puzzleSize-1)-row))){	
						candidates.remove(new Integer(puzzle[i][j]));
					}
				}
			}
		}
	}

	public static void checkY(List<Integer> candidates, int[][] puzzle, int puzzleSize, int row, int column){
		int center = (int) (Math.sqrt(puzzleSize)+1);
		if(puzzleSize%2 == 1 && ((row < center && (row == column || column == puzzleSize-1-row)) || (row >= center && (column == center)))){
			for(int i=0; i<puzzleSize; i++){
				for(int j=0; j<puzzleSize; j++){
					if(puzzle[i][j] != 0 && !(i == row || j == column)){
						
						// Always check the center
						if(i >= center && (j == center)){
							candidates.remove(new Integer(puzzle[i][j]));
						}
						
						// Left Wing of Y
						if (i < center && i == j && row == column){
							candidates.remove(new Integer(puzzle[i][j]));
						}
						
						// Right Wing of Y
						else if (i < center && j == puzzleSize-1-i && column == puzzleSize-1-row){
							candidates.remove(new Integer(puzzle[i][j]));
						}
					}
				}
			}
		}
	}

	private static void solveUsingBacktracking(SudokuBoard sudokuBoard, boolean xSudoku, boolean ySudoku) {
		int puzzleSize = sudokuBoard.getPuzzleSize();
		int start = 0;
		int move = 0;
		
		int[][] puzzle = sudokuBoard.getPuzzle();
		int[][] tempPuzzle = new int[puzzleSize][puzzleSize];
		int[] nopts = new int[(puzzleSize * puzzleSize) + 2]; // array top of
																// stacks

		List<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>(); // array
																				// stacks
																				// of
																				// options
		boolean flag = true;
		for(int i=0; i<puzzleSize; i++){
			for(int j=0; j<puzzleSize; j++){
				ArrayList<Integer> value = new ArrayList<Integer>();
				if(puzzle[i][j] != 0){
					value.add(new Integer(puzzle[i][j]));
//					System.out.println(value);
					if(xSudoku) checkX(value, puzzle, puzzleSize, i, j);
					if(ySudoku) checkY(value, puzzle, puzzleSize, i, j);
					
					checkGrid(value, puzzle, puzzleSize, i, j);
					checkRow(value, puzzle, puzzleSize, i, j);
					checkColumn(value, puzzle, puzzleSize, i, j);
//					System.out.println(value);
					if(value.size() == 0){
						flag = false;
						break;
					}
				}
			}
			if(!flag){
				break;
			}
		}
		
		if(flag){
			System.out.println("Proceed");
			for (int i = 0; i < (puzzleSize * puzzleSize) + 2; i++) {
				options.add(new ArrayList<Integer>());
			}
	
			nopts[start] = 1;
	
			copyPuzzle(tempPuzzle, puzzle);
			// Backtracking Algorithm
			// while with possible solution
			while (nopts[start] > 0) {
	//			System.out.println("-----------------------");
	//			System.out.println("nopts[start] > 0, proceed");
				if (nopts[move] > 0) {
					move++;
	//				System.out.println("nopts[move] > 0");
	//				System.out.printf("MOVE++\nMove = %d\n", move);
					int row = (move - 1) / puzzleSize;
					int column = (move - 1) % puzzleSize;
	
					nopts[move] = 0; // initialize new arr_size
	
					if (move == (puzzleSize * puzzleSize) + 1) {
	//					System.out.println("Solution found!");
						int[][] solution = new int[puzzleSize][puzzleSize];
						copyPuzzle(solution, tempPuzzle);
						
						if(xSudoku){
							if(ySudoku){
								sudokuBoard.getxYSolution().add(solution);
							} else {
								sudokuBoard.getxSolution().add(solution);
							}
						} else {
							if(ySudoku){
								sudokuBoard.getySolution().add(solution);
							} else {
								sudokuBoard.getNormalSolution().add(solution);
							}
						}
					}
	
					// Populate
					else {
	//					System.out.println("No solution yet, populate");
						if (puzzle[row][column] == 0) {
	//						System.out.println("not a fix value");
							populateOptions(options.get(move), puzzleSize);
	
							if(xSudoku) checkX(options.get(move), tempPuzzle, puzzleSize, row, column);
							if(ySudoku) checkY(options.get(move), tempPuzzle, puzzleSize, row, column);
							
							checkGrid(options.get(move), tempPuzzle, puzzleSize, row, column);
							checkRow(options.get(move), tempPuzzle, puzzleSize, row, column);
							checkColumn(options.get(move), tempPuzzle, puzzleSize, row, column);
							
	//						System.out.println("Possible values: " + options.get(move));
	
							nopts[move] = options.get(move).size() - 2;
	
							tempPuzzle[row][column] = options.get(move)
									.get(nopts[move]).intValue();
	//						System.out.println("After populating");
	//						for (int i = 0; i < puzzleSize; i++) {
	//							System.out.println(Arrays.toString(tempPuzzle[i]));
	//						}
						}
	
						// If there is fixed value in the cell
						else {
							System.out.println("Fix");
							System.out.println("Skipping " + row + " " + column + " = " + puzzle[row][column]);
							nopts[move] = 1;
							options.get(move).clear();
							options.get(move).add(0);
							options.get(move).add(new Integer(puzzle[row][column]));
							tempPuzzle[row][column] = options.get(move)
									.get(nopts[move]).intValue();
							System.out.println("After skipping...");
	//						for (int i = 0; i < puzzleSize; i++) {
	//							System.out.println(Arrays.toString(tempPuzzle[i]));
	//						}
						}
					}
				}
	
				// Backtrack
				else {
					if (move <= (puzzleSize * puzzleSize)) {
	//					System.out.println("nopts[move] <= 0");
	//					System.out.println("Backtracking");
						int row = (int) ((move - 1) / puzzleSize);
						int column = (int) ((move - 1) % puzzleSize);
	
						tempPuzzle[row][column] = puzzle[row][column];
					}
	
					move--;
	
					if (move == 0) {
						break;
					}
	
	//				System.out.printf("MOVE--\nMove = %d\n", move);
					int row = (move - 1) / puzzleSize;
					int column = (move - 1) % puzzleSize;
	
					nopts[move]--;
					if (puzzle[row][column] == 0) {
	//					System.out.println(options.get(move).get(nopts[move]));
						tempPuzzle[row][column] = options.get(move)
								.get(nopts[move]).intValue();
					} else {
						tempPuzzle[row][column] = puzzle[row][column];
					}
	
	//				System.out.println("After backtracking");
	//				for (int i = 0; i < puzzleSize; i++) {
	//					System.out.println(Arrays.toString(tempPuzzle[i]));
	//				}
	
				}
			}
		}
	}

	private static void copyPuzzle(int[][] out, int[][] src) {
		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < src[i].length; j++) {
				out[i][j] = src[i][j];
			}
		}

	}

	private static void populateOptions(List<Integer> options, int puzzleSize) {
		options.clear();

		for (int i = 0; i <= puzzleSize; i++) {
			options.add(new Integer(i));
		}

		options.add(0);

	}

	public static void findSolutions(List<SudokuBoard> sudokuBoards) {
		for (int i = 0; i < sudokuBoards.size(); i++) {
			System.out.println("Solving puzzle #" + (i+1));
			solveUsingBacktracking(sudokuBoards.get(i), false, false);
			
			solveUsingBacktracking(sudokuBoards.get(i), true, false);
			
			if(sudokuBoards.get(i).getPuzzleSize() % 2 == 1){
				solveUsingBacktracking(sudokuBoards.get(i), false, true);
				solveUsingBacktracking(sudokuBoards.get(i), true, true);
			}
		}
	}
	
	public static String cellsToString(int[][] cells){
		String str = "";
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				str += (cells[i][j] + " ");
			}
			str += "\n";
		}
		
		str+= "\n";
		
		return str;
	}
}