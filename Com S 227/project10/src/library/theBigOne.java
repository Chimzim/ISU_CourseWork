package library;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public abstract class theBigOne implements Item{
	private Date due;
	private String title;
	private int duration;
	private double replacementCost;
	private Patron checkedOutTo;
	
	
	public theBigOne(String useTitle) {
		title = useTitle;
		due = null;
	    checkedOutTo = null;
	}
	
	public int compareTo(Item other)
	  {
	    return title.compareTo(other.getTitle());
	  }
	
	public Patron getPatron()
	  {
	    return checkedOutTo;
	  }
	
	public Date getDueDate()
	  {
	    return due;
	  }
	
	public boolean isCheckedOut()
	  {
	    return due != null;
	  }
	
	public String getTitle()
	  {
	   return title;
	  }
	
	public boolean isOverdue(Date now)
	  {
	    if (!isCheckedOut())
	    {
	      return false;
	    }
	    return now.after(due);
	  }
	
	 public void checkIn()
	  {
	    if (isCheckedOut())
	    {
	      checkedOutTo = null;
	      due = null;
	    }
	  }
}
