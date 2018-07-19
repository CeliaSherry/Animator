package animation;

import java.awt.Color;

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
  public ColorChangeAnimation(int startTime, int endTime, String shapeID, Color endColor,
                              Color startColor) throws IllegalArgumentException {

    //throws exception if startTime >= endTime
    super(startTime, endTime, shapeID, AnimType.COLORCHANGE);

    this.startColor = copyColor(startColor);
    this.endColor = copyColor(endColor);

  }

  @Override
  public AnimType getType() {
    return this.type;
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
   * @return the color in string form
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

  //below are just some thoughts on the potential usage of the class
  /**
   * Return the rate of color (scale 0-255) change a shape per time unit.
   *
   * @return rate of red color change
   */
  /* private double rRate() {
    return (endColor.getRed() - startColor.getRed()) / this.timeInterval;
  }
  */

  /**
   * Return the rate of color (scale 0-255) change in green per time unit.
   *
   * @return rate of green color change
   */
  /*
  private double gRate() {
    return (endColor.getGreen() - startColor.getGreen()) / this.timeInterval;
  }
  */

  /**
   * Return the rate of color (scale 0-255) change in blue per time unit.
   *
   * @return rate of blue color change
   */
  /*
  private double bRate() {
    return (endColor.getGreen() - startColor.getGreen()) / this.timeInterval;
  }
  */


  /**
   * Return the the transitional shape at given time t, reflecting result of a color change.
   * animation.
   *
   * @param time given time
   * @return the transitional shape
   * @throws IllegalArgumentException if time < 0
   */
  /* @Override
    public ITransitionalShape shapeAtT(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("Time must be a non-negative integer number!");
    }

    this.transhape.getShape().setColor
            (new Color((int) (this.startColor.getRed() + time * this.rRate()),
                    (int) (this.startColor.getGreen() + time * this.gRate()),
                    (int) (this.startColor.getBlue() + time * this.bRate())));
    return this.transhape;
  }
  */


}
