package hw4;

import api.AbstractGame;
import api.Generator;
import api.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BlockAddiction extends AbstractGame {
	/**
	 * 
	 * @param hieght - the amount of rows for the grid
	 * @param width - the amount of columns for the grid
	 * @param gen - the randomly generated icon for the grid
	 * @param preFillRows - the amount of prefilled rows to insert into the grid
	 * if the amount of prefilled rows is greater than zero then it makes a checkered board pattern at the bottom for the amount of pre filled rows, else it just constructs the grid 
	 */
	public BlockAddiction(int hieght, int width, Generator gen, int preFillRows) {
		super(hieght, width, gen);
		if (preFillRows > 0) {
			int positiveRows = preFillRows;
			for(int i = hieght - 1; i > (hieght-1) - preFillRows; i--){
				for(int j = 0; j < width; j++) {
					if((i - positiveRows) % 2 == 0 && j % 2 == 0) {
						super.setBlock(i, j, gen.randomIcon());
					}
					if((i - positiveRows) % 2 == 1) {
						if(j % 2 == 1) {
							super.setBlock(i, j, gen.randomIcon());
						}
					}	
				}
				
			}
		}
	}

	public BlockAddiction(int hieght, int width, Generator gen) {
		super(hieght, width, gen);
	}

	/**
	 * @Override
	 * determines the position to collapse by checking one in front, behind, above and below to see if the Icons match up, if they do match it adds them to an array list which will remove the duplicates and then returns the positions
	 */
	public List<Position> determinePositionsToCollapse() {
		ArrayList<Position> temp = new ArrayList<>();
		ArrayList<Position> ableToCollapse = new ArrayList<Position>();
		for(int i = 0; i < getHeight(); ++i) {
			if(isRowFilled(i)) {
				for(int j = 0; j < getWidth(); ++j) {
					temp.add(new Position(i, j));
				}
			}
		}
		for (int row = 0; row < getHeight(); ++row) {
			for (int col = 0; col < getWidth(); ++col) {
				if (getIcon(row, col) != null) {
					ArrayList<Position> toCollapse = new ArrayList<>();
					try {
						if (getIcon(row, col).equals(getIcon(row+1, col))) {
							toCollapse.add(new Position(row+1, col));
						}
					} catch (IndexOutOfBoundsException e) {

					}
					try {
						if (getIcon(row, col).equals(getIcon(row, col+1))) {
							toCollapse.add(new Position(row, col+1));
						}
					} catch (IndexOutOfBoundsException e) {

					}
					try {
						if (getIcon(row, col).equals(getIcon(row, col-1))) {
							toCollapse.add(new Position(row, col-1));
						}
					} catch (IndexOutOfBoundsException e) {

					}
					try {
						if (getIcon(row, col).equals(getIcon(row-1, col))) {
							toCollapse.add(new Position(row-1, col));
						}
					} catch (IndexOutOfBoundsException e) {

					}
					if (toCollapse.size() >= 2) {
						ableToCollapse.addAll(toCollapse);
						ableToCollapse.add(new Position(row, col));
					}
				}
			}
		}

		for (int i = 0; i < temp.size(); i++) {
			if (!ableToCollapse.contains(temp.get(i))) {
				ableToCollapse.add(temp.get(i));
			}
		}
		Collections.sort(ableToCollapse);
		return ableToCollapse;

	}
	/**
	 * Determines if the row is filled will null characters or not
	 * @param row - the row to be currently checked for null
	 * @return if the row is null or not
	 */
	private boolean isRowFilled(int row) {
		for (int col = 0; col < getWidth(); ++col) {
			if (getIcon(row, col) == null) {
				return false;
			}
		}
		return true;
	}

}
