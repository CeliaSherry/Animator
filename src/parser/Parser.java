package parser;

/**
 * This class represents a Parser class.  This takes a given array with commands in any order and
 * returns an array with the commands in a specific order: -if, -iv, -i, -speed.
 */
public class Parser implements IParser {

  private String []newArray;

  /**
   * Constructs a Parser object by creating a String array.  Sets the positions that represent the
   * input file and the view to null.  Sets index 2 to System.out, the default output, and index 3
   * to 1, the default speed.
   */
  public Parser() {
    //-if, -iv, -o, -speed
    this.newArray = new String[4];
    newArray[0] = null;
    newArray[1] = null;
    newArray[2] = "System.out";
    newArray[3] = "1";
  }

  /**
   * Checks if a command has already been used in a given input.  If so, throws an Exception.
   *
   * @param i given number of times an input has been used.
   * @throws IllegalArgumentException if input command used more than once.
   */
  private void checkAttempt(int i) throws IllegalArgumentException {
    if (i > 1) {
      throw new IllegalArgumentException("Invalid Input");
    }
  }

  /**
   * Takes in command line inputs of -iv, -if, -o, and -speed and puts them in the correct order in
   * a returned array.
   *
   * @param cmdArray given array that represents the command line input.
   * @return String array with the input in the correct order.
   * @throws IllegalArgumentException if a command is repeated more than one.
   * @throws IllegalArgumentException if animation name not given.
   * @throws IllegalArgumentException if invalid view is given.
   */
  @Override
  public String[] changeOrder(String[] cmdArray) throws IllegalArgumentException {
    int i = 0;
    int ifAttempt = 0;
    int iv = 0;
    int o = 0;
    int sp = 0;

    while (i < cmdArray.length) {
      String temp = cmdArray[i];

      switch (temp) {
        case "-if":
          checkAttempt(ifAttempt);
          newArray[0] = cmdArray[i + 1];
          ++ifAttempt;
          i = i + 2;
          break;
        case "-iv":
          checkAttempt(iv);
          newArray[1] = cmdArray[i + 1];
          ++iv;
          i = i + 2;
          break;
        case "-o":
          checkAttempt(o);
          newArray[2] = cmdArray[i + 1];
          ++o;
          i = i + 2;
          break;
        case "-speed":
          checkAttempt(sp);
          newArray[3] = cmdArray[i + 1];
          ++sp;
          i = i + 2;
          break;
        default:
          throw new IllegalArgumentException("Command Not Recognized");
      }
    }


    if (this.newArray[0] == null || this.newArray[1] == null) {
      throw new IllegalArgumentException("Animation Name or View Not Provided");
    }
    if (!(this.newArray[1].equals("text") || this.newArray[1].equals("svg"))) {

      throw new IllegalArgumentException("Invalid View");
    }
    if (this.newArray[2].equals("out")) {
      this.newArray[2] = "System.out";
    }
    return newArray;
  }

}
