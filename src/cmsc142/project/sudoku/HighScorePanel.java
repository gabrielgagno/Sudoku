package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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
	private JButton backButton;
	
	public HighScorePanel(String[] typeList, String[] sizeList) {
		this.setPreferredSize(new Dimension(600, 600));
		this.setComponets(typeList, sizeList);
	}

	public void setComponets(String[] typeList, String[] sizeList) {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
	
		this.backButton = new JButton("Back to Menu");
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
				.addComponent(backButton, Alignment.LEADING)
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
			.addComponent(backButton)
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

	public JLabel getHighScoreLabel() {
		return highScoreLabel;
	}

	public void setHighScoreLabel(JLabel highScoreLabel) {
		this.highScoreLabel = highScoreLabel;
	}

	public JLabel getHighScoreSize() {
		return highScoreSize;
	}

	public void setHighScoreSize(JLabel highScoreSize) {
		this.highScoreSize = highScoreSize;
	}

	public JLabel getHighScoreType() {
		return highScoreType;
	}

	public void setHighScoreType(JLabel highScoreType) {
		this.highScoreType = highScoreType;
	}

	public JTable getHighScoreTable() {
		return highScoreTable;
	}

	public void setHighScoreTable(JTable highScoreTable) {
		this.highScoreTable = highScoreTable;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}

	public JComboBox<String> getHighScoreTypeComboBox() {
		return highScoreTypeComboBox;
	}

	public void setHighScoreTypeComboBox(JComboBox<String> highScoreTypeComboBox) {
		this.highScoreTypeComboBox = highScoreTypeComboBox;
	}

	public JComboBox<String> getHighScoreSizeComboBox() {
		return highScoreSizeComboBox;
	}

	public void setHighScoreSizeComboBox(JComboBox<String> highScoreSizeComboBox) {
		this.highScoreSizeComboBox = highScoreSizeComboBox;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	
	
}
