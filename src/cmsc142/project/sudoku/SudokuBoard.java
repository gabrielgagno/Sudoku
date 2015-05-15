package cmsc142.project.sudoku;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
	int puzzleSize;
	private int[][] puzzle;
	private List<int[][]> normalSolution;
	private List<int[][]> xSolution;
	private List<int[][]> ySolution;
	private List<int[][]> xYSolution;
	
	public SudokuBoard(int size, int[][] puzzle) {
		this.puzzleSize = size;
		this.puzzle = puzzle;
		this.normalSolution = new ArrayList<>();
		this.xSolution = new ArrayList<>();
		this.ySolution = new ArrayList<>();
		this.xYSolution = new ArrayList<>();
	}

	public int getPuzzleSize() {
		return puzzleSize;
	}

	public void setPuzzleSize(int puzzleSize) {
		this.puzzleSize = puzzleSize;
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
	
	public void printAllSolutions(){
		System.out.println("Solutions for Normal Sudoku");
		printSolution(normalSolution);
		
		System.out.println("Solutions for X Sudoku");
		printSolution(xSolution);
		
		System.out.println("Solutions for Y Sudoku");
		printSolution(ySolution);
		
		System.out.println("Solutions for XY Sudoku");
		printSolution(xYSolution);
	}
	
	private void printSolution(List<int[][]> solution){
		for(int i = 0; i < solution.size(); i++){
			System.out.println("Solution #" + (i+1));
			for (int j = 0; j < puzzleSize; j++) {
				System.out.println(Arrays.toString(solution.get(i)[j]));
			}
		}
	}
	
	public int getTotalNoOfSolutions(){
		return this.normalSolution.size() + this.xSolution.size() + this.ySolution.size() + this.xYSolution.size();
	}
}