package cs228hw1.stats;
/**
 * Parses Strings into Numbers.
 * @param T The type of Number to parse to.
 */
public interface IParser<T extends Number>
{
/**
 * Given a String, returns the Number it represents.
 * @param str The string form of the number.
 * @return Returns the Number represented by the input if valid. If it's not 
valid, either null is returned or an error is thrown.
 */
T parse(String str);
}


