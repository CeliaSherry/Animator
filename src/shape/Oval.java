package shape;

import java.awt.Color;
import java.awt.geom.Point2D;

import javafx.util.Pair;


/**
 * This class represents a shape of shape.Oval by implementing the AShape class, including
 * information of its center position, x-Radius and y-Radius. x-Radius is used to describe the scale
 * of the shape along x-axis, likewise, y-Radius is used to describe the scale of the shape along
 * y-axis.
 */
public class Oval extends AShape {
  /**
   * Constructor for an Oval object.
   *
   * @param center  given center coordinates
   * @param color   given color
   * @param xRadius given horizontal radius
   * @param yRadius given vertical radius
   * @throws IllegalArgumentException if xRadius < 0 or yRadius < 0
   */
  public Oval(Point2D.Double center, Color color, double xRadius, double yRadius)
          throws IllegalArgumentException {
    super(new Pair<>("Center", center), color, ShapeType.Oval);
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("Width and height cannot be negative!");
    }
    this.getScale().add(new Pair<>("X radius", xRadius));
    this.getScale().add(new Pair<>("Y radius", yRadius));

  }

  @Override
  public IShape getClone() {
    float[] tempColor = new float[3];
    color.getColorComponents(tempColor);
    return new Oval(new Point2D.Double(this.position.getValue().getX(),
            this.position.getValue().getY()),
            new Color(tempColor[0], tempColor[1], tempColor[2]),
            this.getScale().get(0).getValue(),
            this.getScale().get(1).getValue());
  }


}
