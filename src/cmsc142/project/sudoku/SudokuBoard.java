package cmsc142.project.sudoku;

public class SudokuBoard {
	int size;
	private int[][] puzzle;
	private int[][] normalSolution;
	private int[][] xSolution;
	private int[][] ySolution;
	private int[][] xYSolution;
	
	public SudokuBoard(int size, int[][] puzzle){
		this.size = size;
		this.puzzle = puzzle;
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

	public int[][] getNormalSolution() {
		return normalSolution;
	}

	public void setNormalSolution(int[][] normalSolution) {
		this.normalSolution = normalSolution;
	}

	public int[][] getxSolution() {
		return xSolution;
	}

	public void setxSolution(int[][] xSolution) {
		this.xSolution = xSolution;
	}

	public int[][] getySolution() {
		return ySolution;
	}

	public void setySolution(int[][] ySolution) {
		this.ySolution = ySolution;
	}

	public int[][] getxYSolution() {
		return xYSolution;
	}

	public void setxYSolution(int[][] xYSolution) {
		this.xYSolution = xYSolution;
	}

}
