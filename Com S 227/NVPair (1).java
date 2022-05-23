package api;

/**
 * Represents a name/value pair.
 */
public class NVPair
{
  /**
   * Name for this name/value pair.
   */
  private final String name;
  
  /**
   * Value for this name/value pair. 
   */
  private int value;

  /**
   * Construct a new NVPair with the given name and value zero.
   * @param n
   *   the string name for this pair
   */
  public NVPair(String n)
  {
    name = n;
  }

  /**
   * Construct a new NVPair with the given name and value.
   * @param n
   *   string name for this pair
   * @param v
   *   int value for this pair
   */
  public NVPair(String n, int v)
  {
    name = n;
    value = v;
  }

  /**
   * Returns the name for this pair.
   * @return
   *   name for this pair
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns the value for this pair.
   * @return
   *   value for this pair
   */
  public int getValue()
  {
    return value;
  }

  /**
   * Sets the value for this pair.
   * @param v
   *   new value for this pair
   *   
   */
  public void setValue(int v)
  {
    value = v;
  }

  /**
   * Determines whether this object is equal to the given object.
   * @param obj
   *   any object
   * @return
   *   true if the given object is an NVPair with the same name and value
   *   as this one, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    
    NVPair other = (NVPair) obj;
    return name.equals(other.getName()) && value == other.getValue();
  }
  
  /**
   * Returns a string representation of this object in the form
   * "name:value".
   */
  public String toString()
  {
    return name + ":" + value;
  }
}
