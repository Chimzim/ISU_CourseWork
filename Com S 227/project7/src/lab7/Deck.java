package lab7;
import java.util.Random;

import lab7.Card.Suit;

/**
 * Class representing a standard 52-card deck of playing
 * cards from which cards can be selected at random.
 */
public class Deck
{
  /**
   * The cards comprising this deck.
   */
  private Card[] cards;
  
  /**
   * The random number generator to use for selecting cards.
   */
  private Random rand;
  
  /**
   * Constructs a new deck with a default random number generator.
   */
  public Deck()
  {
    rand = new Random();
    init();
  }

  /**
   * Constructs a new deck with the given random number generator.
   */
  public Deck(Random givenGenerator)
  {
    rand = givenGenerator;
	init();
  }
  
  /**
   * Returns a new array containing k elements selected
   * at random from this deck.
   */
  public Card[] select(int k)
  {
    rand = new Random();
    Card[] myHand = new Card[k];
    Card temp_val;
    int j = 52;
    for(int i = 0; i < myHand.length; i++) {
    	int userInput = rand.nextInt(j);
    	temp_val = cards[userInput];
    	myHand[i] = temp_val;
    	cards[j-1] = temp_val;
    	j -= 1;
    }
    return null;
  }
  
  /**
   * Initializes a new deck of 52 cards.
   */
  private void init()
  {
    cards = new Card[52];
    int index = 0;
    for (int rank = 1; rank <= 13; ++rank)
    {
      cards[index] = new Card(rank, Suit.CLUBS);
      index += 1;
      cards[index] = new Card(rank, Suit.DIAMONDS);
      index += 1;
      cards[index] = new Card(rank, Suit.HEARTS);
      index += 1;
      cards[index] = new Card(rank, Suit.SPADES);
      index += 1;
    }

  }
}