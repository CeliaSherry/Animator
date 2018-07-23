package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EasyAnimatorViewImpl implements IEasyAnimatorView {

  private String outputFile;
  private String input;

  /**
   * Constructor
   * @param outputFile name of the outputFile with extension.
   */
  public EasyAnimatorViewImpl(String outputFile, String input) throws IllegalArgumentException {
    if(outputFile == null) {
      throw new IllegalArgumentException("output cannot be null!");
    }
    this.outputFile = outputFile;
    this.input = input;
  }

  @Override
  public void render() {
    if(this.outputFile == "System.out"){
      System.out.println(this.input);
      return;
    }
    PrintWriter writer;
    try {
      writer = new PrintWriter(this.outputFile);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Output file name is not valid.");
      }
      writer.println(this.input);
    }
  }
