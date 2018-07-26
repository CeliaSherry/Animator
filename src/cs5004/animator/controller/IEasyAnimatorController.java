package cs5004.animator.controller;

import cs5004.animator.model.IEasyAnimatorModel;

/**
 * This interface represents all of the operations offered by the IEasyAnimatorControllerImpl
 * class.  The start method creates and runs an animation.
 */
public interface IEasyAnimatorController {


  /**
   * Takes a model, string that specifies a view, an output file, and the speed and runs
   * an animation.  Creates a view and an output file if needed and uses the speed to have the
   * model produce the necessary input for the view and the view displays the correct output.
   *
   * @param model    given model.
   * @param viewMode String that represents the type of view to be created.
   * @param output   given output file name.
   * @param speed    given speed of animation.
   * @throws IllegalArgumentException if model, viewMode, output, or speed is null,
   *                                  or if a speed cannot be parsed or is negative.
   */
  void start(IEasyAnimatorModel model, String viewMode,
             String output, String speed) throws IllegalArgumentException;
}
