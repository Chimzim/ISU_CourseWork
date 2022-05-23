package lab7;
import java.util.Random;
public class DeckTest
{
  public static void main(String[] args)
  {
    Deck deck = new Deck();
    Card[] hand = deck.select(5);
    System.out.println(Card.toString(hand));
  }
}