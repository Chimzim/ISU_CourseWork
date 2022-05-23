package api;
import java.awt.Color;
import java.util.Arrays;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;
import hw4.LPiece;

/**
 * Sample test code for Piece transform, shift, and cycle.
 * The comments in the code below specifically refer to 
 * testing the LPiece, but you can adapt the code to test any shape.
 * THIS CODE WILL NOT COMPILE UNTIL YOU HAVE IMPLEMENTED LPiece.
 */
public class ExamplePieceTests
{
  /**
   * Helper method to create a shape at the given position 
   * for testing.  EDIT THIS CODE FOR THE SHAPE YOU WANT TO TEST.
   */
  private static Piece makeShape(int row, int col)
  {
    // EDIT HERE TO CREATE A DIFFERENT PIECE TYPE FOR TESTING
    Icon[] icons = { 
      new Icon(Color.RED),
      new Icon(Color.GREEN),
      new Icon(Color.BLUE),
      new Icon(Color.MAGENTA),
    };
    return new LPiece(new Position(row, col), icons);

  }
  
  public static void main(String[] args)
  {
    // As an example, start with a shape whose bounding square
    // is located (10, 10)
    Piece shape = makeShape(10, 10);
    
    // expected (10, 10)
    System.out.println("Position:");
    System.out.println(shape.getPosition());
    System.out.println();
    
    // if testing the L-shape shown above in makePiece
    // then expected [R(0, 0), G(0, 1), B(1, 1), M(2, 1)]
    Cell[] result = shape.getCells();
    System.out.println("Relative positions in bounding square:");
    System.out.println(Arrays.toString(result));
    System.out.println();
    
    // make sure you're really making a deep copy - modify the result
    // and make sure the original didn't change
    result[0].setRowCol(42, 137);
    System.out.println(Arrays.toString(shape.getCells()));
    
    // expected [R(10, 10), G(10, 11), B(11, 11), M(12, 11)]
    System.out.println("Absolute positions");
    System.out.println(Arrays.toString(shape.getCellsAbsolute()));
    System.out.println();
    
    shape.shiftDown();
    
    // expected (11, 10)
    System.out.println("Position after shiftDown:");
    System.out.println(shape.getPosition());
    System.out.println();
    
    // expected [R(0, 0), G(0, 1), B(1, 1), M(2, 1)]
    System.out.println("Relative positions after shiftDown (should be unchanged):");
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // expected [R(11, 10), G(11, 11), B(12, 11), M(13, 11)]
    System.out.println("Absolute positions after shiftDown");
    System.out.println(Arrays.toString(shape.getCellsAbsolute()));
    System.out.println();
    
    shape.shiftRight();
    shape.shiftRight();
    
    // expected (11, 12)
    System.out.println("Position after shiftRight twice:");
    System.out.println(shape.getPosition());
    System.out.println();
    
    // expected [R(0, 0), G(0, 1), B(1, 1), M(2, 1)]
    System.out.println("Relative positions after shiftRight (should be unchanged):");
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // expected [R(11, 12), G(11, 13), B(12, 13), M(13, 13)]
    System.out.println("Absolute positions after shiftRight");
    System.out.println(Arrays.toString(shape.getCellsAbsolute()));
    System.out.println();    
    
    // transform for L-shape should flip it across vertical centerline
    shape.transform();
    
    // expected (11, 12)
    System.out.println("Position after transform (should be unchanged):");
    System.out.println(shape.getPosition());
    System.out.println();
    
    // expected [R(0, 2), G(0, 1), B(1, 1), M(2, 1)]
    System.out.println("Relative positions after transform:");
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // expected [R(11, 14), G(11, 13), B(12, 13), M(13, 13)]
    System.out.println("Absolute positions after transform");
    System.out.println(Arrays.toString(shape.getCellsAbsolute()));
    System.out.println();   
    
    
    // try cycling, expected:
    //        [R(0, 0), G(0, 1), B(1, 1), M(2, 1)]
    //        [M(0, 0), R(0, 1), G(1, 1), B(2, 1)]
    //        [B(0, 0), M(0, 1), R(1, 1), G(2, 1)]
    //        [G(0, 0), B(0, 1), M(1, 1), R(2, 1)]
    //        [R(0, 0), G(0, 1), B(1, 1), M(2, 1)]
    System.out.println("make new shape, cycle four times");
    shape = makeShape(0, 0);
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // check the clone method...
    shape = makeShape(0, 0);
    Piece copy = shape.clone();
    System.out.println(copy.getClass());  // should be LShape
    copy.shiftDown();
    
    // after moving the copy, original should be unmodified
    // expected [R(0, 0), G(0, 1), B(1, 1), M(2, 1)]
    System.out.println(Arrays.toString(shape.getCellsAbsolute()));
  }
}