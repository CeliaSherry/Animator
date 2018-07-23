import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IEasyAnimatorModel;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;


/**
 * This is a JUnit test for the IEasyAnimatorModel interface. It tests whether animation information
 * are stored correctly in the class by toString method. It also tests the creation of shape,
 * appearing and disappearing time setup and creation of animation.
 */

public class IEasyAnimatorModelTest {
  private IEasyAnimatorModel model;
  private String strFront;
  private String strRShape;
  private String strCShape;
  private String strR2Shape;
  private String strAnim;

  /**
   * Predefine the model to be tested. Predefine string to be compared with the output of the
   * methods.
   */
  @Before
  public void setUp() {
    model = new EasyAnimatorModelImpl();
    strFront = "Shapes:\n";
    strRShape = "Name: R\n"
            + "Type: Rectangle\n"
            + "Min Corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n"
            + "\n";
    strCShape = "Name: C\n"
            + "Type: Oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n"
            + "\n";
    strR2Shape = "Name: R2\n"
            + "Type: Rectangle\n"
            + "Min Corner: (200.0,200.0), Width: 80.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=150\n"
            + "\n";
    strAnim = "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
            + "shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
            + "shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80\n"
            + "shape R scales from Width: 40.0, Height: 100.0 to Width: 20.0, Height: 100.0 "
            + "from t=51 to t=70\n"
            + "shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100";
  }


  /**
   * Test the constructor of a EasyAnimatorModelImpl. It doesn't have any input parameters, thus
   * should not throw any exception.
   */
  @Test
  public void testModelConstructor() {
    try {
      new EasyAnimatorModelImpl();
    } catch (Exception e) {
      fail("No Exception should have been thrown!");
    }

  }

  /**
   * Test the allAnimationToString method in the model with both transitional shapes and animations
   * present.Shapes should be shown in the order of their appearing time, and animations should be
   * shown in the order of their staring time. If two shapes appear at the same time or two
   * animations start at the same time, they will shown according to the time they are added to the
   * list.
   */
  @Test
  public void testAllAnimationToStringShapesAndAnimation() {
    //adding shapes
    model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
    model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
    //adding animations
    model.addMove("R", 200f, 200f, 300f, 300f, 10, 50);
    model.addMove("C", 500f, 100f, 500f, 400f, 20, 70);
    model.addMove("R", 300f, 300f, 200f, 200f, 70, 100);
    model.addChangeColor("C", 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 50, 80);
    model.addScaleAnimation("R", 40, 100, 20, 100, 51, 70);

    assertEquals(strFront + strRShape + strCShape + strAnim, model.toString());
  }


  /**
   * Test the allAnimationToString method in the model with only transitional shapes present. Shapes
   * should be shown in the order of their appearing time, and animations should be shown in the
   * order of their staring time. If two shapes appear at the same time or two animations start at
   * the same time, they will shown according to the time they are added to the list.
   */
  @Test
  public void testAllAnimationToStringShapesOnly() {

    //adding shapes
    model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
    model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);

    assertEquals(strFront + strRShape + strCShape, model.toString());
  }


  /**
   * Test the allAnimationToString method in the model with no shapes/transShapes/animation added
   * yet. Should return an empty string.
   */
  @Test
  public void testAllAnimationToStringEmptyShapes() {
    assertEquals("", model.toString());
  }

  /**
   * Test the addRectangle and addOval methods with valid input. By assigning appear time and
   * disappear time to the shapes we can estimate the shapes in String format. Then the expected
   * result and actual result are compared. Insertion should occur at the position where all
   * ITransitionalShapes in the list appearing at the same time or before the given
   * ITransitionalShape are also before it in the list, and all ITransitionalShapes in the list
   * appearing after the given ITransitionalShape are behind it in the list.
   */
  @Test
  public void testValidAddShapes() {
    try {
      model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
      model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
      model.addRectangle("R2", 200f, 200f, 80f, 100f, 1.0f, 0.0f, 0.0f, 1, 150);

      assertEquals(strFront + strRShape + strR2Shape + strCShape,
              model.toString());

    } catch (IllegalArgumentException e) {
      fail("Adding valid shapes! No exception should have been thrown!");
    }
  }

  /**
   * Test the addRectangle and addOval methods with all possible invalid inputs. An exception should
   * be thrown if the ID already exists. An exception should also be thrown if appear time >=
   * disappear time or either of them is less then zero. If any color component, width, height,
   * xRadius, yRadius is less than 0, an exception is also expected.
   */

  @Test
  public void testIllegalAddShapes() {
    model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
    model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);

    //the same shapeID cannot be added to the HashMap twice
    try {
      model.addRectangle("R", 300f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
      fail("ID already exists. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected. The same ID cannot be added twice to the HashMap
    }


    //the same shapeID cannot be added to the HashMap twice
    try {
      model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
      fail("ID already exists. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected. The same ID cannot be added twice to the HashMap
    }


    // appearTime > disappearTime
    try {
      model.addRectangle("D", 300f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 101, 100);
      fail("appearTime > disappearTime. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected.
    }


    // appearTime = disappearTime
    try {
      model.addRectangle("D", 300f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 100, 100);
      fail("appearTime = disappearTime. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected.
    }


    // appearTime < 0
    try {
      model.addRectangle("D", 300f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, -100, 100);
      fail("appearTime < 0. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected.
    }


    // color component < 0
    try {
      model.addRectangle("M", 300f, 200f, 50f, 100f, -1.0f, 0.0f, 0.0f, 6, 100);
      fail("color component < 0. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected.
    }


    // width < 0
    try {
      model.addRectangle("M", 300f, 200f, -50f, 100f, 1.0f, 0.0f, 0.0f, 6, 100);
      fail("width < 0. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected.
    }


    // height < 0
    try {
      model.addRectangle("M", 300f, 200f, 50f, -100f, 1.0f, 0.0f, 0.0f, 6, 100);
      fail("height < 0. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected.
    }


    // xRadius < 0
    try {
      model.addOval("E", 500f, 100f, -60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
      fail("xRadius < 0 An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected. The same ID cannot be added twice to the HashMap
    }

    // yRadius < 0
    try {
      model.addOval("E", 500f, 100f, 60f, -30f, 0.0f, 0.0f, 1.0f, 6, 100);
      fail("yRadius < 0. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected. The same ID cannot be added twice to the HashMap
    }

  }

  /**
   * Test the methods that add animation to the model, including addMove, addChangeColor, and
   * addScaleAnimation method. Insertion should occur at the position where all IAnimations in the
   * list starting at the same time or before the given IAnimation are also before it in the list,
   * and all IAnimations in the list starting after the given ITransitionalShape are behind it in
   * the list. Adding animations of the same type to the same shape is allowed if they don't happen
   * at overlapping time period. Adding animation of different types is always allowed (i.e there
   * will not be any time conflict).
   */

  @Test
  public void testValidAddAnimations() {
    model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
    model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);
    model.addRectangle("R2", 200f, 200f, 80f, 100f, 1.0f, 0.0f, 0.0f, 1, 150);

    try {
      model.addScaleAnimation("R", 40.0f, 100.0f, 20.0f, 100.0f, 51, 70);
      model.addChangeColor("C", 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 50, 80);
      model.addMove("R", 300.0f, 300.0f, 200.0f, 200.0f, 70, 100);
      model.addMove("C", 500.0f, 100.0f, 500.0f, 400.0f, 20, 70);
      model.addMove("R", 200.0f, 200.0f, 300.0f, 300.0f, 10, 50);
      model.addMove("R2", 200f, 200f, 300f, 300f, 10, 50);

    } catch (IllegalArgumentException e) {
      fail("Inputs for addAnimation are legal. No exception is excepted!");
    }

    String temp2 = "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
            + "shape R2 moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
            + "shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
            + "shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80\n"
            + "shape R scales from Width: 40.0, Height: 100.0 to Width: 20.0, Height: 100.0 "
            + "from t=51 to t=70\n"
            + "shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100";
    assertEquals(strFront + strRShape + strR2Shape + strCShape + temp2,
            model.toString());
  }


  /**
   * Test whether the addAnimation method throws an exception with all cases of Illegal inputs:
   * shapeID doesn't exist, or startTime and endTime is not within the time range of the appearTime
   * and disappearTime the corresponding shape, or given animation has time conflict with another
   * animation of the same type and of the same shape, or trying to scaling a shape to a negative
   * width or height.
   */

  @Test
  public void testIllegalAddAnimations() {

    model.addOval("C", 500f, 100f, 60f, 30f, 0.0f, 0.0f, 1.0f, 6, 100);
    model.addRectangle("R", 200f, 200f, 50f, 100f, 1.0f, 0.0f, 0.0f, 1, 100);

    model.addScaleAnimation("R", 40.0f, 100.0f, 20.0f, 100.0f, 51, 70);
    model.addChangeColor("C", 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 50, 80);
    model.addMove("R", 300.0f, 300.0f, 200.0f, 200.0f, 70, 100);
    model.addMove("C", 500.0f, 100.0f, 500.0f, 400.0f, 20, 70);
    model.addMove("R", 200.0f, 200.0f, 300.0f, 300.0f, 10, 50);


    //shapeID doesn't exist
    try {
      model.addMove("R3", 300.0f, 300.0f, 200.0f, 200.0f, 70, 100);
      fail("shapeID doesn't exist! An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }


    //startTime and endTime is not within the time range
    // of the appearTime and disappearTime of the corresponding shape
    try {
      model.addMove("R", 300.0f, 300.0f, 200.0f, 200.0f, 70, 150);
      fail("StartTime is earlier than the appear time. An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }

    // animation has time conflict with another animation
    // of the same type and of the same shape in the list
    try {
      model.addMove("R", 300.0f, 300.0f, 200.0f, 200.0f, 20, 80);
      fail("Time conflict with another animation. An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }

    //negative scale parameter
    try {
      model.addScaleAnimation("R", -200f, 200f, 300f, 400f, 20, 60);
      fail("negative scale parameter! An exception is expected");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }
  }

}