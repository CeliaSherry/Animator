package cs5004.animator.model;


import java.util.List;
import animation.IAnimation;
import shape.IShape;
import transhape.ITransitionalShape;

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
   * Add an IShape to the hashMap that stores all shape and IDs, and store it with the given ID. If
   * the shapeID already existed in the hashMap, an exception will be thrown.
   *
   * @param aShape  given shape
   * @param shapeID given ID
   * @throws IllegalArgumentException if shape is null
   * @throws IllegalArgumentException if shapeID or shape already exists in the hashMap
   */
  void addRectangle(String name,
                    float lx, float ly,
                    float width, float height,
                    float red, float green, float blue,
                    int startOfLife, int endOfLife) throws IllegalArgumentException;


  /**
   * Add an IShape to the hashMap that stores all shape and IDs, and store it with the given ID. If
   * the shapeID already existed in the hashMap, an exception will be thrown.
   *
   * @param aShape  given shape
   * @param shapeID given ID
   * @throws IllegalArgumentException if shape is null
   * @throws IllegalArgumentException if shapeID or shape already exists in the hashMap
   */
  void addOval(String name,
               float cx, float cy,
               float xRadius, float yRadius,
               float red, float green, float blue,
               int startOfLife, int endOfLife) throws IllegalArgumentException;

  /**
   * Add the given ITransitionalShape to ArrayList transShapes according to the appear time of the
   * given ITransitionalShape. Insertion occurs at the position where all ITransitionalShapes in the
   * list appearing at the same time or before the given ITransitionalShape are also before it in
   * the list, and all ITransitionalShapes in the ist appearing after the given ITransitionalShape
   * are behind it in the list.
   *
   * @param shapeID       given ID of the shape to be assigned appear and disappear time
   * @param appearTIme    appearing time to be assigned
   * @param disappearTime disappearing time to be assigned
   * @throws IllegalArgumentException if the given shapeID doesn't exist
   * @throws IllegalArgumentException if shapeID has been assigned already
   * @throws IllegalArgumentException if appear time is not before disappear time
   * @throws IllegalArgumentException if appear time or disappear time is negative
   */
  //void addTransShape(String shapeID, int appearTIme, int disappearTime);

  void addMove(String name,
               float moveFromX, float moveFromY, float moveToX, float moveToY,
               int startTime, int endTime);

  void addChangeColor(String name,
                      float oldR, float oldG, float oldB, float newR, float newG,
                      float newB, int startTime, int endTime);

  void addScaleAnimation(String name, float fromSx, float fromSy, float toSx,
                         float toSy, int startTime, int endTime);

  /**
   * Add the given IAnimation to ArrayList animations according to the appear time of the given
   * IAnimation. Insertion occurs at the position where all IAnimations in the list appearing at the
   * same time or before the given IAnimation are also before it in the list, and all IAnimations in
   * the ist appearing after the given ITransitionalShape are behind it in the list. If there is a
   * time conflict between the given animation and the animations in the current list, an exception
   * will be thrown.
   *
   * @param animation given IAnimation
   * @throws IllegalArgumentException if given animation is null
   * @throws IllegalArgumentException if time conflict with other animations of the same type
   * @throws IllegalArgumentException if the shapeID of the animation doesn't exist
   * @throws IllegalArgumentException if start and end time are not within shape existing time
   */
  void addAnimation(IAnimation animation) throws IllegalArgumentException;

}
