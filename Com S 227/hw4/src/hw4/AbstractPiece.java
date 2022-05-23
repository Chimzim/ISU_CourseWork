package hw4;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

public abstract class AbstractPiece implements Piece{
	/**
	 * origin - holds the position to be used for the absolute position of the piece
	 * block - the Array of cells that hold the Icons and positons for the given piece
	 */
	private Position origin;
	private Cell[] block;
	/**
	 * Construct an abstract Piece at the given position
	 * @param givenPosition - position of the upper left hand corner.
	 */
	protected AbstractPiece(Position givenPosition) {
		origin = givenPosition;
	}
	
	/**
	 * @return origin - the given position of the upper left hand corner
	 */
	public Position getPosition() {
		return origin;
	}
	/**
	 * @Override
	 * @param givenCells - The cells used to set the Piece inside the abstract class
	 * Sets the positions and icons for the parent class.
	 */
	public void setCells(Cell[] givenCells) {
		block = new Cell[givenCells.length];
		for(int i = 0; i < block.length; i++) {
			block[i] = new Cell(givenCells[i]);
		}
	}
	/**
	 * @Override
	 * Makes a copy of the parents.
	 * @return copy - A copy of the piece.
	*/
	public Cell[] getCells() {
		Cell[] copy = new Cell[block.length];
		for(int i = 0; i < block.length; i++) {
			copy[i] = new Cell(block[i].getIcon(), new Position(block[i].getRow(), block[i].getCol()));
		}
		return copy;
	}
	/**
	 * @Override
	 * Gives the non relative positions of where the piece is located on the grid
	 * @return ret - The absolute position of the piece on the grid.
	*/
	 public Cell[] getCellsAbsolute()
	  {
	    Cell[] ret = new Cell[block.length];
	    for(int i = 0; i < block.length; i++) {
	    	int row = block[i].getRow() + origin.row();
		    int col = block[i].getCol() + origin.col();
		    Icon b = block[i].getIcon();
		    ret[i] = new Cell(b, new Position(row, col));
	    }
	    return ret;
	  }
	 /**
	  * @Override
	  * Moves the piece one row down.
	*/
	public void shiftDown() {
		origin = new Position(origin.row()+1, origin.col());	
	}
	/**
	 * @Override
	 * Moves the piece on column to the right
	 */
	public void shiftRight() {
		origin = new Position(origin.row(), origin.col()+1);
	}
	/**
	 * @Override
	 * Moves the piece on column to the left
	 */
	public void shiftLeft() {
		origin = new Position(origin.row(), origin.col()-1);
	}
	/* 
	 * @Override
	 * This method is different for each piece
	 */
    public abstract void transform();
	/**
	 * @Override
	 * @return c - returns a clone of the piece which gives the correct runtime for the piece
	 */
	public Piece clone() {
		try {
			AbstractPiece c = (AbstractPiece) super.clone();
			c.block = new Cell[block.length];
			
			for(int i = 0; i < block.length; i++) {
				c.block[i] = new Cell(block[i]);
			}
			return c;
		}
		 catch (CloneNotSupportedException e)
	    {
	      // can't happen, since we know the superclass is cloneable
	      return null;
	    }
	}
}
