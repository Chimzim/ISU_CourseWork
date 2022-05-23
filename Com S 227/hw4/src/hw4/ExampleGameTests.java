package hw4;
import java.awt.Color;
import java.util.List;
import java.util.Random;

import api.AbstractGame;
import api.Icon;
import api.Position;
import examples.SampleGenerator;
import hw4.BasicGenerator;
import hw4.BlockAddiction;

/**
 * Some examples for testing the BlockAddiction game constructor
 * and the determinePositionsToCollapse method.
 * 
 * THIS CODE WILL NOT COMPILE UNTIL YOU HAVE IMPLEMENTED BlockAddiction
 */
public class ExampleGameTests
{
  public static void main(String[] args)
  {

    // fill some cells to try out, see method below
    BlockAddiction game = setUpExample();
    
    // print it out, should be:
    //    - M G - - -
    //    G - G - - -
    //    - G - - - - 
    //    - - R B - -
    //    - R R R - -
    //    R - - B B -
    //    - - B - - - 
    //    - - - - B - 
    printGrid(game);
    
    // try our method
    List<Position> result = game.determinePositionsToCollapse();
    // expected [(3, 2), (4, 1), (4, 2), (4, 3)]
    System.out.println(result);
    System.out.println();
    
    // try collapsing, should get:
    //    - - - - - -
    //    G M - - - -
    //    - - G - - -
    //    - G G - - -
    //    - - - B - -
    //    - - - B B -
    //    - - B - - -
    //    - - - - B -
    game.collapsePositions(result);
    printGrid(game);
        
    // should be able to call determinePositionsToCollapse again
    result = game.determinePositionsToCollapse();
    // expected [(2, 2), (3, 1), (3, 2), (4, 3), (5, 3), (5, 4)]
    System.out.println(result);
    System.out.println();
    
    // ... collapse again, should get
    //    - - - - - -
    //    G - - - - -
    //    - M - - - -
    //    - - - - - -
    //    - - - - - -
    //    - - - - - -
    //    - - B - - -
    //    - - - - B -
    game.collapsePositions(result);
    printGrid(game);
    
    // call determinePositionsToCollapse again, returns empty list,
    // score does not increase
    result = game.determinePositionsToCollapse();
    System.out.println(result);
    
    
    // try out the prefill, using the examples.SampleGenerator,
    // the bottom four rows should look like this
    //
    //   R - R - R - R -
    //   - R - R - R - R
    //   R - R - R - R -
    //   - R - R - R - R    
    game = new BlockAddiction(16, 8, new SampleGenerator(), 4);    
    printGrid(game);
  }
  
  public static void printGrid(AbstractGame game)
  {
    final String fmt = "%2s";
    for (int row = 0; row < game.getHeight(); row += 1)
    {
      for (int col = 0; col < game.getWidth(); col += 1)
      {
        Icon b = game.getIcon(row, col);
        String s;
        if (b == null)
        {
          s = "-";
        }
        else
        {
          s = b.toString();
        }
        System.out.printf(fmt, s);
      }
      System.out.println();
    }
  }

  
  private static BlockAddiction setUpExample()
  {
    
    // print it out, should be:
    //    - M G - - -
    //    G - G - - -
    //    - G - - - - 
    //    - - R B - -
    //    - R R R - -
    //    R - - B B -
    //    - - B - - - 
    //    - - - - B - 
    
    BlockAddiction game = new BlockAddiction(8, 6, new BasicGenerator(new Random()));

    Icon b = new Icon(Color.RED);
    game.setBlock(3, 2, b);
    game.setBlock(4, 1, b);
    game.setBlock(4, 2, b);
    game.setBlock(4, 3, b);
    game.setBlock(5, 0, b);
    
    b = new Icon(Color.GREEN);
    game.setBlock(0, 2, b);
    game.setBlock(1, 2, b);
    game.setBlock(2, 1, b);
    game.setBlock(1, 0, b);
    
    b = new Icon(Color.BLUE);
    game.setBlock(3, 3, b);
    game.setBlock(5, 3, b);
    game.setBlock(5, 4, b);
    game.setBlock(6, 2, b);
    game.setBlock(7, 4, b);
    
    b = new Icon(Color.MAGENTA);
    game.setBlock(0, 1, b);   
    
    return game;

  }
}