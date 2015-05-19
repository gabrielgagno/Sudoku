package cmsc142.project.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class StartPanelController implements ActionListener{
	private StartPanel start;
//	private SudokuGui sudokuGui;
	
	public StartPanelController(){
//		this.sudokuGui = new SudokuGui();
		this.start = new StartPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==start.newGame){
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(this.start);
			if(response == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				FileAccess fileAccess = new FileAccess();
			}
		}
	}
}