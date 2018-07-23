package parser;

public class Parser implements IParser {

  private String newArray[];

  public Parser() {
    //-if, -iv, -o, -speed
    this.newArray = new String[4];
    newArray[0] = null;
    newArray[1] = null;
    newArray[2] = "System.out";
    newArray[3] = "1";
  }

  @Override
  public String[] changeOrder(String []cmdArray) throws IllegalArgumentException{
    int i = 0;

    while(i < cmdArray.length) {
      String temp = cmdArray[i];

      switch (temp) {
        case "-if":
          newArray[0] = cmdArray[i+1];
          i = i+2;
          break;
        case "-iv":
          newArray[1] = cmdArray[i+1];
          i=i+2;
          break;
        case "-o":
          newArray[2] = cmdArray[i+1];
          i=i+2;
          break;
        case "-speed":
          newArray[3] = cmdArray[i+1];
          i=i+2;
          break;
        default:
          throw new IllegalArgumentException("Command Not Recognized");

      }
    }
    if(this.newArray[0] == null || this.newArray[1] == null) {
      throw new IllegalArgumentException("Animation Name or View Not Provided");
    }
    if(!(this.newArray[1].equals("text") || this.newArray[1].equals("svg"))) {
      throw new IllegalArgumentException("Invalid View");
    }
    if(this.newArray[2].equals("out")) {
      this.newArray[2] = "System.out";
    }
    return newArray;
  }


  //check that speed is number with try/catch
}
