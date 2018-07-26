package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * This class represents an EasyAnimatorViewImplFile.  This view renders the data from an
 * IEasyAnimatorModel and prints it into an output file.
 */
public class EasyAnimatorViewImplFile implements IEasyAnimatorView {

  private String outputFile;
  private String input;

  /**
   * Constructs an EasyAnimatorViewImplFile.  Takes an output file name and a string representation
   * of the data and creates the object.
   *
   * @param outputFile given output file name.
   * @param input      given input string.
   * @throws IllegalArgumentException if output file name is null.
   */
  public EasyAnimatorViewImplFile(String outputFile, String input) throws IllegalArgumentException {
    if (outputFile == null) {
      throw new IllegalArgumentException("output cannot be null!");
    }
    this.outputFile = outputFile;
    this.input = input;
  }

  /**
   * Renders the data in IEasyAnimatorModel in a form that is understandable for the user.
   * Creates a file with the given output name and writes the model data to thee file.
   *
   * @throws IllegalArgumentException if output file cannot be created.
   */
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
