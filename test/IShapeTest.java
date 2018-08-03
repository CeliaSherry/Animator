import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import shape.IShape;
import shape.Oval;
import shape.Rectangle;

import static org.junit.Assert.*;

public class IShapeTest {
  private IShape shape0;
  private IShape shape1;
  private IShape shape2;
  private IShape shape3;
  @Before
  public void setUP() {
    shape0 =new Rectangle(new Point2D.Double(200f, 200f),
            new Color(1.0f, 0.0f, 0.0f), 1, 100);
    shape1 = new Oval(new Point2D.Double(500f, 100f),
            new Color(0.0f,0.0f,1.0f), 6, 100);
  }

  @Test
  public void testGetCloneRec() {
    shape3 = shape0.getClone();
    assertEquals(shape3.toString(),shape1.toString());

  }

  @Test
  public void testGetCloneOval() {
    shape3 = shape1.getClone();
    assertEquals(shape3.toString(),shape1.toString());

  }

  @Test
  public void testSetColorRec() {
    shape3 = shape0.getClone();
    shape3.setColor(100, 150, 200);
    shape2 = new Rectangle(new Point2D.Double(200f, 200f),
            new Color(100, 150,200), 1, 100);
    assertEquals(shape3.toString(), shape2.toString());

  }

  @Test
  public void setPositionRec() {
    shape3 = shape0.getClone();
    shape3.setPosition(50,50);
    shape2 = new Rectangle(new Point2D.Double(50f, 50f),
            new Color(1.0f, 0.0f, 0.0f), 1, 100);
    assertEquals(shape2.toString(), shape3.toString());

  }

  @Test
  public void setScaleRec() {
    shape3 = shape0.getClone();
    shape3.setScale(50,50);
    shape2 = new Rectangle(new Point2D.Double(200f, 200f),
            new Color(1.0f, 0.0f, 0.0f), 50, 50);
    assertEquals(shape2.toString(), shape3.toString());
  }
}