package transhape;


/**
 * This interface represents a shape of a certain ID in transition phases, such as appearing and
 * disappearing.
 */
public interface ITransitionalShape {


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


  String toStringText(int speed);





}
