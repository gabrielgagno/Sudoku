package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener{
	private JLabel sudoku;
	JButton newGame;
	JButton highScore;
	JButton exit;
	private BufferedImage image;
	public StartPanel(){
		this.setPreferredSize(new Dimension(700,600));
		this.setComponents();
		this.setVisible(true);
		this.setFocusable(true);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 40));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			image = ImageIO.read(new File("./resources/grid.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, this);
	}
	
	private void setComponents(){
		sudoku = new JLabel(new ImageIcon("./resources/sudoku.png"));
		newGame = new JButton("New Game");
		highScore = new JButton("High Score");
		exit = new JButton("Exit");
		
		sudoku.setFont(new Font("Serif", Font.BOLD, 100));
//		sudoku.setPreferredSize(new Dimension(500,250));
		sudoku.setBounds(100, 100, 500, 250);
		newGame.setPreferredSize(new Dimension(200,50));
		highScore.setPreferredSize(new Dimension(200,50));
		exit.setPreferredSize(new Dimension(200,50));
		
		newGame.addActionListener(this);
		this.add(sudoku);
		this.add(newGame);
		this.add(highScore);
		this.add(exit);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}

