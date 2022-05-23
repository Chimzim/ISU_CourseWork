package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A Documentary is a library item that can be checked out for 14 days and cannot be renewed.
 * If overdue, the fine is 1.00 per day for the first 5 days and .50 per day thereafter,
 * up to a maximum equal to the item's replacement cost.
 */
public class Documentary extends theBigOne
{
  /**
   * Title of this item.
   */
  private String title;
  
  /**
   * Due date for this item.  This value is null when not checked out.
   */
  private Date dueDate;
  
  /**
   * Patron to whom this item is checked out.  This value is null when not checked out.
   */
  private Patron checkedOutTo;

  /**
   * Replacement cost for this Documentary.
   */
  private double replacementCost;
  
  /**
   * Duration of this Documentary, in minutes.
   */
  private int duration;

  /**
   * Constructs a Documentary with the given title, replacement cost, and duration.
   * @param givenTitle
   *   title for this item
   * @param givenCost
   *   replacement cost for this item, in dollars
   * @param givenDuration
   *   duration of this item, in minutes
   */
  public Documentary(String givenTitle, double givenCost, int givenDuration)
  {
    super(givenTitle);
    replacementCost = givenCost;
    duration = givenDuration;
  }

  /**
   * Returns the duration of this Documentary.
   * @return
   *   duration of this Documentary
   */
  public int getDuration()
  {
    return duration;
  }
  
  @Override
  public void checkOut(Patron p, Date now)
  {
    if (!isCheckedOut())
    {
      int checkOutDays = 14;
      
      // use a GregorianCalendar to figure out the Date corresponding to
      // midnight, 14 days from now
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.add(Calendar.DAY_OF_YEAR, checkOutDays);
      
      // always set to 11:59:59 pm on the day it's due
      cal.set(Calendar.HOUR_OF_DAY, 23);
      cal.set(Calendar.MINUTE, 59);
      cal.set(Calendar.SECOND, 59);     
      
      // convert back to Date object
      dueDate = cal.getTime();
      
      checkedOutTo = p;      
    }
  }

  @Override
  public void checkIn()
  {
    super.checkIn();
  }

  @Override
  public void renew(Date now)
  {
    // cannot be renewed
  }

  @Override
  public double getFine(Date now)
  {
    if (isCheckedOut() && isOverdue(now))
    {
      // how late is it, in milliseconds
      double elapsed = now.getTime() - dueDate.getTime();
      
      // how late is it, in days
      int millisPerDay = 24 * 60 * 60 * 1000;
      int daysLate = (int) Math.ceil(elapsed / millisPerDay);
      
      // compute the fine
      double fine;
      if (daysLate <= 5)
      {
        fine = daysLate;
      }
      else
      {
        fine = 5 + (daysLate - 5) * .50;
      }
      return Math.min(fine, replacementCost);    }
    else
    {
      return 0;
    }
  }

  @Override
  public boolean isOverdue(Date now)
  {
    return super.isOverdue(now);
  }

  @Override
  public int compareTo(Item other)
  {
    return super.compareTo(other);
  }

  @Override
  public String getTitle()
  {
   return super.getTitle();
  }

  @Override
  public boolean isCheckedOut()
  {
    return super.isCheckedOut();
  }

  @Override
  public Date getDueDate()
  {
    return super.getDueDate();
  }

  @Override
  public Patron getPatron()
  {
    return super.getPatron();
  }
}