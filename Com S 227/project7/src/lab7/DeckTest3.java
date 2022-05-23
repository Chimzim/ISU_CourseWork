package lab7;

/**
 * Use the Deck class to generate lots of random 5-card hands,
 * and then use the PokerHand class to check how many straights,
 * full houses, and flushes are produced.
 */
public class DeckTest3
{
  public static void main(String[] args)
  {
    Deck deck = new Deck();
    
    // counters
    int straights = 0;
    int fullHouses = 0;
    int flushes = 0;

    // if you uncomment any of the SOPs below for testing, 
    // maybe make this number smaller!
    int iterations = 1000000;
    
    for (int i = 0; i < iterations; ++i)
    {
      Card[] cards = deck.select(5);
      PokerHand hand = new PokerHand(cards);
      if (hand.isStraight())
      {
        straights += 1;
        //System.out.println(Card.toString(cards));
      }
      if (hand.isFullHouse())
      {
        fullHouses += 1;
        //System.out.println(Card.toString(cards));
      }
      
      // includes "straight flushes", which occur about .00001 of the time, and full houses
      // that are all the same suit, which occur about .00023 of the time
      if (hand.isFlush())  
      {
        flushes += 1;
        //System.out.println(Card.toString(cards));
      }
    }
    
    System.out.printf("Flushes     (expected about .00198): %7.5f\n", ((double) flushes) / iterations);
    System.out.printf("Straights   (expected about .00355): %7.5f\n", ((double) straights) / iterations);
    System.out.printf("Full Houses (expected about .00144): %7.5f\n", ((double) fullHouses) / iterations);

  }

}