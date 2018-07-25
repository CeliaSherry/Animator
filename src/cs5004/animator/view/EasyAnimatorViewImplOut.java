package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EasyAnimatorViewImplOut implements IEasyAnimatorView {

  private String input;

  /**
   * Constructor
   */
  public EasyAnimatorViewImplOut(String input) throws IllegalArgumentException {
    this.input = input;
  }

  @Override
  public void render() {
    System.out.println(this.input);
    return;
  }
}
