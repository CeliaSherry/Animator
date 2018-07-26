package parser;

/**
 * This interface represents all of the methods used by the Parser class.  This class takes
 * command line inputs that can be in any order and returns an array with the correct
 * information in a specific order.
 */
public interface IParser {

  /**
   * Takes in command line inputs of -iv, -if, -o, and -speed and puts them in the correct
   * order in a returned array.
   *
   * @param cmdArray given array that represents the command line input.
   * @return String array with the input in the correct order.
   * @throws IllegalArgumentException if a command is repeated more than one.
   * @throws IllegalArgumentException if animation name not given.
   * @throws IllegalArgumentException if invalid view is given.
   */
  String[] changeOrder(String[] cmdArray) throws IllegalArgumentException;

}
