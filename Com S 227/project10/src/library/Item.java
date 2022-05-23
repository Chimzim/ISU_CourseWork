package library;

import java.util.Date;

/**
 * Interface representing operations on items in a library.  
 * Items are intended to be ordered lexicographically by title.
 */
public interface Item extends Comparable<Item>
{
  /**
   * Checks out this item to the given patron, if possible.
   * Does nothing if the item is already checked out.
   * @param p
   *   Patron to whom to check out the item
   * @param now
   *   current date
   */
  void checkOut(Patron p, Date now);
  
  /**
   * Checks in this item.  Does nothing if the item is
   * not checked out.
   */
  void checkIn();
  
  /**
   * Renews this item, if possible.  Does nothing if the item is not checked out.
   * Does nothing if the item is currently overdue.
   * @param now
   *   current date
   */
  void renew(Date now);
  
  /**
   * Returns the patron to whom this item is checked out, or null
   * if the item is not checked out.
   * @return
   *   patron to whom this item is checked out, or null
   */
  Patron getPatron();
  
  /**
   * Returns the fine owed if this item is overdue, or zero
   * if the item is not currently overdue.
   * @param now
   *   current date
   * @return
   *   the fine currently owed
   */
  double getFine(Date now);
  
  /**
   * Determines whether this item is currently checked out.
   * @return
   *   true if this item is checked out, false otherwise
   */
  boolean isCheckedOut();
  
  /**
   * Determines whether this item is currently overdue.
   * @param now
   *   current date
   * @return
   *   true if this item is overdue, false otherwise
   */
  boolean isOverdue(Date now);
  
  /**
   * Returns the due date for this item, or null if not currently checked out.
   * @return
   *   due date for this item, or null
   */
  Date getDueDate();
  
  /**
   * Returns the title of this item.
   * @return
   *   title of this item
   */
  String getTitle();
}