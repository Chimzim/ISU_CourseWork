package api;

import java.util.ArrayList;

/**
 * Represents a symbol table for storing and looking up program symbols.  We
 * will have two types of symbols, data (variables) and labels (jump targets).
 * Each symbol is an NVPair.
 */
public class SymbolTable
{
  /**
   * The backing list for the table.
   */
  private ArrayList<NVPair> list;

  /**
   * Creates a new, empty symbol table.
   */
  public SymbolTable()
  {
    list = new ArrayList<NVPair>();
  }
  
  /**
   * Gets the number of symbols in the symbol table.
   * @return
   *  The number of symbols in the symbol table.
   */
  public int size()
  {
    return list.size();
  }
  
  /**
   * Returns the NVPair associated with index i.
   * @param i
   *   index of the NVPair to return
   * @return
   *  The NVPair associated with index i.
   */
  public NVPair getByIndex(int i)
  {
    return list.get(i);
  }
  
  /**
   * Adds entry into table with the given name and value zero.
   * @param name
   *   string to be added to the symbol table
   */
  public void add(String name)
  {
    list.add(new NVPair(name));
  }

  /**
   * Adds entry into table with the given name and value.
   * @param name
   *   string to be added to the symbol table
   * @param value
   *   integer value to be associated with the given name
   */
  public void add(String name, int value)
  {
    list.add(new NVPair(name, value));
  }

  /**
   * Determines whether the table contains a pair with
   * the given name, case insensitive.
   * @param name
   *   the string name to search for in this table
   * @return 
   *   true if the table contains a pair with the given name, false otherwise
   */
  public boolean containsName(String name)
  {
    for (NVPair p : list)
    {
      if (p.getName().equalsIgnoreCase(name))
      {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Does a case insensitive search for a pair with the given name.
   * @param name
   *   the string name to search for in this table
   * @return
   *  The NVPair associated with the given name
   * @throws IllegalArgumentException
   *  if there is no pair with the given name
   */
  public NVPair findByName(String name)
  {
    for (NVPair p : list)
    {
      if (p.getName().equalsIgnoreCase(name))
      {
        return p;
      }
    }
    
    // report error
    throw new IllegalArgumentException("Name " + name + " not found in symbol table. ");
  }

  /**
   * Does a search for a pair with value v.
   * @param v
   *   the value to search for in this table
   * @return
   *  The name associated with value v, or null if no such value.
   * @throws IllegalArgumentException
   *  if there is no pair with the given value
   */
  public NVPair findByValue(int v)
  {
    for (NVPair p : list)
    {
      if (p.getValue() == v)
      {
        return p;
      }
    }
    
    // report error
    throw new IllegalArgumentException("No pair with value " + v + " found in symbol table. ");
  }

  /**
   * Returns the index of a given pair p in the table.
   * @param p
   *   the NVPair to search for in the table
   * @return
   *  The index of the first pair in the table that matches p's name and value, or -1 if no such pair
   */
  public int indexOf(NVPair p)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).equals(p))
      {
        return i;
      }
    }
    return -1;
  }

}
