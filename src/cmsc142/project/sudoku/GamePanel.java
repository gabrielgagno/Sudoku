package cmsc142.project.sudoku;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class GamePanel extends JPanel{
	private JButton backMenuButton;
	private JButton checkerButton;
	private JButton solverButton;
	private JButton activateSpecialButton;
	private JButton prevPuzzleButton;
	private JButton nextPuzzleButton;
	private JButton prevSolutionButton;
	private JButton nextSolutionButton;
	private JLabel typeLabel, timerLabel;
	private JComboBox<String> typeComboBox;
	private JTable sudokuTable;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setComponents();
		this.setVisible(true);
		this.setFocusable(true);
	}

	private void setComponents() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		backMenuButton = new JButton("Back");
		timerLabel = new JLabel("00:00:00");
		
		typeLabel = new JLabel("Type:");
		typeComboBox = new JComboBox<String>();
		typeComboBox.addItem(new String("Normal"));
		typeComboBox.addItem(new String("X"));
		typeComboBox.addItem(new String("Y"));
		typeComboBox.addItem(new String("XY"));
		
		activateSpecialButton = new JButton("Activate Special Sudoku");
		prevPuzzleButton = new JButton("<");
		nextPuzzleButton = new JButton(">");
		
		sudokuTable = new JTable(4, 4);
		sudokuTable.setPreferredSize(new Dimension(400, 400));
		sudokuTable.setRowHeight((int) this.sudokuTable.getPreferredSize().getWidth()/this.sudokuTable.getRowCount());
		
		prevSolutionButton = new JButton("<<");
		nextSolutionButton = new JButton(">>");
		checkerButton = new JButton("Check");
		solverButton = new JButton("Solve");

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(backMenuButton)
						.addComponent(timerLabel)
					)
					.addComponent(typeLabel)
					.addComponent(typeComboBox)
					.addComponent(activateSpecialButton)
				)
				.addGroup(layout.createSequentialGroup()
					.addComponent(prevPuzzleButton)
					.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(sudokuTable)
						.addGroup(layout.createSequentialGroup()
							.addComponent(prevSolutionButton)
							.addComponent(nextSolutionButton)
						)
						.addGroup(layout.createSequentialGroup()
							.addComponent(checkerButton)
							.addGap(200)
							.addComponent(solverButton)
						)
					)
					.addComponent(nextPuzzleButton)
				)
			)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(75)
			.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addComponent(backMenuButton)
					.addComponent(timerLabel)
				)
				.addComponent(typeLabel)
				.addComponent(typeComboBox)
				.addComponent(activateSpecialButton)
			)
			.addGap(20)
			.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(prevPuzzleButton)
				.addGroup(layout.createSequentialGroup()
					.addComponent(sudokuTable)
					.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(prevSolutionButton)
						.addComponent(nextSolutionButton)
					)
					.addGap(20)
					.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(checkerButton)
						.addComponent(solverButton)
					)
				)
				.addComponent(nextPuzzleButton)
			)
			.addGap(75)
		);
	}

	public JButton getBackMenuButton() {
		return backMenuButton;
	}

	public void setBackMenuButton(JButton backMenuButton) {
		this.backMenuButton = backMenuButton;
	}

	public JButton getCheckerButton() {
		return checkerButton;
	}

	public void setCheckerButton(JButton checkerButton) {
		this.checkerButton = checkerButton;
	}

	public JButton getSolverButton() {
		return solverButton;
	}

	public void setSolverButton(JButton solverButton) {
		this.solverButton = solverButton;
	}

	public JButton getActivateSpecialButton() {
		return activateSpecialButton;
	}

	public void setActivateSpecialButton(JButton activateSpecialButton) {
		this.activateSpecialButton = activateSpecialButton;
	}

	public JButton getPrevPuzzleButton() {
		return prevPuzzleButton;
	}

	public void setPrevPuzzleButton(JButton prevPuzzleButton) {
		this.prevPuzzleButton = prevPuzzleButton;
	}

	public JButton getNextPuzzleButton() {
		return nextPuzzleButton;
	}

	public void setNextPuzzleButton(JButton nextPuzzleButton) {
		this.nextPuzzleButton = nextPuzzleButton;
	}

	public JButton getPrevSolutionButton() {
		return prevSolutionButton;
	}

	public void setPrevSolutionButton(JButton prevSolutionButton) {
		this.prevSolutionButton = prevSolutionButton;
	}

	public JButton getNextSolutionButton() {
		return nextSolutionButton;
	}

	public void setNextSolutionButton(JButton nextSolutionButton) {
		this.nextSolutionButton = nextSolutionButton;
	}

	public JLabel getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(JLabel typeLabel) {
		this.typeLabel = typeLabel;
	}

	public JLabel getTimerLabel() {
		return timerLabel;
	}

	public void setTimerLabel(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	public JComboBox<String> getTypeComboBox() {
		return typeComboBox;
	}

	public void setTypeComboBox(JComboBox<String> typeComboBox) {
		this.typeComboBox = typeComboBox;
	}

	public JTable getSudokuTable() {
		return sudokuTable;
	}

	public void setSudokuTable(JTable sudokuTable) {
		this.sudokuTable = sudokuTable;
	}
}