package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanelController implements ActionListener {
	private StartPanel startPanel;
	
	public StartPanelController() {
		this.startPanel = new StartPanel();
		
		this.startPanel.newGame.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
//		if (event.getSource() == startPanel.newGame) {
//			JFileChooser fileChooser = new JFileChooser();
//			int response = fileChooser.showOpenDialog(this.startPanel);
//			if (response == JFileChooser.APPROVE_OPTION) {
//				File file = fileChooser.getSelectedFile();
//				FileAccess fileAccess = new FileAccess();
//			}
//		} else 
//			
			
	}

	public StartPanel getStartPanel() {
		return startPanel;
	}

	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}

}