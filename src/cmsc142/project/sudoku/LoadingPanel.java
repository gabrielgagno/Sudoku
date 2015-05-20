package cmsc142.project.sudoku;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LoadingPanel extends JPanel{
	
	public LoadingPanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setVisible(true);
		this.setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g){
		System.out.println("lala");
		super.paintComponent(g);
		try {
			System.out.println("karacute");
			g.drawImage(ImageIO.read(new File("./resources/load.png")), 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
