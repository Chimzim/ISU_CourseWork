package hw4;

import java.util.Arrays;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

public class DiagonalPiece extends AbstractPiece{
	
	
	public DiagonalPiece(Position position, Icon[] icons) {
		super(position);
		Cell [] block = new Cell[icons.length];
		block[0] = new Cell(icons[0], new Position(0,0));
		block[1] = new Cell(icons[1], new Position(1,1));
		super.setCells(block);
		}
	
	public void transform() {
		Cell[] block = Arrays.copyOf(getCells(), 2);
		if(block[0].getCol() < block[1].getCol()) {
			block[0].setCol(block[0].getCol()+1);
			block[1].setCol(block[1].getCol()-1);
		}
		else { 
			block[0].setCol(block[0].getCol()-1);
			block[1].setCol(block[1].getCol()+1);
		}
		super.setCells(block);
	}

	@Override
	public void cycle() {
		Cell[] block = Arrays.copyOf(getCells(), 2);
		Icon tempColor = block[0].getIcon();
		block[0].setIcon(block[1].getIcon());
		block[1].setIcon(tempColor);
		super.setCells(block);
	}

}
