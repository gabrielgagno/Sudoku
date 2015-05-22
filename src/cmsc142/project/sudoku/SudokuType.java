package cmsc142.project.sudoku;

public enum SudokuType {
	NORMAL_SUDOKU(0),
	X_SUDOKU(1),
	Y_SUDOKU(2),
	XY_SUDOKU(3);
	
	private int value;
	
	private SudokuType(int value){
		this.value = value;
	}
	
	public int getValue() {
	    return this.value;
	}
	
	@Override
	public String toString(){
		switch (this) {
			case NORMAL_SUDOKU:
				return "Normal Sudoku";
			
			case X_SUDOKU:
				return "X Sudoku";
			
			case Y_SUDOKU:
				return "Y Sudoku";
			
			case XY_SUDOKU:
				return "XY Sudoku";
			
			default:
				return "";
		}
	}

}
