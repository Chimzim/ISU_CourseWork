package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import hw4.BlockAddiction;
import api.Game;
import api.Generator;
import hw4.BasicGenerator;

import api.Game;

/**
 * Main class for a GUI for a Tetris-type game sets up a 
 * GamePanel instance in a frame.  Edit the create() method
 * to change the game or generator type.
 */
public class GameMain
{
  /**
   * Cell size in pixels.
   */
  public static final int SIZE = 25; 
  
  /**
   * Font for displaying score.
   */
  public static final int SCORE_FONT = 24; 
  
  /**
   * Grid background color.
   */
  public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
  
  
  /**
   * Helper method for instantiating the components.  This
   * method should be executed in the context of the Swing
   * event thread only.
   */
  private static void create()
  {
    // EDIT HERE TO CHANGE THE GAME
    
    // Use the SampleGame
//  Game game = new examples.SampleGame();
    
    // use a seed if you want to make the setup reproducible.
    Random rand = new Random();
    
    // A typical game will have the checkerboard pattern in the
    // bottom few rows and generate pieces with BasicGenerator
    Generator gen = new BasicGenerator(rand);
//    Game game = new BlockAddiction(20, 12, gen, 5);
    
    // You could also construct it without the checkerboard fill
    Game game = new BlockAddiction(16, 12, gen);

    
    // create the three panels for the game
    PlayLevel level = new PlayLevel();
    ScorePanel scorePanel = new ScorePanel();
    PreviewPanel previewPanel = new PreviewPanel();
    GamePanel gamePanel = new GamePanel(game, scorePanel, previewPanel, level);
     
    // arrange the three panels with main on bottom, score and preview on top
    JPanel topPanel = new JPanel();
    
    topPanel.add(scorePanel);
    topPanel.add(previewPanel);
    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(topPanel);
    mainPanel.add(gamePanel);
    
    // put main panel in a window
    JFrame frame = new JFrame("Com S 227 Block Addiction!");
    frame.getContentPane().add(mainPanel);

    // give panels a nonzero size
    int pad = 20; //?
    Dimension d = new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE);   
    gamePanel.setPreferredSize(d);
    d = new Dimension(game.getWidth() * GameMain.SIZE - 3 * GameMain.SIZE - pad, 3 * GameMain.SIZE);   
    scorePanel.setPreferredSize(d);
    d = new Dimension(3 * GameMain.SIZE, 3 * GameMain.SIZE);   
    previewPanel.setPreferredSize(d);
    frame.pack();
    
    // we want to shut down the application if the 
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // be sure key events get to the panel
    gamePanel.requestFocus();
    
    // rock and roll...
    frame.setVisible(true);
  }
  
  /**
   * Entry point.  Main thread passed control immediately
   * to the Swing event thread.
   * @param args not used
   */
  public static void main(String[] args)
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        create();
      }
    };
    SwingUtilities.invokeLater(r);
  }
}
