package shape;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;


/**
 * This abstract class represents a general shape of a certain color, of a certain type, at a
 * certain position and of a certain scale, which are the common features shared by all shapes.
 */
public abstract class AShape implements IShape {
  private Color color;

  // this field is used in the toString method, which requires adding the type information according
  // to the specific type of animation.
  private ShapeType type;

  /* As different shapes may use different definition of positions, this pair structure stores the
   name defining the position and the corresponding coordinates. For example, a rectangular use
   its minimum corner as its position, whereas an oval use its center as its position.*/
  private Pair<String, Point2D.Double> position;


  /* As different shapes may use different definition of scales, this list of  pair structure stores
   a list of the name defining a scale parameter and the corresponding coordinates. For example, a
   rectangular use width and height as its scale, whereas an oval use its X radius and Y radius as
   its scale. It is flexible and practical if we decide to introduce more shapes later */
  private final List<Pair<String, Double>> scale;


  /**
   * Constructor for AShape class with given parameters. Depending on the specific shape, the exact
   * content of the fields may differ.
   *
   * @param position given coordinates that describe the position of a shape generally
   * @param color    given color
   * @param type     given type of shape
   * @throws IllegalArgumentException if width < 0 or height < 0
   */
  public AShape(Pair<String, Point2D.Double> position, Color color, ShapeType type)
          throws IllegalArgumentException {

    this.position = new Pair<>(position.getKey(),
            new Point2D.Double(position.getValue().getX(), position.getValue().getY()));

    //color in 0.0 to 1.0 scale inclusively
    float[] tempColor = new float[3];
    color.getColorComponents(tempColor);
    this.color = new Color(tempColor[0], tempColor[1], tempColor[2]);
    this.scale = new ArrayList<>();
    this.type = type;

  }

  /**
   * Return information about this AShape, including type, position, scale information and color in
   * String format. For example: "Type: Rectangle\nMin corner: (200.0,200.0), Width: 50.0, Height:
   * 100.0, Color: (1.0,0.0,0.1)\n".
   *
   * @return information including type, position, scales and color
   */

  @Override
  public String toString() {
    String result = "";
    result += "Type: " + this.type + "\n" + this.getPositionName() + ": ("
            + String.format("%.1f", this.getPosition().getX()) + ","
            + String.format("%.1f", this.getPosition().getY())
            + "), "
            + this.scaleToString()
            + colorToString()
            + "\n";
    return result;
  }

  /**
   * Return the color of this shape in string form. For example: "Color: (1.0,0.0,0.1)".
   *
   * @return the color of this shape in string form
   */
  protected String colorToString() {
    String result = "Color: (";
    float[] tempColor = new float[3];
    color.getColorComponents(tempColor);
    result += String.format("%.1f", tempColor[0]) + ","
            + String.format("%.1f", tempColor[1]) + ","
            + String.format("%.1f", tempColor[2]) + ")";
    return result;

  }


  /**
   * Return the scale of this shape in string form. For example: "Width: 50.0, Height: 100.0, ".
   *
   * @return the scale of this shape in string form
   */
  protected String scaleToString() {
    String result = "";
    if (this.scale.size() == 0) {
      return result;
    }
    for (Pair<String, Double> scaleParam : scale) {
      result += scaleParam.getKey() + ": " + String.format("%.1f", scaleParam.getValue()) + ", ";
    }
    return result;
  }


  @Override
  public List<Pair<String, Double>> getScale() {
    return this.scale;
  }

  @Override
  public Point2D.Double getPosition() {
    return this.position.getValue();
  }

  @Override
  public String getPositionName() {
    return this.position.getKey();
  }


}
