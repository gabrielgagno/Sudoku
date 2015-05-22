package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GamePanelController implements ActionListener{
	private GamePanel gamePanel;
	private JFrame frame;
	
	public GamePanelController(JFrame frame){
		this.frame = frame;
		this.gamePanel = new GamePanel();
		gamePanel.getBackMenuButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getSource() == gamePanel.getBackMenuButton()){
			
		}
	}

}
