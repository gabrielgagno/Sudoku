package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HighScorePanel extends JPanel {
	private JLabel highScoreLabel;
	private JLabel highScoreSize;
	private JLabel highScoreType;
	private JTable highScoreTable;
	private JScrollPane tableScrollPane;
	private JComboBox<String> highScoreTypeComboBox;
	private JComboBox<String> highScoreSizeComboBox;
	
	public HighScorePanel() {
		this.setPreferredSize(new Dimension(600, 600));
		this.setComponets();
	}

	public void setComponets() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

				
		String[] typeList = { "Normal", "X Sudoku", "Y Sudoku", "XY Sudoku"};
		String[] sizeList = { "4x4", "9x9"};
		
		this.highScoreLabel = new JLabel("Sudoku Highscores");
		highScoreLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		this.highScoreSize = new JLabel("Puzzle Size");
		this.highScoreType = new JLabel("Puzzle Type");
		this.highScoreTable = new JTable(10, 2);
		this.highScoreSizeComboBox = new JComboBox<>(sizeList);
		this.highScoreTypeComboBox = new JComboBox<>(typeList);
		this.highScoreSizeComboBox.setPreferredSize(new Dimension(10, 10));
		this.tableScrollPane = new JScrollPane(highScoreTable);
		
		tableScrollPane.setPreferredSize(new Dimension(550, 500));
		GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
		horizontalGroup.addGap(100);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(highScoreLabel)
				.addGroup(layout.createSequentialGroup()
					.addGap(100)
					.addComponent(highScoreType)
					.addComponent(highScoreTypeComboBox)
					.addComponent(highScoreSize)
					.addComponent(highScoreSizeComboBox)
					.addGap(100)
				)
				.addComponent(tableScrollPane)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(30)
			.addComponent(highScoreLabel)
			.addGap(50)
			.addGroup(layout.createParallelGroup()
				.addComponent(highScoreType)
				.addComponent(highScoreTypeComboBox)
				.addComponent(highScoreSize)
				.addComponent(highScoreSizeComboBox)
			)
			.addGap(50)
			.addComponent(tableScrollPane)
		);

		this.setBackground(Color.YELLOW);
	}
}
