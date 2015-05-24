package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class SudokuGuiController implements ActionListener {
	private SudokuGui sudokuGui;
	private StartPanelController startPanelController;
	private HighScorePanelController highScorePanelController;
	private GamePanelController gamePanelController;
	
	public SudokuGuiController(){
		this.sudokuGui = new SudokuGui();
		
		this.highScorePanelController = new HighScorePanelController();
		this.startPanelController = new StartPanelController();
		this.gamePanelController = new GamePanelController();
		
		this.sudokuGui.changePanel(this.startPanelController.getStartPanel());
		
		/*
		 * Adds action listener for the transition of panels
		 */
		this.startPanelController.getStartPanel().newGame.addActionListener(this);
		this.startPanelController.getStartPanel().highScore.addActionListener(this);
		this.startPanelController.getStartPanel().exit.addActionListener(this);
		
		this.highScorePanelController.getHighScorePanel().getBackButton().addActionListener(this);
		this.gamePanelController.getGamePanel().getBackMenuButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.startPanelController.getStartPanel().newGame)){
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(sudokuGui);
			if(response == JFileChooser.APPROVE_OPTION){
				ArrayList<File> fileList = new ArrayList<File>();
				File file = fileChooser.getSelectedFile();
				this.sudokuGui.changePanel(new LoadingPanel());

//				gamePanelController.initialize(file.getAbsolutePath());
//				this.sudokuGui.changePanel(gamePanelController.getGamePanel());
			}
		} else if (event.getSource().equals(this.startPanelController.getStartPanel().highScore)){
			highScorePanelController.updateHighScore();
			this.sudokuGui.changePanel(highScorePanelController.getHighScorePanel());
		} else if (event.getSource().equals(this.startPanelController.getStartPanel().exit)){
			sudokuGui.dispose();
		} else if(event.getSource().equals(this.highScorePanelController.getHighScorePanel().getBackButton())){
			this.sudokuGui.changePanel(this.startPanelController.getStartPanel());
		} else if(event.getSource().equals(this.gamePanelController.getGamePanel().getBackMenuButton())){
			this.sudokuGui.changePanel(this.startPanelController.getStartPanel());
		}
	}
}
