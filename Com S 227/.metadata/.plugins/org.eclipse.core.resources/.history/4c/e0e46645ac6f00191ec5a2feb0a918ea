package hw4;
import api.Piece;

import java.util.Arrays;

import api.Cell;
import api.Icon;
import api.Position;

public class CornerPiece extends AbstractPiece {
	/**
	 * orientation - holds the current position so the correct transformation happens to the corner piece
	 */
	private int orientation = 0;
	
	public CornerPiece(Position position, Icon[] icons) {
		super(position);
		Cell[] block = new Cell[icons.length];
		block[0] = new Cell(icons[0], new Position(0,0));
		block[1] = new Cell(icons[1], new Position(1, 0));
		block[2] = new Cell(icons[2], new Position(1, 1));
		super.setCells(block);
		}
	/**
	 * The corner piece is bounded by a 2x2 square and rotates around the bounding square
	 */
	public void transform() {
		Cell[] block = Arrays.copyOf(getCells(), 3);
		if(orientation == 0) {
			block[0].setPosition(new Position(0,1));
			block[1].setPosition(new Position(0,0));
			block[2].setPosition(new Position(1,0));
			orientation += 1;
		}
		else if(orientation == 1) {
			block[0].setPosition(new Position(1,1));
			block[1].setPosition(new Position(0,1));
			block[2].setPosition(new Position(0,0));
			orientation += 1;
		}
		else if(orientation == 2) {
			block[0].setPosition(new Position(1,0));
			block[1].setPosition(new Position(1,1));
			block[2].setPosition(new Position(0,1));
			orientation += 1;
		}
		else if(orientation == 3) {
			block[0].setPosition(new Position(0,0));
			block[1].setPosition(new Position(0,1));
			block[2].setPosition(new Position(1,1));
			orientation = 0;
		}
		setCells(block);
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
	}
	 
}