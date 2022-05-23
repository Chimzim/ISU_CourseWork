package hw4;

import java.util.Arrays;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

public class IPiece extends AbstractPiece{
	
	public IPiece(Position position, Icon[] icons) {
		super(position);
		Cell[] block = new Cell[icons.length];
		block[0] = new Cell(icons[0], new Position(0,1));
		block[1] = new Cell(icons[1], new Position(1,1));
		block[2] = new Cell(icons[2], new Position(1,2));
		super.setCells(block);
		}
	
	public void transform() {
		//does nothing 
	}
	/**
	 * Cycles through the colors on the Piece moving each color up one
	 */
	public void cycle() {
		Cell[] block = Arrays.copyOf(getCells(), 3);
		Icon tempColor = block[0].getIcon();
		block[0].setIcon(block[2].getIcon());
		block[2].setIcon(tempColor);
		tempColor = block[1].getIcon();
		block[1].setIcon(block[2].getIcon());
		block[2].setIcon(tempColor);
		setCells(block);
	}

	
	

}
