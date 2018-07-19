import org.junit.Before;
import org.junit.Test;


import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import animation.ColorChangeAnimation;
import animation.IAnimation;
import animation.MoveAnimation;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IEasyAnimatorModel;
import shape.IShape;
import shape.Rectangle;
import shape.Oval;

import javafx.util.Pair;


import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;


/**
 * This is a JUnit test for the IEasyAnimatorModel interface. It tests whether animation information
 * are stored correctly in the class by toString method. It also tests the creation of shape,
 * appearing and disappearing time setup and creation of animation.
 */

public class IEasyAnimatorTest {
  private IShape rec1;
  private IShape rec2;
  private IShape oval1;
  private IShape oval2;
  private IAnimation move1;
  private IAnimation move2;
  private IAnimation move3;
  private IAnimation changeColor1;
  private IAnimation scale1;
  private IEasyAnimatorModel model;
  private String strFront;
  private String strRShape;
  private String strCShape;
  private String strR2Shape;
  private String strAnim;


  /**
   * Predefine the IShapes, ITransitionalShapes and Animations for testing the model.
   */
  @Before
  public void setUp() {
    HashMap<String, Double> scaleRatio;
    List<Pair<String, Double>> startScale;

    rec1 = new Rectangle(new Point2D.Double(200f, 200f), new Color(1.0f, 0.0f, 0.0f), 50f, 100f);
    rec2 = new Rectangle(new Point2D.Double(200f, 200f), new Color(1.0f, 0.0f, 0.0f), 80f, 100f);
    oval1 = new Oval(new Point2D.Double(500f, 100f), new Color(0.0f, 0.0f, 1.0f), 60f, 30f);
    oval2 = new Oval(new Point2D.Double(500f, 100f), new Color(0.0f, 0.0f, 1.0f), 90f, 30f);

    move1 = new MoveAnimation(10, 50, "R",
            new Point2D.Double(200f, 200f), new Point2D.Double(300f, 300f));
    move2 = new MoveAnimation(20, 70, "C", new Point2D.Double(500f, 100f),
            new Point2D.Double(500f, 400f));
    move3 = new MoveAnimation(70, 100, "R",
            new Point2D.Double(300f, 300f), new Point2D.Double(200f, 200f));
    changeColor1 = new ColorChangeAnimation(50, 80, "C",
            new Color(0.0f, 0.0f, 1.0f), new Color(0.0f, 1.0f, 0.0f));
    scaleRatio = new HashMap<>();
    scaleRatio.put("Width", 0.5);
    scaleRatio.put("Height", 1.0);
    startScale = new ArrayList<>();
    startScale.add(new Pair<>("Width", 40.0));
    startScale.add(new Pair<>("Height", 100.0));

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
            + "shape C changes color from (0.0,1.0,0.0) to (0.0,0.0,1.0) from t=50 to t=80\n"
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



    model.addRectangle("R",200f,200f,50f,100f,1.0f,0.0f,0.0f,1,100);
    model.addOval("C",500f,100f, 60f,30f,0.0f,0.0f,1.0f,6,100);


    model.addMove("R",200f,200f,300f,300f,10,50);
    model.addMove("C",500f,100f,500f,400f, 20,70);
    model.addMove("R", 300f, 300f, 200f, 200f,70,100);
    model.addChangeColor("C",0.0f,0.0f, 1.0f,0.0f, 1.0f, 0.0f,50,80);
    model.addScaleAnimation("R",40,100,20,100,51,70);


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


    model.addRectangle("R",200f,200f,50f,100f,1.0f,0.0f,0.0f,1,100);
    model.addOval("O",500f,100f, 60f,30f,0.0f,0.0f,1.0f,6,10);

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
   * Test the addShape method with valid input. By assigning appear time and disappear time to the
   * shapes (using addTransShape method) we can estimate the shapes in String format. Then the
   * expected result and actual result are compared.
   */
  //@Test
 /* public void testValidAddShape() {
    try {
      model.addShape(rec1, "R");
      model.addShape(oval1, "C");
      model.addTransShape("R", 1, 100);
      model.addTransShape("C", 6, 100);
      assertEquals(strFront + strRShape + strCShape, model.toString());

    } catch (IllegalArgumentException e) {
      fail("Adding shapes! No exception should have been thrown!");
    }
  }
*/
  /**
   * Test the addShape method with all possible invalid inputs. An exception should be thrown if the
   * ID already exists. An exception should also be thrown if the added shape is null or already
   * exist in the HashMap.
   */
 /*
  @Test
  public void testIllegalAddShape() {
    model.addShape(rec1, "R");
    model.addShape(oval1, "C");
    //null shape cannot be added to the the HashMap
    try {
      model.addShape(null, "A");
      fail("shape is null! An exception should have be thrown!");
    } catch (IllegalArgumentException e) {
      //shape object is null. Exception is expected.
    }

    //the same shape (same object address) cannot be added to the HashMap twice

    try {
      model.addShape(rec1, "K");
      fail("shape already added! An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected. the same shape (same object address) cannot be added to the HashMap
    }

    //the same shapeID cannot be added to the HashMap twice
    try {
      model.addShape(oval2, "R");
      fail("ID already exists. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //Exception is expected. The same ID cannot be added twice to the HashMap
    }
  }
*/
  /**
   * Test the addTransShape with valid input. Insertion should occur at the position where all
   * ITransitionalShapes in the list appearing at the same time or before the given
   * ITransitionalShape are also before it in the list, and all ITransitionalShapes in the list
   * appearing after the given ITransitionalShape are behind it in the list.
   */
  /*
  @Test
  public void testValidAddTransShape() {
    model.addShape(rec1, "R");
    model.addShape(oval1, "C");
    model.addShape(rec2, "R2");
    try {
      model.addTransShape("C", 6, 100);
      model.addTransShape("R", 1, 100);
      model.addTransShape("R2", 1, 150);
    } catch (IllegalArgumentException e) {
      fail("Valid transhape input, no exception should have been thrown!");
    }


    assertEquals(strFront + strRShape + strR2Shape + strCShape,
            model.toString());
  }
  */

  /**
   * Test whether the addTransShape method throws an exception with all cases of Illegal inputs:
   * shapeID doesn't exist, or shapeID already assigned to another ITransitionalShape, or appearTime
   * >= disappearTime, or appearTime < 0.
   */
  /*
  @Test
  public void testIllegalAddTransShape() {
    model.addShape(rec1, "R");
    model.addShape(oval1, "C");
    model.addTransShape("C", 1, 100);

    //shapeID doesn't exist
    try {
      model.addTransShape("B", 3, 5);
      fail("ShapeID doesn't exist. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //ShapeID doesn't exist. exception expected
    }

    //shapeID already assigned to another ITransitionalShape
    try {
      model.addTransShape("C", 3, 5);
      fail("shapeID already assigned to another ITransitionalShape. "
              + "An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //shapeID already assigned to another ITransitionalShape. exception expected
    }

    //startTime = endTime
    try {
      model.addTransShape("R", 0, 0);
      fail("startTime >= endTime. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //startTime = endTime. exception expected
    }

    //startTime > endTime
    try {
      model.addTransShape("R", 8, 5);
      fail("startTime >= endTime. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //startTime > endTime. exception expected
    }

    //startTime > endTime
    try {
      model.addTransShape("R", -8, 5);
      fail("startTime < 0. An exception should have been thrown!");
    } catch (IllegalArgumentException e) {
      //startTime < 0. exception expected
    }
  }
*/
  /**
   * Test the addAnimation method. Insertion should occur at the position where all IAnimations in
   * the list starting at the same time or before the given IAnimation are also before it in the
   * list, and all IAnimations in the list starting after the given ITransitionalShape are behind it
   * in the list.
   */
  /*
  @Test
  public void testValidAddAnimation() {
    model.addShape(rec1, "R");
    model.addShape(oval1, "C");
    model.addShape(rec2, "R2");
    model.addTransShape("R", 1, 100);
    model.addTransShape("C", 6, 100);
    model.addTransShape("R2", 1, 150);


    IAnimation move4 = new MoveAnimation(10, 50, "R2",
            new Point2D.Double(200f, 200f), new Point2D.Double(300f, 300f));

    try {
      model.addAnimation(scale1);
      model.addAnimation(changeColor1);
      model.addAnimation(move3);
      model.addAnimation(move2);
      model.addAnimation(move1);
      model.addAnimation(move4);
    } catch (IllegalArgumentException e) {
      fail("Inputs for addAnimation are legal. No exception is excepted!");
    }

    String temp2 = "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
            + "shape R2 moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
            + "shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
            + "shape C changes color from (0.0,1.0,0.0) to (0.0,0.0,1.0) from t=50 to t=80\n"
            + "shape R scales from  Width: 40.0, Height: 100.0 to Width: 20.0, Height: 100.0 "
            + "from t=51 to t=70\n"
            + "shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100";
    assertEquals(strFront + strRShape + strR2Shape + strCShape + temp2,
            model.toString());
  }
*/

  /**
   * Test whether the addAnimation method throws an exception with all cases of Illegal inputs:
   * shapeID doesn't exist, or animation object is null, or startTime and endTime is not within the
   * time range of the appearTime and disappearTime of the ITransShape, or given animation has time
   * conflict with another animation of the same type and of the same shape.
   */
  /*
  @Test
  public void testIllegalAddAnimation() {
    model.addShape(rec1, "R");
    model.addShape(oval1, "C");
    model.addTransShape("C", 6, 100);
    model.addTransShape("R", 1, 100);
    model.addAnimation(move1);
    model.addAnimation(move2);
    model.addAnimation(move3);
    model.addAnimation(changeColor1);
    model.addAnimation(scale1);


    //shapeID doesn't exist
    IAnimation move4 = new MoveAnimation(10, 50, "R3",
            new Point2D.Double(200f, 200f), new Point2D.Double(300f, 300f));
    try {
      model.addAnimation(move4);
      fail("shapeID doesn't exist! An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }

    //animation to be added is null
    try {
      model.addAnimation(null);
      fail("Given animation is null. An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }


    //startTime and endTime is not within the time range
    // of the appearTime and disappearTime of the ITransShape
    IAnimation move5 = new MoveAnimation(0, 8, "R",
            new Point2D.Double(200f, 200f), new Point2D.Double(300f, 300f));
    try {
      model.addAnimation(move5);
      fail("StartTime is earlier than the appear time. An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }

    // animation has time conflict with another animation
    // of the same type and of the same shape in the list
    IAnimation move6 = new MoveAnimation(8, 30, "R",
            new Point2D.Double(200f, 200f), new Point2D.Double(300f, 300f));
    try {
      model.addAnimation(move6);
      fail("Time conflict with another animation. An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }

    // the same animation object cannot be added twice
    try {
      model.addAnimation(move1);
      fail("Time conflict with another animation. An exception should have been thrown");
    } catch (IllegalArgumentException e) {
      //exception is expected
    }

  }
  */
}