package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HighScorePanelController implements ActionListener {
	private HighScorePanel highScorePanel;
	
	public HighScorePanelController(){
		String[] typeList = {"Normal Sudoku", "X Sudoku", "Y Sudoku", "XY Sudoku"};
		String[] sizeList = {};
		
		FileAccess fileAccess = new FileAccess();
		ArrayList<ArrayList<String[]>> highScorePerPuzzleType = null;
		
		try {
			HashMap<String, ArrayList<ArrayList<String[]>>> highscores = fileAccess.readScoreData("resources/highscores.dat");
			
			Object[] keys = highscores.keySet().toArray();
			
			sizeList = new String[keys.length];
			
			for (int i = 0; i < keys.length; i++) {
				sizeList[i] = (String) keys[i];
			}
			
			for (int i = 0; i < keys.length; i++) {
				System.out.println(keys[i]);
				highScorePerPuzzleType = highscores.get(keys[i]);
				
				for(int j = 0; j < highScorePerPuzzleType.size(); j++){
					System.out.println("Puzzle Type " + j);
					for(int k = 0; k < highScorePerPuzzleType.get(j).size(); k++){
						System.out.println(highScorePerPuzzleType.get(j).get(k));
					}
				}
			}
			
		} catch (IOException e) {
			System.out.println("Error reading file! " + e.getMessage());
		}
		
		this.highScorePanel = new HighScorePanel(typeList, sizeList);
		
		this.highScorePanel.getHighScoreSizeComboBox().addActionListener(this);
		this.highScorePanel.getHighScoreTypeComboBox().addActionListener(this);
	}
	
	private void updateTable(){
		String sizeSelected = this.highScorePanel.getHighScoreSizeComboBox().getSelectedItem().toString();
		String typeSelected = this.highScorePanel.getHighScoreTypeComboBox().getSelectedItem().toString();
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(this.highScorePanel.getHighScoreSizeComboBox())){
			
			
		} else if(event.getSource().equals(this.highScorePanel.getHighScoreTypeComboBox())){
			
		}
		
	}

	
	public HighScorePanel getHighScorePanel() {
		return highScorePanel;
	}

	public void setHighScorePanel(HighScorePanel highScorePanel) {
		this.highScorePanel = highScorePanel;
	}
}
