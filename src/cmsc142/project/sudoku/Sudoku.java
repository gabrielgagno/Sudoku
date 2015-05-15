package cmsc142.project.sudoku;


public class Sudoku {

	private static final String OUTPUT_FILE = "resources/solution.out";
	private static final String INPUT_FILE = "resources/input.in";

	public static void main(String[] args) {
		SudokuGuiController sudokuGuiController = new SudokuGuiController();
//		FileAccess fileAccess = new FileAccess();
//
//		try {
//			ArrayList<SudokuBoard> puzzles = fileAccess.readBoard(INPUT_FILE);
//
//			SudokuUtils.findSolutions(puzzles);
//			fileAccess.writeSolutions(puzzles, OUTPUT_FILE);
//
////			for (int i = 0; i < puzzles.size(); i++) {
////				puzzles.get(i).printAllSolutions();
////			}
//		} catch (IOException e) {
//			System.out.println("[ Error accessing file! ]" + e.getMessage());
//		}

	}

}