package animation;

import java.awt.geom.Point2D;

import shape.IShape2;
import shape.ShapeType;


/**
 * This class represents a moving animation from one place to another at a given rate by extending
 * the AAnimation class. Besides the general information stored in AAnimation class, it also stores
 * the position information before and after the animation.
 */
public class MoveAnimation extends AAnimation2 {

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


  @Override
  public String toStringSvg(int speed, IShape2 shape) {
    String result = "";
    result += "<animate attributeType = \"xml\" begin=\""
            + this.getStartTime() * 1000.0 / speed + "ms\" dur=\""
            + this.timeInterval * 1000.0 / speed + "ms\" "
            + this.toStringSvgMoveChange(shape)
            + " fill=\"remove\" />";

    return result;
  }




  private String toStringSvgMoveChange(IShape2 shape){
    String result = "";
    if(shape.getShapeType() == ShapeType.Rectangle) {
      result += "attributeName=\"x\" from=\""
              + this.startPosition.getX()
              + "\" to=\""
              + this.endPosition.getX() + "\""
              + "attributeName=\"y\" from=\""
              + this.startPosition.getY()
              + "\" to=\""
              + this.endPosition.getY() + "\"";
    } else if(shape.getShapeType() == ShapeType.Oval) {
      result += "attributeName=\"cx\" from=\""
              + this.startPosition.getX()
              + "\" to=\""
              + this.endPosition.getX() + "\""
              + "attributeName=\"cy\" from=\""
              + this.startPosition.getY()
              + "\" to=\""
              + this.endPosition.getY() + "\"";
    }
    return result;
  }




}
