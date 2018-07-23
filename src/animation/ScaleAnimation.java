package animation;

/**
 * This class represents a scaling animation form the original size to a bigger or smaller one at
 * certain rate by extending the AAnimation class. Besides the general information stored in
 * AAnimation class, it also stores the scale information before and after the animation.
 */
public class ScaleAnimation extends AAnimation {

  //to store the scale before and after the animation
  private final double fromSx;
  private final double fromSy;
  private final double toSx;
  private final double toSy;

  /**
   * Construct a ScaleAnimation object that scales the shape from the start time to the end time,
   * from the given start width and height to the given end width and height . Any of the scale
   * parameters cannot be negative. The duration of scaling cannot be 0.
   *
   * @param startTime given start time
   * @param endTime   given end time
   * @param shapeID   given shape ID
   * @param fromSx    given start width
   * @param fromSy    given start height
   * @param toSx      given end width
   * @param toSy      given end height
   * @throws IllegalArgumentException if startTime >= endTime
   * @throws IllegalArgumentException if and of the scale parameter is negative
   */
  public ScaleAnimation(int startTime, int endTime, String shapeID, double fromSx, double fromSy,
                        double toSx, double toSy)
          throws IllegalArgumentException {
    //throws exception if startTime >= endTime
    super(startTime, endTime, shapeID, AnimType.SCALE);
    if (fromSx < 0 || fromSy < 0 || toSx < 0 || toSy < 0) {
      throw new IllegalArgumentException("scale parameter cannot be negative!");
    }

    this.fromSx = fromSx;
    this.fromSy = fromSy;
    this.toSx = toSx;
    this.toSy = toSy;
  }


  @Override
  public AnimType getType() {
    return this.type;
  }


  /**
   * Return the change of the shape due to this scaling animation in string form. For example:
   * "scales from Width: 50.0, Height: 200.0 to Width: 25.0, Height:100.0".
   *
   * @return the change of the shape due to this animation in string form
   */
  @Override
  public String toStringAnimationChange() {
    String result = "scales from Width: " + String.format("%.1f", this.fromSx)
            + ", Height: " + String.format("%.1f", this.fromSy) + " to "
            + "Width: " + String.format("%.1f", this.toSx)
            + ", Height: " + String.format("%.1f", this.toSy);
    return result;
  }


}
