package shape;

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





}
