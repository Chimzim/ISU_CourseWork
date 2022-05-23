package hw4;
import api.Piece;

import java.util.Arrays;

import api.Cell;
import api.Icon;
import api.Position;


public class SnakePiece extends AbstractPiece{
	/**
	 * orientation - holds the position for the next transformation of the snake piece.
	 */
	private int orientation = 1;
	public SnakePiece(Position position, Icon[] icons) {
		super(position);
		Cell [] block = new Cell[icons.length];
		block[0] = new Cell(icons[0], new Position(0,0));
		block[1] = new Cell(icons[1], new Position(1,0));
		block[2] = new Cell(icons[2], new Position(1,1));
		block[3] = new Cell(icons[3], new Position(1,2));
		setCells(block);
	}
	/**
	 * The snake piece is bounded by a 3x3 square and transforms in a one directional by the origin and the rest of the cells following behind.
	 */
	public void transform() {
		Cell[] block = Arrays.copyOf(getCells(), 4);
		Position[] SnakeMove = {
				new Position(0,0),
				new Position(0,1),
				new Position(0,2),
				new Position(1,2),
				new Position(1,1),
				new Position(1,0),
				new Position(2,0),
				new Position(2,1),
				new Position(2,2),
				new Position(1,2),
				new Position(1,1),
				new Position(1,0),
		};
		
		for(int i = 3; i > 0; i--) {
			block[i].setPosition(new Position(block[i-1].getRow(), block[i-1].getCol()));
			if(orientation == 12) {
				orientation = 0;
			}
		}
		
		block[0].setPosition(SnakeMove[orientation]);
		orientation += 1;
		setCells(block);
	}
	/**
	 * Cycles through the colors on the Piece moving each color up one
	 */
	public void cycle() {
		Cell[] block = Arrays.copyOf(getCells(), 4);
		Icon tempColor = block[0].getIcon();
		block[0].setIcon(block[3].getIcon());
		block[3].setIcon(tempColor);
		tempColor = block[1].getIcon();
		block[1].setIcon(block[3].getIcon());
		block[3].setIcon(tempColor);
		tempColor = block[2].getIcon();
		block[2].setIcon(block[3].getIcon());
		block[3].setIcon(tempColor);
		setCells(block);
		
	}
}
