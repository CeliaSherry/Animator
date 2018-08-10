package cs5004.animator.view;

/**
 * This class represents an EasyAnimatorViewImplOut.  This view renders the data from an
 * IEasyAnimatorModel and prints it with System.out.
 */
public class EasyAnimatorViewImplOut implements IEasyAnimatorView {

  private String input;

  /**
   * Constructs an EasyAnimatorViewImplOut. Takes a string representation of the model data and
   * prints it to the screen.
   *
   * @param input given model data.
   */
  public EasyAnimatorViewImplOut(String input) {
    this.input = input;
  }

  /**
   * Renders the data in IEasyAnimatorModel in a form that is understandable for the user. Prints
   * model data to System.out.
   */
  @Override
  public void render() {
    System.out.println(this.input);
    return;
  }
}
