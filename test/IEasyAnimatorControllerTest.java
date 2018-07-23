import cs5004.animator.controller.IEasyAnimatorController;
import cs5004.animator.controller.IEasyAnimatorControllerImpl;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IEasyAnimatorModel;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for IEasyAnimatorController interface. It tests the start method of the controller
 * that adds information to the easy animator model, and return the view written in a file as
 * required in the command line.
 */
public class IEasyAnimatorControllerTest {

  private IEasyAnimatorModel model;
  private IEasyAnimatorController controller;

  /**
   * Predefine model to be tested.
   */
  public void setUp() {

    model = new EasyAnimatorModelImpl();
    //adding shapes
    model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
    model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
    //adding animations
    model.addMove("R", 200f, 200f, 300f, 300f, 10, 50);
    model.addMove("C", 500f, 100f, 500f, 400f, 20, 70);
    model.addMove("R", 300f, 300f, 200f, 200f, 70, 100);
    model.addChangeColor("C", 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 50, 80);
    model.addScaleAnimation("R", 40, 100, 20, 100, 51, 70);
    controller = new IEasyAnimatorControllerImpl();
  }

  /**
   * Test the start method with valid inputs: "text" view, "System.out", and speed of 2.
   */
  public void testValidStartText(){
    controller.start(model,"text","System.out", "2");

  }


  /**
   * Test the start method with valid inputs: "text" view, "System.out", and speed of 2.
   */
  public void testValidStartSvg(){
    controller.start(model,"svg","System.out", "2");
  }




}