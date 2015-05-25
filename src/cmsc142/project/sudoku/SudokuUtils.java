package cmsc142.project.sudoku;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class SudokuUtils {
	public final static int NORMAL_SUDOKU_INDEX = 0;
	public final static int X_SUDOKU_INDEX = 1;
	public final static int Y_SUDOKU_INDEX = 2;
	public final static int XY_SUDOKU_INDEX = 3;
	
	public static void checkGrid(List<Integer> candidates, int[][] puzzle,
			int puzzleSize, int row, int column) {
		int subGridSize = (int) Math.sqrt(puzzleSize);
		int gridRow = row / subGridSize * (subGridSize);
		int gridColumn = column / subGridSize * (subGridSize);

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
		int center = puzzleSize/2;
		if(puzzleSize%2 == 1 && ((row < center && (row == column || column == puzzleSize-1-row)) || (row >= center && (column == center)))){
			for(int i=0; i<puzzleSize; i++){
				for(int j=0; j<puzzleSize; j++){
					if(puzzle[i][j] != 0 && !(i == row || j == column)){

						if(i >= center && (j == center)){
							candidates.remove(new Integer(puzzle[i][j]));
						}
						
						// Left Wing of Y
						if ((i < center && i == j && (row == column || (row >= center && column == center)))){
							candidates.remove(new Integer(puzzle[i][j]));
						}
						
						// Right Wing of Y
						else if ((i < center && j == puzzleSize-1-i && (column == puzzleSize-1-row || (row >= center && column == center)))){
							candidates.remove(new Integer(puzzle[i][j]));
						}
					}
				}
			}
		}
	}
	
	public static HashSet<Point> checkPuzzle(SudokuBoard sudokuBoard, boolean xSudoku, boolean ySudoku, boolean searchAll){
		HashSet<Point> errorCells = new HashSet<>();
		int puzzleSize = sudokuBoard.getPuzzleSize();
		int[][] puzzle = sudokuBoard.getPuzzle();
		
		for(int i = 0; i < puzzleSize; i++){
			for(int j = 0; j < puzzleSize; j++){
				List<Integer> value = new ArrayList<Integer>();
				if(puzzle[i][j] != 0){
					value.add(new Integer(puzzle[i][j]));
					if(xSudoku) checkX(value, puzzle, puzzleSize, i, j);
					if(ySudoku) checkY(value, puzzle, puzzleSize, i, j);
					
					checkGrid(value, puzzle, puzzleSize, i, j);
					checkRow(value, puzzle, puzzleSize, i, j);
					checkColumn(value, puzzle, puzzleSize, i, j);
					
					if(value.size() == 0){
						errorCells.add(new Point(i, j));
						if(!searchAll){
							return errorCells;
						}
					}
				}
			}
		}
		
		return errorCells;
	}
	
	public static void solveUsingBacktracking(SudokuBoard sudokuBoard, boolean xSudoku, boolean ySudoku) {
		int puzzleSize = sudokuBoard.getPuzzleSize();
		int start = 0;
		int move = 0;
		
		int[][] puzzle = sudokuBoard.getPuzzle();
		int[][] tempPuzzle = new int[puzzleSize][puzzleSize];
		int[] nopts = new int[(puzzleSize * puzzleSize) + 2]; // array top of
																// stacks

		List<ArrayList<Integer>> options = new ArrayList<ArrayList<Integer>>(); // array stacks of options
		
		if(checkPuzzle(sudokuBoard, xSudoku, ySudoku, false).size() == 0){
			for (int i = 0; i < (puzzleSize * puzzleSize) + 2; i++) {
				options.add(new ArrayList<Integer>());
			}
	
			nopts[start] = 1;
	
			tempPuzzle = copyPuzzle(puzzle);
			// Backtracking Algorithm
			// while with possible solution
			while (nopts[start] > 0) {
				if (nopts[move] > 0) {
					move++;

					int row = (move - 1) / puzzleSize;
					int column = (move - 1) % puzzleSize;
	
					nopts[move] = 0; // initialize new arr_size
	
					if (move == (puzzleSize * puzzleSize) + 1) {
						int[][] solution = new int[puzzleSize][puzzleSize];
						solution = copyPuzzle(tempPuzzle);
						
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
						if (puzzle[row][column] == 0) {
							populateOptions(options.get(move), puzzleSize);
	
							if(xSudoku) checkX(options.get(move), tempPuzzle, puzzleSize, row, column);
							if(ySudoku) checkY(options.get(move), tempPuzzle, puzzleSize, row, column);
							
							checkGrid(options.get(move), tempPuzzle, puzzleSize, row, column);
							checkRow(options.get(move), tempPuzzle, puzzleSize, row, column);
							checkColumn(options.get(move), tempPuzzle, puzzleSize, row, column);
							
	
							nopts[move] = options.get(move).size() - 2;
	
							tempPuzzle[row][column] = options.get(move)
									.get(nopts[move]).intValue();
						}
	
						// If there is fixed value in the cell
						else {
							nopts[move] = 1;
							options.get(move).clear();
							options.get(move).add(0);
							options.get(move).add(new Integer(puzzle[row][column]));
							tempPuzzle[row][column] = options.get(move).get(nopts[move]).intValue();
						}
					}
				}
	
				// Backtrack
				else {
					if (move <= (puzzleSize * puzzleSize)) {
						int row = (move - 1) / puzzleSize;
						int column = (move - 1) % puzzleSize;
	
						tempPuzzle[row][column] = puzzle[row][column];
					}
	
					move--;
	
					if (move == 0) {
						break;
					}
	
					int row = (move - 1) / puzzleSize;
					int column = (move - 1) % puzzleSize;
	
					nopts[move]--;
					if (puzzle[row][column] == 0) {
						tempPuzzle[row][column] = options.get(move).get(nopts[move]).intValue();
					} else {
						tempPuzzle[row][column] = puzzle[row][column];
					}
				}
			}
		}
	}

	public static int[][] copyPuzzle(int[][] src) {
		 int[][] out = new int[src.length][src.length];
		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < src[i].length; j++) {
				out[i][j] = src[i][j];
			}
		}
		return out;
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
	
	public static String formatSeconds(int totalSeconds) {
		int hours;
		int minutes;
		int seconds;
		seconds = totalSeconds%60;
		totalSeconds /= 60;
		minutes = totalSeconds%60;
		totalSeconds /= 60;
		hours = totalSeconds;
		String duration = "";
		if(hours < 10)
			duration += 0;
		duration += hours + ":";
		if(minutes < 10)
			duration += 0;
		duration += minutes + ":";
		if(seconds < 10)
			duration += 0;
		duration += seconds;
		return duration;
	}
	
	public static Integer getSeconds(String time) {
		int hours;
		int minutes;
		int seconds;
		int totalSeconds;
		
		String[] timeTokens = time.split(":");
		
		hours = Integer.parseInt(timeTokens[0]);
		minutes = Integer.parseInt(timeTokens[1]);
		seconds = Integer.parseInt(timeTokens[2]);
		
		totalSeconds = seconds;
		totalSeconds += (minutes*60);
		totalSeconds += (hours*60*60);
		
		return new Integer(totalSeconds);
	}


}