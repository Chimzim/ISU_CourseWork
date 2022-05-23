package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A Book is a library item that can be checked out for 21 days and renewed at most twice.
 * If overdue, the fine is .25 per day.
 */
public class Book extends theBigOne
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
   * Number of times the item has been renewed for the current patron.
   */
  private int renewalCount;
  
  /**
   * Constructs a book with the given title.
   * @param givenTitle
   */
  public Book(String givenTitle)
  {
	super(givenTitle);
    title = givenTitle;
    renewalCount = 0;
  }
  
  @Override
  public void checkOut(Patron p, Date now)
  {
    if (!isCheckedOut())
    {
      int checkOutDays = 21;
      
      // use a GregorianCalendar to figure out the Date corresponding to
      // midnight, 21 days from now
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
     renewalCount = 0;
  }

  @Override
  public void renew(Date now)
  {
    if (isCheckedOut() && !isOverdue(now) && renewalCount < 2)
    {
      checkOut(checkedOutTo, dueDate);
      renewalCount += 1;
    }    
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
      
      // compute the fine, 25 cents per day
      return daysLate * .25;
    }
    else
    {
      return 0;
    }
  }
}

  