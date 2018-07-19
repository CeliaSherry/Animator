package shape;


import java.awt.Color;
import java.awt.geom.Point2D;

import javafx.util.Pair;


/**
 * This class represents a shape of Rectangle, including its left-bottom corner as position,
 * width and height as scale, and color.
 */
public class Rectangle extends AShape {

  /**
   * Constructor for a Rectangle object with given parameters. Width and Height are stored in the
   * HashMap scale and must be positive.
   *
   * @param corner given X-Y coordinates of the upper left corner
   * @param color  given color
   * @param width  given width
   * @param height given height
   * @throws IllegalArgumentException if width or height is smaller than 0
   */
  public Rectangle(Point2D.Double corner, Color color, double width, double height)
          throws IllegalArgumentException {
    super(new Pair("Min Corner", corner), color, ShapeType.Rectangle);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height cannot be negative!");
    }
    this.getScale().add(new Pair("Width", width));
    this.getScale().add(new Pair("Height", height));

  }



  //below are just some thoughts on the potential usage of the class


  /*
  @Override
  public Point2D.Double getCorner() {
    return this.corner;
  }
  */
}
