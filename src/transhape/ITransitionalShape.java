package transhape;


/**
 * This interface represents a shape of a certain ID in transition phases, such as appearing and
 * disappearing.
 */
public interface ITransitionalShape {
  /**
   * Return the appearing time of the shape following Svg format.
   *
   * @return the appearing time of the shape following Svg format
   */
  String toStringSvgAppear(int speed);

  /**
   * Return the disappearing time of the shape following Svg format.
   *
   * @return the disappearing time of the shape following Svg format
   */
  String toStringSvgDisappear(int speed);

  /**
   * Getter for the shapeID field.
   *
   * @return the shapeID of this ITransitionalShape
   */
  String getShapeID();

  /**
   * Getter for the appearing time of a shape.
   *
   * @return the appearing time of this ITransitionalShape
   */
  int getAppearTime();

  /**
   * Getter for the appearing time of a shape.
   *
   * @return the appearing time of this ITransitionalShape
   */
  int getDisappearTime();

  /**
   * Compare which shape appears first. Return true if this shape appears before the other shape,
   * otherwise false.
   *
   * @param other other ITransitionalShape
   * @return true if this shape appears before the other shape, otherwise false
   */
  boolean appearBefore(ITransitionalShape other);

  /**
   * Takes a speed and returns the text representation of the TransitionalShape in String form with
   * the appear and disappear times in seconds.
   *
   * @param speed given speed.
   * @return text representation of the TransitionalShape.
   */
  String toStringText(int speed);


  /**
   * Determines whether this transShape is present at the given time (frame tick). A shape is
   * between its appearing time and disappearing time(inclusive).
   *
   * @param time given time
   * @return true if this tranShape is present, otherwise false.
   */
  boolean isPresent(int time);


}
