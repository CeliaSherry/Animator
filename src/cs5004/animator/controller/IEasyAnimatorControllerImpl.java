package cs5004.animator.controller;

import cs5004.animator.model.IEasyAnimatorModel;
import cs5004.animator.view.EasyAnimatorViewImplFile;
import cs5004.animator.view.EasyAnimatorViewImplOut;
import cs5004.animator.view.IEasyAnimatorView;

/**
 * This class represents a controller that runs an animation.  It takes in a model and passes
 * information to a view for the user to see.
 */
public class IEasyAnimatorControllerImpl implements IEasyAnimatorController {

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
  @Override
  public void start(IEasyAnimatorModel model, String viewMode,
                    String output, String speed) throws IllegalArgumentException {

    String modelInfo;
    IEasyAnimatorView view;
    int speedInt;

    if (model == null || viewMode == null || speed == null || output == null) {
      throw new IllegalArgumentException("Inputs cannot be null!");
    }
    try {
      speedInt = Integer.valueOf(speed);
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot Convert Speed to Integer");
    }
    if (speedInt <= 0) {
      throw new IllegalArgumentException("Cannot Have Negative Speed");
    }

    if (viewMode.equals("text")) {
      modelInfo = model.toStringText(speedInt);
    } else if (viewMode.equals("svg")) {
      modelInfo = model.toStringSvg(speedInt);
    } else {
      throw new IllegalArgumentException("Invalid view mode!");
    }

    if (output.equals("System.out")) {
      view = new EasyAnimatorViewImplOut(modelInfo);
    } else {
      view = new EasyAnimatorViewImplFile(output, modelInfo);
    }
    view.render();
  }

}
