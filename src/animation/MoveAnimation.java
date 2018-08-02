package animation;

import java.awt.geom.Point2D;

import javafx.util.Pair;
import shape.IShape;
import shape.ShapeType;


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


  /**
   * Takes a speed and IShape and returns the svg representation of the animation in String form.
   * This class includes the move information and calculates the proper beginning and end times.
   *
   * @param speed given speed.
   * @param shape given IShape.
   * @return String with svg representation of animation.
   */
  @Override
  public String toStringSvg(int speed, IShape shape) {
    String result = "";
    result += "<animate attributeType=\"xml\" begin=\""
            + this.getStartTime() * 1000.0 / speed + "ms\" dur=\""
            + this.timeInterval * 1000.0 / speed + "ms\" "
            + this.toStringSvgMoveXChange(shape)
            + " fill=\"freeze\"/>\n";

    result += "<animate attributeType=\"xml\" begin=\""
            + this.getStartTime() * 1000.0 / speed + "ms\" dur=\""
            + this.timeInterval * 1000.0 / speed + "ms\" "
            + this.toStringSvgMoveYChange(shape)
            + " fill=\"freeze\"/>";

    return result;
  }


  /**
   * Returns a string that represents the portion of an Svg file that contains information
   * about the change in X during the move.  If the shape is a rectangle, the attribute
   * and start and end positions are filled out accordingly.  If the shape is an oval, the
   * attribute name and start and end positions are filled out accordingly.
   *
   * @param shape given IShape.
   * @return String that represents the change in X during a move according to an Svg file.
   */
  private String toStringSvgMoveXChange(IShape shape) {
    String result = "";
    if (shape.getShapeType() == ShapeType.Rectangle) {
      result += "attributeName=\"x\" from=\""
              + this.startPosition.getX()
              + "\" to=\""
              + this.endPosition.getX() + "\"";

    } else if (shape.getShapeType() == ShapeType.Oval) {
      result += "attributeName=\"cx\" from=\""
              + this.startPosition.getX()
              + "\" to=\""
              + this.endPosition.getX() + "\"";

    }
    return result;
  }


  /**
   * Returns a string that represents the portion of an Svg file that contains information
   * about the change in Y during the move.  If the shape is a rectangle, the attribute
   * and start and end positions are filled out accordingly.  If the shape is an oval, the
   * attribute name and start and end positions are filled out accordingly.
   *
   * @param shape given IShape.
   * @return String that represents the change in Y during a move according to an Svg file.
   */
  private String toStringSvgMoveYChange(IShape shape) {
    String result = "";
    if (shape.getShapeType() == ShapeType.Rectangle) {
      result += "attributeName=\"y\" from=\""
              + this.startPosition.getY()
              + "\" to=\""
              + this.endPosition.getY() + "\"";
    } else if (shape.getShapeType() == ShapeType.Oval) {
      result += "attributeName=\"cy\" from=\""
              + this.startPosition.getY()
              + "\" to=\""
              + this.endPosition.getY() + "\"";
    }
    return result;
  }


  @Override
  public IAnimation getClone(){
    return new MoveAnimation(startTime,endTime,shapeID,
            new Point2D.Double(this.startPosition.getX(), this.startPosition.getY()),
            new Point2D.Double(this.endPosition.getX(), this.endPosition.getY()));
  }

  @Override
  public void updateAtTime(IShape shape, int time) {
    double startX = startPosition.getX();
    double startY = startPosition.getY();
    double endX = endPosition.getX();
    double endY = endPosition.getY();

    double currentX = formula(time,this.startTime,this.endTime,startX,endX);
    double currentY = formula(time,this.startTime,this.endTime,startY,endY);


    shape.setPosition(new Pair<>(shape.getPositionName(),
            new Point2D.Double(currentX,currentY)));
  }











}
