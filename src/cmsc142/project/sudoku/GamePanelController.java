package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanelController implements ActionListener{
	GamePanel gamePanel;
	public GamePanelController(){
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
