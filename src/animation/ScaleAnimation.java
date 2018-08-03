package animation;

import shape.IShape;
import shape.ShapeType;

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


  /**
   * Takes a speed and IShape and returns the svg representation of the animation in String form.
   * This class includes the scale information, including starting and ending width and height
   * using the from (x,y) and the to (x,y).
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
            + this.toStringSvgScaleXChange(shape)
            + " fill=\"freeze\"/>\n";
    result += "<animate attributeType=\"xml\" begin=\""
            + this.getStartTime() * 1000.0 / speed + "ms\" dur=\""
            + this.timeInterval * 1000.0 / speed + "ms\" "
            + this.toStringSvgScaleYChange(shape)
            + " fill=\"freeze\"/>";

    return result;
  }



  /**
   * Returns a string that represents the portion of an Svg file that contains information
   * about the change in X during the scale change.  If the shape is a rectangle, the attribute
   * and start and end positions are filled out accordingly.  If the shape is an oval, the
   * attribute name and start and end positions are filled out accordingly.
   *
   * @param shape given IShape.
   * @return String that represents the change in X during a move according to an Svg file.
   */
  private String toStringSvgScaleXChange(IShape shape) {
    String result = "";
    if (shape.getShapeType() == ShapeType.Rectangle) {
      result += "attributeName=\"width\" from=\""
              + this.fromSx
              + "\""
              + " to=\""
              + this.toSx
              + "\""
              + " repeatCount=\"0\"";

    } else if (shape.getShapeType() == ShapeType.Oval) {
      result += "attributeName=\"rx\" from=\""
              + this.fromSx
              + "\""
              + " to=\""
              + this.toSx
              + "\""
              + " repeatCount=\"0\"";

    }
    return result;
  }


  /**
   * Returns a string that represents the portion of an Svg file that contains information
   * about the change in Y during the scale change.  If the shape is a rectangle, the attribute
   * and start and end positions are filled out accordingly.  If the shape is an oval, the
   * attribute name and start and end positions are filled out accordingly.
   *
   * @param shape given IShape.
   * @return String that represents the change in Y during a move according to an Svg file.
   */
  private String toStringSvgScaleYChange(IShape shape) {
    String result = "";
    if (shape.getShapeType() == ShapeType.Rectangle) {
      result += "attributeName=\"height\" from=\""
              + this.fromSy
              + "\""
              + " to=\""
              + this.toSy
              + "\""
              + " repeatCount=\"0\"";
    } else if (shape.getShapeType() == ShapeType.Oval) {
      result += "attributeName=\"cy\" from=\""
              + this.fromSy
              + "\""
              + " to=\""
              + this.toSy
              + "\""
              + " dur=\""
              + " repeatCount=\"0\"";
    }
    return result;
  }

/*
  @Override
  public IAnimation getClone(){
    return new ScaleAnimation(startTime,endTime,shapeID,
            this.fromSx, fromSy, toSx, toSy);
  }
  */


  @Override
  public void updateAtTime(IShape shape, int time) {
    int currentSx = formula(time,this.startTime,this.endTime,this.fromSx,this.toSx);
    int currentSy = formula(time,this.startTime,this.endTime,this.fromSy,this.toSy);

    shape.setScale(currentSx,currentSy);
  }

}
