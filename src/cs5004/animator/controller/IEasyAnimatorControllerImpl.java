package cs5004.animator.controller;

import cs5004.animator.model.IEasyAnimatorModel2;
import cs5004.animator.view.EasyAnimatorViewImplFile;
import cs5004.animator.view.EasyAnimatorViewImplOut;
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
  public void start(IEasyAnimatorModel2 model, String viewMode,
                    String output, String speed) throws IllegalArgumentException {
    if(model == null || viewMode == null || speed==null || output==null) {
      throw new IllegalArgumentException("Inputs cannot be null!");
    }

    String modelInfo;
    IEasyAnimatorView view;


    int speedInt;
    try {
      speedInt = Integer.valueOf(speed);
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot Convert Speed to Integer");
    }
    if(speedInt <= 0) {
      throw new IllegalArgumentException("Cannot Have Negative Time");
    }


    if(viewMode == "text") {
        modelInfo = model.toStringText(speedInt);

    } else if(viewMode == "svg") {
        modelInfo = model.toStringSvg(speedInt);
    } else {
      throw new IllegalArgumentException("Invalid view mode!");
    }

    if(output == "System.out") {
      view = new EasyAnimatorViewImplOut(modelInfo);
    }
    else {
      view = new EasyAnimatorViewImplFile(output,modelInfo);
    }
    view.render();
  }





}
