package hw4;

import java.util.Arrays;

import api.Cell;
import api.Position;
import api.Icon;
import api.Piece;

public class LPiece extends AbstractPiece{
	
	public LPiece(Position position, Icon[] icons) {
		super(position);
		Cell[] LP = new Cell[icons.length];
		LP[0] = new Cell(icons[0], new Position(0,0));
		LP[1] = new Cell(icons[1], new Position(0,1));
		LP[2] = new Cell(icons[2], new Position(1,1));
		LP[3] = new Cell(icons[3], new Position(2,1));
		super.setCells(LP);
		}
	
	public void transform() {
		Cell[] LP = Arrays.copyOf(getCells(), 4);
		if(LP[0].getCol() < LP[1].getCol()) {
			LP[0].setCol(LP[0].getCol()+2);
		}
		else {
			LP[0].setCol(LP[0].getCol()-2);
		}
		setCells(LP);
	}

		public void cycle() {
			Cell[] LP = Arrays.copyOf(getCells(), 4);
			Icon tempColor = LP[0].getIcon();
			LP[0].setIcon(LP[3].getIcon());
			LP[3].setIcon(tempColor);
			tempColor = LP[1].getIcon();
			LP[1].setIcon(LP[3].getIcon());
			LP[3].setIcon(tempColor);
			tempColor = LP[2].getIcon();
			LP[2].setIcon(LP[3].getIcon());
			LP[3].setIcon(tempColor);
			
			setCells(LP);
		}
		
		
}


