package cmsc142.project.sudoku;

import java.util.List;

public abstract class SudokuUtils {
	
	public static void checkGrid(List<Integer> candidates, int puzzleSize, int row, int column){
		
	}
	
	public static void checkRow(){
		
	}

	public static void checkColumn(List<Integer> candidates, int[][] puzzle, int puzzleSize, int column){
		for(int i=0; i<puzzleSize;i++){
			if(puzzle[i][column] !=0)
					candidates.remove(puzzle[i][column]);
		}
	}
}
