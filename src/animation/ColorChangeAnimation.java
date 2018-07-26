package animation;

import java.awt.Color;

import shape.IShape;

/**
 * This class represents the color changing animation of a shape within a given period of time at a
 * certain rate by extending the AAnimation class. Besides the general information stored in
 * AAnimation class, it also stores the color information before and after the animation.
 */
public class ColorChangeAnimation extends AAnimation {

  //to store the color before and after the animation
  private final Color startColor;
  private final Color endColor;


  /**
   * Constructor for a ColorChangeAnimation object with given parameters.
   *
   * @param startTime given start time
   * @param endTime   given end time
   * @param shapeID   ID of the given shape
   * @param endColor  given endColor
   * @throws IllegalArgumentException if starTime >= endTime
   */
  public ColorChangeAnimation(int startTime, int endTime, String shapeID, Color startColor,
                              Color endColor) throws IllegalArgumentException {

    //throws exception if startTime >= endTime
    super(startTime, endTime, shapeID, AnimType.COLORCHANGE);

    this.startColor = copyColor(startColor);
    this.endColor = copyColor(endColor);

  }


  /**
   * Return a new Color object which is a copy of the input Color object in RGB 0.0 to 1.0 scale.
   *
   * @param color given Color object
   * @return a copy of the given Color object in 0.0 to 1.0 scale
   */
  public static Color copyColor(Color color) {
    //color in 0.0 to 1.0 scale inclusively
    float[] tempColor = new float[3];
    color.getColorComponents(tempColor);
    return new Color(tempColor[0], tempColor[1], tempColor[2]);
  }

  /**
   * Return the change of the shape due to this color change animation in string form. For example:
   * "changes color from (0.0,0.1,0.2) to (0.0,0.1,0.3)".
   *
   * @return the change of the shape due to this animation in string form
   */
  @Override
  public String toStringAnimationChange() {
    String result = "";
    result += "changes color from " + colorToString(this.startColor) + " to "
            + colorToString(this.endColor);
    return result;
  }


  /**
   * Return the given color in string form.
   *
   * @param color given color.
   * @return the color in string form.
   */
  private String colorToString(Color color) {
    String result = "(";
    float[] tempColor = new float[3];
    color.getColorComponents(tempColor);
    result += String.format("%.1f", tempColor[0]) + ","
            + String.format("%.1f", tempColor[1]) + ","
            + String.format("%.1f", tempColor[2]) + ")";
    return result;

  }

  /**
   * Takes a speed and IShape and returns the svg representation of the animation in String form.
   * This class includes the color information, including starting color and ending color in
   * the proper format.
   *
   * @param speed given speed.
   * @param shape given IShape.
   * @return String with svg representation of animation.
   */
  @Override
  public String toStringSvg(int speed, IShape shape) {
    String result = "";
    result += "<animateColor attributeName=\"fill\" attributeType=\"XML\" from=\""
            + "rgb("
            + this.startColor.getRed() + ","
            + this.startColor.getGreen() + ","
            + this.startColor.getBlue() + ")\""
            + " to=\""
            + "rgb("
            + this.endColor.getRed() + ","
            + this.endColor.getGreen() + ","
            + this.endColor.getBlue() + ")\""
            + " dur=\""
            + this.timeInterval * 1.0 / speed + "s\"/>";

    return result;
  }

}
