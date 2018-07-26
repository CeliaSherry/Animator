package animation;


import java.util.List;

import shape.IShape;
import transhape.ITransitionalShape;

/**
 * This interface represents the animation of an ITransitionalShape.
 */
public interface IAnimation {

  /**
   * Determine whether one animation starts before the other animation. Return true if this
   * animation starts first, otherwise false.
   *
   * @return true if this animation starts before the other animation, otherwise false
   * @other the other animation
   */
  boolean startBefore(IAnimation other);

  /**
   * Determine whether an animation is within the time range of an ITransitionalShape. Return true
   * if the animation doesn't start after or equal to the appear time of the shape or doesn't end
   * before or equal to the disappear time of the shape, otherwise false.
   *
   * @param appearTime    given appearing time of an ITransitionalShape
   * @param disappearTime given disappearing time of an ITransitionalShape
   * @return true if the animation is not within the valid time range of a shape, otherwise false
   */
  boolean illegalAnimationTime(int appearTime, int disappearTime)
          throws IllegalArgumentException;

  /**
   * Determine if this animation has a time conflict with any animation in the given list. A time
   * conflict is defined as two animation of the same type of the same shapeID having common time
   * intervals. For example, move from t=50 to t=70 and move from t=60 to t=80. However, if starting
   * time of one animation is equal to the end point of the other, the two animations are not
   * considered as having time conflict. If two animations are of different animation type, there
   * will be no time conflict even if the two time intervals overlapped.
   *
   * @param animations given list of animations
   * @return true if this a time conflict between this animation and the list, otherwise false.
   */
  boolean isTimeConflictWithList(List<IAnimation> animations);

  /**
   * Getter for startTime of this animation.
   *
   * @return start time of the animation
   */
  int getStartTime();

  /**
   * Getter for endTime of this animation.
   *
   * @return end time of the animation
   */
  int getEndTime();

  /**
   * Getter for the shape ID of this animation.
   *
   * @return ID of the shape where the animation is taking place
   */
  String getShapeID();


  /**
   * Return the ITransitionalShape in the list with the shapeId of this shape.  If not found,
   * null will be returned.
   *
   * @param transShapes given list of ITransitionalShapes.
   * @return ITransitionalShape in the list with the same shapeID. Return null if not found.
   */
  ITransitionalShape findCorrespondingShape(List<ITransitionalShape> transShapes);

  /**
   * Getter for type of this animation.
   *
   * @return tyoe of the animation
   */
  AnimType getType();

  /**
   * Determine if the time interval of this animation has any overlap with the other animation, not
   * including the start time and end time. Return true if there is an overlapped time interval
   * between the two animation objects, otherwise false.
   *
   * @param other the other animation
   * @return true if there is an common time interval between the two animations, otherwise false
   */
  boolean isTimeIntervalOverlap(IAnimation other);

  /**
   * Return the change of the shape due to a certain animation in string form.
   *
   * @return the change of the shape due to a certain animation in string form
   */
  String toStringAnimationChange();

  /**
   * Takes a speed and returns the text representation of the animation in String form with
   * the start and end times in seconds.
   *
   * @param speed given speed.
   * @return text representation of the animation.
   */
  String toStringText(int speed);

  /**
   * Takes a speed and IShape and returns the svg representation of the animation in String form.
   *
   * @param speed given speed.
   * @param shape given IShape.
   * @return String with svg representation of animation.
   */
  String toStringSvg(int speed, IShape shape);


}
