package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class StartPanelController implements ActionListener, MouseListener {
	private StartPanel startPanel;
	
	public StartPanelController() {
		this.startPanel = new StartPanel();
		
		this.startPanel.getNewGame().addMouseListener(this);
		this.startPanel.getHighScore().addMouseListener(this);
		this.startPanel.getExit().addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
	}

	public StartPanel getStartPanel() {
		return startPanel;
	}

	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		if(event.getSource().equals(this.startPanel.getNewGame())){
			this.startPanel.getNewGame().setIcon(new ImageIcon("./resources/images/Buttons/Button_New_Hover.png"));
		} else if(event.getSource().equals(this.startPanel.getHighScore())){
			this.startPanel.getHighScore().setIcon(new ImageIcon("./resources/images/Buttons/Button_Score_Hover.png"));
		} else if(event.getSource().equals(this.startPanel.getExit())){
			this.startPanel.getExit().setIcon(new ImageIcon("./resources/images/Buttons/Button_Exit_Hover.png"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		if(event.getSource().equals(this.startPanel.getNewGame())){
			this.startPanel.getNewGame().setIcon(new ImageIcon("./resources/images/Buttons/Button_New.png"));
		} else if(event.getSource().equals(this.startPanel.getHighScore())){
			this.startPanel.getHighScore().setIcon(new ImageIcon("./resources/images/Buttons/Button_Score.png"));
		} else if(event.getSource().equals(this.startPanel.getExit())){
			this.startPanel.getExit().setIcon(new ImageIcon("./resources/images/Buttons/Button_Exit.png"));
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(this.startPanel.getNewGame())){
			this.startPanel.getNewGame().setIcon(new ImageIcon("./resources/images/Buttons/Button_New.png"));
		} else if(event.getSource().equals(this.startPanel.getHighScore())){
			this.startPanel.getHighScore().setIcon(new ImageIcon("./resources/images/Buttons/Button_Score.png"));
		} else if(event.getSource().equals(this.startPanel.getExit())){
			this.startPanel.getExit().setIcon(new ImageIcon("./resources/images/Buttons/Button_Exit.png"));
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(this.startPanel.getNewGame())){
			this.startPanel.getNewGame().setIcon(new ImageIcon("./resources/images/Buttons/Button_New_Hover.png"));
		} else if(event.getSource().equals(this.startPanel.getHighScore())){
			this.startPanel.getHighScore().setIcon(new ImageIcon("./resources/images/Buttons/Button_Score_Hover.png"));
		} else if(event.getSource().equals(this.startPanel.getExit())){
			this.startPanel.getExit().setIcon(new ImageIcon("./resources/images/Buttons/Button_Exit_Hover.png"));
		}
	}

}