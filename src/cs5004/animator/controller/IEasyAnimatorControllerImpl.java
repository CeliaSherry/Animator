package cs5004.animator.controller;

import cs5004.animator.model.IEasyAnimatorModel;
import cs5004.animator.view.EasyAnimatorViewImpl;
import cs5004.animator.view.IEasyAnimatorView;

/**
 *
 */
public class IEasyAnimatorControllerImpl implements IEasyAnimatorController {
 // private final Readable in;
 // private final Appendable out;

  /**
   * Constructor
   * @param out
   * @throws IllegalArgumentException
   */
 /* public IEasyAnimatorControllerImpl(Appendable out) throws IllegalArgumentException {
    if(out==null) {
      throw new IllegalArgumentException("Input or Output is NULL!");
    }
    //this.in = in;
    this.out = out;

  }
  */



  /**
   *
   * @param model
   * @param viewMode
   * @param output
   * @param speed
   * @throws IllegalArgumentException
   */
  @Override
  public void start(IEasyAnimatorModel model, String viewMode,
                    String output, String speed) throws IllegalArgumentException {
    if(model == null || viewMode == null || speed==null || output==null) {
      throw new IllegalArgumentException("Inputs cannot be null!");
    }

    String modelInfo;

    if(viewMode == "text") {
     modelInfo = model.toStringText(String.valueOf(speed));

    } else if(viewMode == "svg") {
     modelInfo = model.toStringSvg(String.valueOf(speed));
    } else {
      throw new IllegalArgumentException("Invalid view mode!");
    }

    IEasyAnimatorView view = new EasyAnimatorViewImpl(output, modelInfo);
    view.render();
  }



}
