package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EasyAnimatorViewImpl implements IEasyAnimatorView {

  private String outputFile;

  /**
   * Constructor
   * @param outputFile name of the outputFile with extension.
   */
  public EasyAnimatorViewImpl(String outputFile) throws IllegalArgumentException {
    if(outputFile == null) {
      throw new IllegalArgumentException("output cannot be null!");
    }
    this.outputFile = outputFile;
  }

  @Override
  public void render(String input) {
    if(this.outputFile == "System.out"){
      System.out.println(input);
      return;
    }
    PrintWriter writer;
    try {
      writer = new PrintWriter(this.outputFile);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Output file name is not valid.");
      }
      writer.println(input);
    }
  }
