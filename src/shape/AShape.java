package shape;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

import javafx.util.Pair;


/**
 * This abstract class represents a general shape of a certain color, of a certain type, at a
 * certain position and of a certain scale, which are the common features shared by all shapes.
 */
public abstract class AShape implements IShape {
  protected Color color;

  // this field is used in the toString method, which requires adding the type information according
  // to the specific type of animation.
  protected ShapeType type;

  /* As different shapes may use different definition of positions, this pair structure stores the
   name defining the position and the corresponding coordinates. For example, a rectangular use
   its minimum corner as its position, whereas an oval use its center as its position.*/
  protected Pair<String, Point2D.Double> position;


  /* As different shapes may use different definition of scales, this list of  pair structure stores
   a list of the name defining a scale parameter and the corresponding coordinates. For example, a
   rectangular use width and height as its scale, whereas an oval use its X radius and Y radius as
   its scale. It is flexible and practical if we decide to introduce more shapes later */
  protected List<Pair<String, Double>> scale;


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

  /**
   * Return the scale of this shape which represents sized of the shape.
   *
   * @return the scale of this shape
   */
  @Override
  public List<Pair<String, Double>> getScale() {
    return this.scale;
  }

  /**
   * Return the x-y coordinates that describes the position of this shape.
   *
   * @return the x-y coordinates that describes the position of this shape.
   */
  @Override
  public Point2D.Double getPosition() {
    return this.position.getValue();
  }

  /**
   * Return the name of the parameter that represents the position of this shape. For example: "Min
   * Corner" or "Center".
   *
   * @return the x-y coordinates that describes the position of this shape.
   */
  @Override
  public String getPositionName() {
    return this.position.getKey();
  }

  /**
   * Getter for the red component of the shape's color.
   *
   * @return int that represents the red component of the shape's color.
   */
  @Override
  public int getRed() {
    return this.color.getRed();
  }

  /**
   * Getter for the green component of the shape's color.
   *
   * @return int that represents the green component of the shape's color.
   */
  @Override
  public int getGreen() {
    return this.color.getGreen();
  }

  /**
   * Getter for the blue component of the shape's color.
   *
   * @return int that represents the blue component of the shape's color.
   */
  @Override
  public int getBlue() {
    return this.color.getBlue();
  }

  /**
   * Getter for the ShapeType of this shape.
   *
   * @return ShapeType of this shape.
   */
  @Override
  public ShapeType getShapeType() {
    return this.type;
  }

  @Override
  public abstract IShape getClone();

  @Override
  public void setColor(int newRed, int newGreen, int newBlue) {
    this.color = new Color(newRed, newGreen, newBlue);
  }


  @Override
  public void setPosition(double newX, double newY) {
    this.position = new Pair<>(this.position.getKey(),
            new Point2D.Double(newX, newY));

  }

  @Override
  public void setScale(double newWidth, double newHeight) {
    List<Pair<String, Double>> newScale = new ArrayList<>();
    newScale.add(new Pair<>(this.scale.get(0).getKey(), newWidth));
    newScale.add(new Pair<>(this.scale.get(1).getKey(), newHeight));
    this.scale = newScale;
  }

}
