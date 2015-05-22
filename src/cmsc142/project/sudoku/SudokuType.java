package cmsc142.project.sudoku;

public enum SudokuType {
	Normal(0),
	X(1),
	Y(2),
	XY(3);
	
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
			case Normal:
				return "Normal";
			
			case X:
				return "X";
			
			case Y:
				return "Y";
			
			case XY:
				return "XY";
			
			default:
				return "";
		}
	}
	
	public String toStringAbbrev(){
		switch (this) {
			case Normal:
				return "N";
			
			case X:
				return "X";
			
			case Y:
				return "Y";
			
			case XY:
				return "XY";
			
			default:
				return "";
		}
	}

	public static String[] getValues() {
		String[] values = new String[values().length];
		
		for(int i = 0; i < values().length; i++){
			values[i] = values()[i].toString();
		}
		return values;
		
	}
	
	
}
