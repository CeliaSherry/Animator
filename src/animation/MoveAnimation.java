package animation;

import java.awt.geom.Point2D;


/**
 * This class represents a moving animation from one place to another at a given rate by extending
 * the AAnimation class. Besides the general information stored in AAnimation class, it also stores
 * the position information before and after the animation.
 */
public class MoveAnimation extends AAnimation {

  //to store the color before and after the animation
  private final Point2D.Double startPosition;
  private final Point2D.Double endPosition;


  /**
   * Construct a animation.MoveAnimation object with given parameters. The duration of the movement
   * cannot be 0.
   *
   * @param startTime     given startTime
   * @param endTime       given endTime
   * @param shapeID       ID of the given shape
   * @param startPosition given startPosition
   * @param endPosition   given end position
   * @throws IllegalArgumentException if startTime >= endTime
   */
  public MoveAnimation(int startTime, int endTime, String shapeID,
                       Point2D.Double startPosition, Point2D.Double endPosition)
          throws IllegalArgumentException {
    //throws exception if startTime >= endTime
    super(startTime, endTime, shapeID, AnimType.MOVE);
    this.startPosition = new Point2D.Double(startPosition.getX(), startPosition.getY());
    this.endPosition = new Point2D.Double(endPosition.getX(), endPosition.getY());
  }


  @Override
  public AnimType getType() {
    return this.type;
  }

  /**
   * Return the change of the shape due to this move animation in string form. For example: "moves
   * from (200.0,200.0) to (300.0,300.0)".
   *
   * @return the change of the shape due to this animation in string form
   */
  @Override
  public String toStringAnimationChange() {
    String result = "";
    result += "moves from ("
            + String.format("%.1f", this.startPosition.getX()) + ", "
            + String.format("%.1f", this.startPosition.getY())
            + ") to ("
            + String.format("%.1f", this.endPosition.getX()) + ", "
            + String.format("%.1f", this.endPosition.getY()) + ")";
    return result;
  }

  //below are just some thoughts on the potential usage of the class
  /**
   * Return the horizontal moving rate of a shape per time unit.
   *
   * @return the horizontal moving rate
   */
  /*
  private double horizontalRate() {
    return (endPosition.getX() - this.startPosition.getX())
            / this.timeInterval;
  }
  */

  /**
   * Return the vertical moving rate of a shape per time unit.
   *
   * @return the vertical moving rate
   */
  /*
  private double verticalRate() {
    return (endPosition.getY() - this.startPosition.getY())
            / this.timeInterval;
  }
  */

  /**
   * Return the the transitional shape at given time t, reflecting result of a move animation.
   *
   * @param time given time
   * @return the transitional shape
   */
  /*
  @Override
  public ITransitionalShape shapeAtT(int time) {
    this.transhape.getShape().setPosition(this.startPosition.getX()
                    + time * this.horizontalRate(),
            this.startPosition.getY()
                    + time * this.verticalRate());
    return this.transhape;
  }
  */


}
