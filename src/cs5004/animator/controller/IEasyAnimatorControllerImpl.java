package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;


import javax.swing.Timer;
import javax.swing.JOptionPane;

import cs5004.animator.model.IEasyAnimatorModel;
import cs5004.animator.view.EasyAnimatorViewGui;
import cs5004.animator.view.EasyAnimatorViewImplFile;
import cs5004.animator.view.EasyAnimatorViewImplOut;
import cs5004.animator.view.IEasyAnimatorView;

/**
 * This class represents a controller that runs an animation.  It takes in a model and passes
 * information to a view for the user to see.
 */
public class IEasyAnimatorControllerImpl implements IEasyAnimatorController {
  private Timer timer;
  private Timer overallTimer;
  private int frameTick = 0;
  private int currentSpeed;

  /**
   * Takes a model, string that specifies a view, an output file, and the speed and runs an
   * animation. Creates a view and an output file if needed and uses the speed to have the model
   * produce the necessary input for the view and the view displays the correct output.
   *
   * @param model    given model.
   * @param viewMode String that represents the type of view to be created.
   * @param output   given output file name.
   * @param speed    given speed of animation.
   * @throws IllegalArgumentException if model, viewMode, output, or speed is null, or if a speed
   *                                  cannot be parsed or is negative.
   */
  @Override
  public void start(IEasyAnimatorModel model, String viewMode,
                    String output, String speed) throws IllegalArgumentException {

    String modelInfo = null;
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
    } else if (!(viewMode.equals("visual"))) {
      throw new IllegalArgumentException("Invalid view mode!");
    }

    if (output.equals("System.out") && (!(viewMode.equals("visual")))) {
      view = new EasyAnimatorViewImplOut(modelInfo);
      view.render();
    } else if (viewMode.equals("visual")) {
      view = new EasyAnimatorViewGui();
      this.currentSpeed = speedInt;

      overallTimer = new Timer(1000 / speedInt, new ActionListener() {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          if (((EasyAnimatorViewGui) view).start()) {
            startTimer(timer);
            ((EasyAnimatorViewGui) view).setStart(false);
          } else if (((EasyAnimatorViewGui) view).pause()) {
            stopTimer(timer);
            ((EasyAnimatorViewGui) view).setPause(false);
          } else if (((EasyAnimatorViewGui) view).restart()) {
            restartTimer(timer);
            ((EasyAnimatorViewGui) view).setRestart(false);
          } else if (((EasyAnimatorViewGui) view).increase()) {
            increaseSpeed(timer);
            ((EasyAnimatorViewGui) view).setIncrease(false);
          } else if (((EasyAnimatorViewGui) view).decrease()) {
            decreaseSpeed(timer);
            ((EasyAnimatorViewGui) view).setDecrease(false);
          } else if (((EasyAnimatorViewGui) view).save()) {
            makeSave(model);
            ((EasyAnimatorViewGui) view).setSave(false);
          }

        }
      });
      this.startTimer(overallTimer);

      timer = new Timer(1000 / speedInt, new ActionListener() {

        /**
         * Set data of the shapes at the current frame tick to the view.
         * @param e given action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          ((EasyAnimatorViewGui) view).setData(model.shapesAtFrame(frameTick));
          frameTick++;
        }

      });


    } else {
      view = new EasyAnimatorViewImplFile(output, modelInfo);
      view.render();
    }


  }


  private void startTimer(Timer timer) {
    timer.start();

  }

  private void restartTimer(Timer timer) {
    this.frameTick = 0;
    this.startTimer(timer);
  }


  private void stopTimer(Timer timer) {
    timer.stop();
  }


  private void increaseSpeed(Timer timer) {
    int temp = this.currentSpeed;
    this.currentSpeed = temp + 5;
    timer.setDelay(1000 / this.currentSpeed);

  }

  private void decreaseSpeed(Timer timer) {
    if (this.currentSpeed >= 6) {
      int temp = this.currentSpeed;
      this.currentSpeed = temp - 5;
      timer.setDelay(1000 / this.currentSpeed);
    }
  }

  private void makeSave(IEasyAnimatorModel model) throws IllegalArgumentException {
    stopTimer(timer);

    PrintStream writer;
    String fileName = JOptionPane.showInputDialog(null, "Save Animation to SVG"
            , "Input File Name Without Extension: ", JOptionPane.PLAIN_MESSAGE);

    if (fileName == null) {
      startTimer(timer);
      return;

    }

    fileName = fileName + ".svg";


    try {
      writer = new PrintStream(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Output file name is not valid.");
    }

    writer.append(model.toStringSvg(this.currentSpeed));
    startTimer(timer);
  }

}