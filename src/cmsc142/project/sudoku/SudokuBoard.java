package cmsc142.project.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
	int size;
	private int[][] puzzle;
	private List<int[][]> normalSolution;
	private List<int[][]> xSolution;
	private List<int[][]> ySolution;
	private List<int[][]> xYSolution;

	public SudokuBoard(int size, int[][] puzzle) {
		this.size = size;
		this.puzzle = puzzle;
		this.normalSolution = new ArrayList<>();
		this.xSolution = new ArrayList<>();
		this.ySolution = new ArrayList<>();
		this.xYSolution = new ArrayList<>();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(int[][] puzzle) {
		this.puzzle = puzzle;
	}

	public List<int[][]> getNormalSolution() {
		return normalSolution;
	}

	public void setNormalSolution(List<int[][]> normalSolution) {
		this.normalSolution = normalSolution;
	}

	public List<int[][]> getxSolution() {
		return xSolution;
	}

	public void setxSolution(List<int[][]> xSolution) {
		this.xSolution = xSolution;
	}

	public List<int[][]> getySolution() {
		return ySolution;
	}

	public void setySolution(List<int[][]> ySolution) {
		this.ySolution = ySolution;
	}

	public List<int[][]> getxYSolution() {
		return xYSolution;
	}

	public void setxYSolution(List<int[][]> xYSolution) {
		this.xYSolution = xYSolution;
	}
	
	public void printNormalSolutions(){
		for(int i = 0; i < normalSolution.size(); i++){
			System.out.println("Solution #" + (i+1));
			for (int j = 0; j < size; j++) {
				System.out.println(Arrays.toString(normalSolution.get(i)[j]));
			}
		}
	}

}
