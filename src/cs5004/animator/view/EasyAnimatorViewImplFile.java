package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintStream;


public class EasyAnimatorViewImplFile implements IEasyAnimatorView {

  private String outputFile;
  private String input;

  /**
   * Constructor
   * @param outputFile name of the outputFile with extension.
   */
  public EasyAnimatorViewImplFile(String outputFile, String input) throws IllegalArgumentException {
    if(outputFile == null) {
      throw new IllegalArgumentException("output cannot be null!");
    }
    this.outputFile = outputFile;
    this.input = input;
  }

  @Override
  public void render() {
    PrintStream writer;
    try {
      writer = new PrintStream(this.outputFile);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Output file name is not valid.");
      }
      writer.append(this.input);
    }
  }
