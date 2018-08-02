package shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

import javafx.util.Pair;


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
  List<Pair<String, Double>> getScale();

  /**
   * Getter for the red component of the shape's color.
   *
   * @return int that represents the red component of the shape's color.
   */
  int getRed();

  /**
   * Getter for the green component of the shape's color.
   * @return int that represents the green component of the shape's color.
   */
  int getGreen();

  /**
   * Getter for the blue component of the shape's color.
   * @return int that represents the blue component of the shape's color.
   */
  int getBlue();

  /**
   * Getter for the ShapeType of this shape.
   *
   * @return ShapeType of this shape.
   */
  ShapeType getShapeType();



IShape getClone();


  void setColor(Color color);

  void setPosition(Pair<String, Point2D.Double> position);

  void setScale(List<Pair<String, Double>> scale);





}
