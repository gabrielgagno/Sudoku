package cmsc142.project.sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.HashSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer {
	private SudokuBoard currentBoard;
	private HashSet<Point> errorCells;
	
	public CellRenderer(){
		
	}
	 
	public Component getTableCellRendererComponent(JTable table, Object value,HashSet<Point> errorCells, boolean   isSelected, boolean hasFocus, int row, int column){ 
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		    errorCells.add(new Point(table.getSelectedRow(), table.getSelectedColumn()));
		    
		    if(errorCells.contains(new Point(row, column))){
		    	c.setBackground(new Color(210, 0, 0));
		    } else if(((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==0) ||
		    		((row/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1 && (column/(int)Math.sqrt(currentBoard.getPuzzleSize()))%2==1)){
		        c.setBackground(new Color(210, 210, 210)); 
		    } else {
		    	c.setBackground(new Color(240,240,240));
		    }
		    return c; 
	}
	
}
