package library;

import java.util.Date;

/**
 * A ReferenceItem is a library item that cannot be checked out.
 */
public class ReferenceItem extends theBigOne
{
  /**
   * Title of this item.
   */
  private String title;
  
  /**
   * Constructs a ReferenceItem with the given title.
   * @param givenTitle
   */
  public ReferenceItem(String givenTitle)
  {
    super(givenTitle);
    }
  
  @Override
  public void checkOut(Patron p, Date now)
  {
    // can't be checked out
  }

  @Override
  public void checkIn()
  {
    // can't be checked out
  }

  @Override
  public void renew(Date now)
  {
    // can't be checked out
  }

@Override
public double getFine(Date now) {
	// TODO Auto-generated method stub
	return 0;
}
}
  