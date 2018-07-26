package cs5004.animator.view;

/**
 * This class represents all of the methods implemented by an IEasyAnimatorView.  This consists
 * of rendering the data in IEasyAnimatorModel into a format usable by the end user.
 */
public interface IEasyAnimatorView {

  /**
   * Renders the data in IEasyAnimatorModel in a form that is understandable for the user.
   * @throws IllegalArgumentException if output file cannot be created.
   */
  void render() throws IllegalArgumentException;


}
