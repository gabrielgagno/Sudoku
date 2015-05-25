package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {
	private JLabel blank;
	private JButton newGame;
	private JButton highScore;
	private JButton exit;

	public StartPanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setComponents();
		this.setVisible(true);
		this.setFocusable(true);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 20));
	}
	
	public JButton getNewGame() {
		return newGame;
	}

	public void setNewGame(JButton newGame) {
		this.newGame = newGame;
	}

	public JButton getHighScore() {
		return highScore;
	}

	public void setHighScore(JButton highScore) {
		this.highScore = highScore;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	@Override
	public void paintComponent(Graphics g){
		try {
			super.paintComponent(g);
			BufferedImage image = ImageIO.read(new File("./resources/images/background.jpg"));
			g.drawImage(image, 0, 0, this);
		} catch (IOException e) {
			System.out.println("[ Error reading background.png ]");
		}
	}
	
	private void setComponents(){
		blank = new JLabel();
		newGame = new JButton(new ImageIcon("./resources/images/buttons/Button_New.png"));
		highScore = new JButton(new ImageIcon("./resources/images/buttons/Button_Score.png"));
		exit = new JButton(new ImageIcon("./resources/images/buttons/Button_Exit.png"));
		
		blank.setPreferredSize(new Dimension(200, 280));
		newGame.setBorderPainted(false);
		newGame.setBorder(BorderFactory.createEmptyBorder());
		newGame.setContentAreaFilled(false);
		
		highScore.setBorderPainted(false);
		highScore.setBorder(BorderFactory.createEmptyBorder());
		highScore.setContentAreaFilled(false);
		
		exit.setBorderPainted(false);
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setContentAreaFilled(false);
		
		this.add(blank);
		this.add(newGame);
		this.add(highScore);
		this.add(exit);	
	}
}

