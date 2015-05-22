package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GamePanelController implements ActionListener {
	private GamePanel gamePanel;

	public GamePanelController() {
		this.gamePanel = new GamePanel();
	}

	@Override
	public void actionPerformed(ActionEvent event) {

	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

}
