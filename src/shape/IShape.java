package shape;

import java.awt.geom.Point2D;
import java.util.List;


/**
 * This interface represents a shape and includes basic methods that retrieve information of a
 * shape.
 */
public interface IShape {

  /**
   * Return the information such as type, position, width, height and color of this IShape in string
   * form.
   *
   * @return the information such as type, position, width, height and color in string form
   */
  String toString();


  /**
   * Return the x-y coordinates that describes the position of this shape.
   *
   * @return the x-y coordinates that describes the position of this shape.
   */
  Point2D.Double getPosition();


  /**
   * Return the name of the parameter that represents the position of this shape. For example: "Min
   * Corner" or "Center".
   *
   * @return the x-y coordinates that describes the position of this shape.
   */
  String getPositionName();

  /**
   * Return the scale of this shape which represents sized of the shape.
   *
   * @return the scale of this shape
   */
  List getScale();


  //below are just some thoughts on the potential usage of the interface

  /**
   * Return the color of this shape.
   *
   * @return the color of this shape
   */
  //Color getColor();


  /**
   * Return the position of the upper left corner of this shape.
   *
   * @return the position of the upper left corner of this shape
   */
  //Point2D.Double getCorner();


  /**
   * Return the parameter that describes the expansion
   *
   * @return the width of this shape
   */

  //double getWidth();


  /**
   * Return the height of this shape.
   *
   * @return the height of this shape
   */

  //double getHeight();


  /**
   * Set the position of the shape based on the given coordinates.
   *
   * @param x given x-coordinate
   * @param y given y-coordinate
   */
  /*
  void setPosition(double x, double y);
  */
  /**
   * Set the color of the shape based on the given color.
   *
   * @param red   the red component of the given color in 0.0 to 1.0 scale
   * @param green the red component of the given color in 0.0 to 1.0 scale
   * @param blue  the red component of the given color in 0.0 to 1.0 scale
   * @throws IllegalArgumentException if any of the components is smaller than 0.0 and greater than
   *                                  1.0.
   */

  //void setColor(float red, float green, float blue) throws IllegalArgumentException;


  /**
   * Set the width of the shape.
   *
   * @param width given width
   */
  //void setWidth(double width);

  /**
   * Set the height of the shape.
   *
   * @param height given height
   */
  //void setHeight(double height);


}
