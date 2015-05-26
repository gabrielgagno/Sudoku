package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;

public class GamePanel extends JPanel{
	private JButton backMenuButton;
	private JButton checkerButton;
	private JButton resetButton;
	private JButton solverButton;
	private JButton activateSpecialButton;
	private JButton prevPuzzleButton;
	private JButton nextPuzzleButton;
	private JButton prevSolutionButton;
	private JLabel solutionCountLabel;
	private JButton nextSolutionButton;
	private JLabel typeLabel, timerLabel;
	private JComboBox<String> typeComboBox;
	private JTable sudokuTable;
	private Timer timer;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setComponents();
		this.setVisible(true);
		this.setFocusable(true);
	}
	
	private JButton createButtonImage(ImageIcon imageIcon){
		JButton button = new JButton(imageIcon);
		button.setBorderPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		
		return button;
	}
	
	private JButton createButton(String title, Font font){
		JButton button = new JButton(title);
		button.setBorderPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setFont(font);
		return button;
	}
	
	private JLabel createLabel(String title, Font font){
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder());
		label.setFont(font);
		return label;
	}
	
	private void setComponents() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		backMenuButton = createButton("Back to Menu", new Font("A Year Without Rain", Font.PLAIN, 20));
		Font font = backMenuButton.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		backMenuButton.setFont(font.deriveFont(attributes));
		
		timerLabel = createLabel("00:00:00", new Font("A Year Without Rain", Font.PLAIN, 16));
		
		typeLabel = createLabel("Type", new Font("A Year Without Rain", Font.BOLD, 16));
		typeComboBox = new JComboBox<String>();
		typeComboBox.setFont(new Font("A Year Without Rain", Font.PLAIN, 16));
		typeComboBox.addItem(new String("Normal"));
		typeComboBox.addItem(new String("X"));
		typeComboBox.addItem(new String("Y"));
		typeComboBox.addItem(new String("XY"));
		
		activateSpecialButton = createButtonImage(new ImageIcon("./resources/images/Buttons/Button_Activate.png"));
		
		prevPuzzleButton = createButtonImage(new ImageIcon("./resources/images/Buttons/Button_Left.png"));
		nextPuzzleButton = createButtonImage(new ImageIcon("./resources/images/Buttons/Button_Right.png"));
		
		prevPuzzleButton.setEnabled(false);
		nextPuzzleButton.setEnabled(false);
		
		sudokuTable = new JTable(4, 4);
		sudokuTable.setPreferredSize(new Dimension(400, 400));
		sudokuTable.setRowHeight((int) this.sudokuTable.getPreferredSize().getWidth()/this.sudokuTable.getRowCount());
		
		prevSolutionButton = createButton("<< Prev", new Font("A Year Without Rain", Font.PLAIN, 20));
		nextSolutionButton = createButton("Next >>", new Font("A Year Without Rain", Font.PLAIN, 20));
		prevSolutionButton.setEnabled(false);
		nextSolutionButton.setEnabled(false);
		
		solutionCountLabel = new JLabel("");
		solutionCountLabel.setVisible(false);
		
		checkerButton = createButtonImage(new ImageIcon("./resources/images/Buttons/Button_Check.png"));
		resetButton = createButtonImage(new ImageIcon("./resources/images/Buttons/Button_Reset.png"));
		solverButton = createButtonImage(new ImageIcon("./resources/images/Buttons/Button_Solve.png"));

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(backMenuButton)
				.addGroup(layout.createSequentialGroup()
					.addComponent(timerLabel)
					.addGap(20)
					.addComponent(typeLabel)
					.addComponent(typeComboBox)
					.addGap(20)
					.addComponent(activateSpecialButton)
				)
				.addGroup(layout.createSequentialGroup()
					.addComponent(prevPuzzleButton)
					.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(sudokuTable)
						.addGroup(layout.createSequentialGroup()
							.addComponent(prevSolutionButton)
							.addComponent(solutionCountLabel)
							.addComponent(nextSolutionButton)
						)
						.addGroup(layout.createSequentialGroup()
							.addComponent(checkerButton)
							.addComponent(resetButton)
							.addComponent(solverButton)
						)
					)
					.addComponent(nextPuzzleButton)
				)
			)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGap(10)
			.addComponent(backMenuButton)
			.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(timerLabel)
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
						.addComponent(solutionCountLabel)
						.addComponent(nextSolutionButton)
					)
					.addGap(20)
					.addGroup(layout.createParallelGroup(Alignment.CENTER)
						.addComponent(checkerButton)
						.addComponent(resetButton)
						.addComponent(solverButton)
					)
				)
				.addComponent(nextPuzzleButton)
			)
			.addGap(75)
		);
	}
	
	public void paintComponent(Graphics g){
		try {
			super.paintComponent(g);
			BufferedImage image = ImageIO.read(new File("./resources/images/BG_1.jpg"));
			g.drawImage(image, 0, 0, this);
		} catch (IOException e) {
			System.out.println("[ Error reading background.png ]");
		}
	}
	
	public String computeDuration(int timeLength) {
		int hour, min, sec;
		sec = timeLength%60;
		timeLength /= 60;
		min = timeLength%60;
		timeLength /= 60;
		hour = timeLength;
		String duration = "";
		if(hour < 10)
			duration += 0;
		duration += hour + ":";
		if(min < 10)
			duration += 0;
		duration += min + ":";
		if(sec < 10)
			duration += 0;
		duration += sec;
		return duration;
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

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}

	public JLabel getSolutionCountLabel() {
		return solutionCountLabel;
	}

	public void setSolutionCountLabel(JLabel solutionCountLabel) {
		this.solutionCountLabel = solutionCountLabel;
	}
}
