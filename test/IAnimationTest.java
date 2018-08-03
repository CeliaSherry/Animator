import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import animation.IAnimation;
import animation.MoveAnimation;

import static org.junit.Assert.*;

public class IAnimationTest {
  IAnimation animation0;

  @Before
  public void setUp(){


    animation0 = new MoveAnimation(10,50,
            "R",new Point2D.Double(200,200), new Point2D.Double(300,300));

  }

  @Test
  public void updateAtTime() {
  }

  @Test
  public void testFormula(){
    assertEquals(250,animation0.formula(30,10,
            50,200,300));
  }
}