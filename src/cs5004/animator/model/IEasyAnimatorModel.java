package cs5004.animator.model;



/**
 * This interface represents an easy animator tool that creates and stores information of a series
 * of animations on different shapes by implementing the IEasyAnimatorModel interface. It supports
 * methods such as returning the description of the animations and the shapes in string form.
 */
public interface IEasyAnimatorModel {
  /**
   * Return all information stored in this model, including all shapes with appearing and
   * disappearing times, and all animations taking place. Shapes are shown in the order of their
   * appearing time, and animations are shown in the order of their staring time. If two shapes
   * appear at the same time or two animations start at the same time, they will shown according to
   * the time they were added to the list.
   */
  String toString();

  /**
   * Add a rectangle to model. If the shapeID already existed in the hashMap, an exception will be
   * thrown. Other illegal inputs such as negative entry of width or height, negative color
   * component, star of lift earlier or equal to end of life or either of the time is negative will
   * trigger the throwing of an exception.
   *
   * @param name        given shape ID
   * @param lx          given x-coordinate of the rectangle
   * @param ly          given y-coordinate of the rectangle
   * @param width       given width
   * @param height      given height
   * @param red         given red component of the color
   * @param green       given green component of the color
   * @param blue        given blue component of the color
   * @param startOfLife given appearing time of the rectangle
   * @param endOfLife   given disappearing time of the rectangle
   * @throws IllegalArgumentException if width or height is negative
   * @throws IllegalArgumentException if the name has already been assigned
   * @throws IllegalArgumentException if any color component is negative of greater than 1
   * @throws IllegalArgumentException if shapeID or shape already exists in the hashMap
   * @throws IllegalArgumentException if appearing time >= disappearing time
   * @throws IllegalArgumentException if appearing time < 0 or disappearing time < 0
   */
  void addRectangle(String name,
                    float lx, float ly,
                    float width, float height,
                    float red, float green, float blue,
                    int startOfLife, int endOfLife) throws IllegalArgumentException;


  /**
   * Add an Oval to model. If the shapeID already existed in the hashMap, an exception will be
   * thrown. Other illegal inputs such as negative entry of xRadius or yRadius, negative color
   * component, star of lift earlier or equal to end of life or either of the time is negative will
   * trigger the throwing of an exception.
   *
   * @param name        given shape ID
   * @param cx          given x-coordinate of the oval
   * @param cy          given y-coordinate of the oval
   * @param xRadius     given x Radius
   * @param yRadius     given y Radius
   * @param red         given red component of the color
   * @param green       given green component of the color
   * @param blue        given blue component of the color
   * @param startOfLife given appearing time of the rectangle
   * @param endOfLife   given disappearing time of the rectangle
   * @throws IllegalArgumentException if x Radius or y Radius is negative
   * @throws IllegalArgumentException if the name has been previously assigned to another shape
   * @throws IllegalArgumentException if any color component is negative of greater than 1
   * @throws IllegalArgumentException if shapeID or shape already exists in the hashMap
   * @throws IllegalArgumentException if appearing time >= disappearing time
   * @throws IllegalArgumentException if appearing time < 0 or disappearing time < 0
   */
  void addOval(String name,
               float cx, float cy,
               float xRadius, float yRadius,
               float red, float green, float blue,
               int startOfLife, int endOfLife) throws IllegalArgumentException;


  /**
   * Add an animation of type move to the animation ArrayList according to the appear time .
   * Insertion occurs at the position where all IAnimations in the list appearing at the same time
   * or before the given IAnimation are also before it in the list, and all IAnimations in the ist
   * appearing after the given ITransitionalShape are behind it in the list. If there is a time
   * conflict between the given animation and the animations in the current list, an exception will
   * be thrown. Animation cannot happen beyond the life of a shape.
   *
   * @param name      given shape ID
   * @param moveFromX given original x-coordinate of the the shape
   * @param moveFromY given original y-coordinate of the the shape
   * @param moveToX   given new x-coordinate of the the shape
   * @param moveToY   given new y-coordinate of the the shape
   * @param startTime given start time of the move animation
   * @param endTime   given end time of the move animation
   * @throws IllegalArgumentException if time conflict with other move animations
   * @throws IllegalArgumentException if the there is no shape with given name
   * @throws IllegalArgumentException if start and end time are not within shape existing time
   */

  void addMove(String name,
               float moveFromX, float moveFromY, float moveToX, float moveToY,
               int startTime, int endTime);


  /**
   * Add an animation of type color change to the animation ArrayList according to the appear time .
   * Insertion occurs at the position where all IAnimations in the list appearing at the same time
   * or before the given IAnimation are also before it in the list, and all IAnimations in the ist
   * appearing after the given ITransitionalShape are behind it in the list. If there is a time
   * conflict between the given animation and the animations in the current list, an exception will
   * be thrown. Animation cannot happen beyond the life of a shape.
   *
   * @param name      given shape ID
   * @param oldR      given original red component of the shape color
   * @param oldG      given original green component of the shape color
   * @param oldB      given original blue component of the shape color
   * @param newR      given new red component of the shape color
   * @param newG      given new green component of the shape color
   * @param newB      given new green component of the shape color
   * @param startTime given start time of the move animation
   * @param endTime   given end time of the move animation
   * @throws IllegalArgumentException if time conflict with other move animations
   * @throws IllegalArgumentException if the there is no shape with given name
   * @throws IllegalArgumentException if start and end time are not within shape existing time
   */
  void addChangeColor(String name,
                      float oldR, float oldG, float oldB, float newR, float newG,
                      float newB, int startTime, int endTime);


  /**
   * Add an animation of type scale to the animation ArrayList according to the appear time .
   * Insertion occurs at the position where all IAnimations in the list appearing at the same time
   * or before the given IAnimation are also before it in the list, and all IAnimations in the ist
   * appearing after the given ITransitionalShape are behind it in the list. If there is a time
   * conflict between the given animation and the animations in the current list, an exception will
   * be thrown. Animation cannot happen beyond the life of a shape.
   *
   * @param name      given shape ID
   * @param fromSx    given original width of the shape
   * @param fromSy    given original height of the shape
   * @param toSx      given new width of the shape
   * @param toSy      given new height of the shape
   * @param startTime given start time of the move animation
   * @param endTime   given end time of the move animation
   * @throws IllegalArgumentException if time conflict with other move animations
   * @throws IllegalArgumentException if the there is no shape with given name
   * @throws IllegalArgumentException if start and end time are not within shape existing time
   */
  void addScaleAnimation(String name, float fromSx, float fromSy, float toSx,
                         float toSy, int startTime, int endTime);


  /**
   * Takes a speed and returns the text representation of the model in String form with
   * the start and end times in seconds.
   *
   * @param speed given speed.
   * @return text representation of the model.
   */
  String toStringText(int speed);

  /**
   * Takes a speed and IShape and returns the svg representation of the model in String form.
   *
   * @param speed given speed.
   * @return String with svg representation of model.
   */
  String toStringSvg(int speed);

}
