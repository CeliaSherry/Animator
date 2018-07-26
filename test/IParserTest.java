import org.junit.Before;
import org.junit.Test;

import parser.IParser;
import parser.Parser;
import static org.junit.Assert.assertEquals;

/**
 * A Junit test for the IParser interface.  It tests whether the Parser class correctly
 * parses given String arrays.
 */
public class IParserTest {

  private IParser cmdArray;
  String []newArray;

  /**
   * Set up Before scenario.
   */
  @Before
  public void setUp() {
    String []temp = new String[8];
    temp[0] = "-if";
    temp[1] = "smalldemo.txt";
    temp[6] = "-iv";
    temp[7] = "text";
    temp[4] = "-o";
    temp[5] = "out";
    temp[2] = "-speed";
    temp[3] = "2";
    cmdArray = new Parser();
    newArray = cmdArray.changeOrder(temp);
  }

  /**
   * Tests if input given in wrong order is outputted as array in the correct order.
   */
  @Test
  public void testParse() {
    //-if, -iv, -o, -speed
    String temp = "smalldemo.txt text System.out 2";
    String tempString = "";
    tempString = tempString + newArray[0] + " " + newArray[1] + " " + newArray[2] + " " +
            newArray[3];
    assertEquals(temp,tempString);
  }






























}