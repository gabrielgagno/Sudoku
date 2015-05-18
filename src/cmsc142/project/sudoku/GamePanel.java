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
	private static JButton backMenuButton, checkerButton, solverButton, activateSpecialButton;
	private static JButton prevPuzzleButton, nextPuzzleButton, prevSolutionButton, nextSolutionButton;
	private static JLabel typeLabel, timerLabel;
	private static JComboBox typeComboBox;
	private static JTable sudokuTable;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setComponents();
		this.setVisible(true);
		this.setFocusable(true);
	}

	private void setComponents() {
		// TODO Auto-generated method stub
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		backMenuButton = new JButton("Back");
		timerLabel = new JLabel("00:00:00");
		
		typeLabel = new JLabel("Type:");
		typeComboBox = new JComboBox();
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
}
