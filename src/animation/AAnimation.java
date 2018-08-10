package animation;


import java.util.List;

import shape.IShape;
import transhape.ITransitionalShape;

/**
 * This class represents a general animation happening at a certain period and at a certain rate by
 * implementing the IAnimation interface. It stores information such as start and end time of the
 * animation, and shapeID that the animation is applied to.
 */
public abstract class AAnimation implements IAnimation {
  protected final int startTime;
  protected final int endTime;
  protected final int timeInterval;
  protected final String shapeID;

  /* this field is used in the toString method, which requires adding the animation type
  information according to the specific type of animation.It is also used in determining whether
  there is a time conflict while adding a new animation, because time conflict only exists among
  animations of the same type. */
  protected final AnimType type;


  /**
   * Constructor for an animation.AAnimation object of a shape with given ID, using given start and
   * end time .
   *
   * @param startTime given start time
   * @param endTime   given end time
   * @param shapeID   given ID of a shape
   * @param type      given type of an animation
   * @throws IllegalArgumentException if startTime is greater than the endTime
   * @throws IllegalArgumentException if either time is negative
   */
  public AAnimation(int startTime, int endTime, String shapeID, AnimType type)
          throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Time has to be a non-negative integer!");
    }
    if (startTime >= endTime) {
      throw new IllegalArgumentException("StartTime has to be before the endTime!");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.timeInterval = endTime - startTime;
    this.shapeID = shapeID;
    this.type = type;
  }


  /**
   * Return the given IAnimation in String form, including its animation type, the change in the
   * shape due to the animation, the start and end time of the animation. For example: "shape R
   * moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50".
   *
   * @return the given IAnimation in string form
   */
  @Override
  public String toString() {
    String result = "\n";
    result += "shape " + this.getShapeID() + " " + this.toStringAnimationChange()
            + " from t="
            + String.format("%d", this.getStartTime())
            + " to t="
            + String.format("%d", this.getEndTime());
    return result;
  }


  /**
   * Determine whether an animation is within the time range of an ITransitionalShape. Return true
   * if the animation doesn't start after or equal to the appear time of the shape or doesn't end
   * before or equal to the disappear time of the shape, otherwise false.
   *
   * @param appearTime    given appearing time of an ITransitionalShape
   * @param disappearTime given disappearing time of an ITransitionalShape
   * @return true if the animation is not within the valid time range of a shape, otherwise false
   */
  @Override
  public boolean illegalAnimationTime(int appearTime, int disappearTime)
          throws IllegalArgumentException {
    return !(this.getStartTime() >= appearTime
            && this.getEndTime() <= disappearTime);

  }

  /**
   * Determine if this animation has a time conflict with another animation. A time conflict is
   * defined as two animation of the same type of the same shapeID having common time intervals. For
   * example, move from t=50 to t=70 and move from t=60 to t=80. However, if starting time of one
   * animation is equal to the end point of the other, the two animations are not considered as
   * having time conflict. If two animations are of different animation type, there will be no time
   * conflict even if the two time intervals overlapped.
   *
   * @param other the other animation
   * @return true if this a time conflict between the two animations, otherwise false.
   */
  private boolean isTimeConflict(IAnimation other) {
    if (other.getType() != this.getType() || (!other.getShapeID().equals(this.getShapeID()))) {
      return false;
    }
    return this.isTimeIntervalOverlap(other);
  }


  /**
   * Determine whether one animation starts before the other animation. Return true if this
   * animation starts first, otherwise false.
   *
   * @return true if this animation starts before the other animation, otherwise false
   * @other the other animation
   */
  @Override
  public boolean startBefore(IAnimation other) {
    return this.startTime < other.getStartTime();
  }

  /**
   * Return the ITransitionalShape in the list with the shapeId of this shape.  If not found, null
   * will be returned.
   *
   * @param transShapes given list of ITransitionalShapes.
   * @return ITransitionalShape in the list with the same shapeID. Return null if not found.
   */
  @Override
  public ITransitionalShape findCorrespondingShape(List<ITransitionalShape> transShapes) {
    if (transShapes == null || transShapes.size() == 0) {
      return null;
    }
    for (ITransitionalShape transShape : transShapes) {
      if (this.getShapeID().equals(transShape.getShapeID())) {
        return transShape;
      }
    }
    return null;
  }

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
  @Override
  public boolean isTimeConflictWithList(List<IAnimation> animations) {
    for (IAnimation animationInTheList : animations) {
      if (this.isTimeConflict(animationInTheList)) {
        return true;
      }

    }
    return false;
  }


  /**
   * Takes a speed and returns the text representation of the animation in String form with the
   * start and end times in seconds.
   *
   * @param speed given speed.
   * @return text representation of the animation.
   */
  @Override
  public String toStringText(int speed) {
    String result = "\n";
    result += "shape " + this.getShapeID() + " " + this.toStringAnimationChange()
            + " from t="
            + String.format("%.1f", (this.getStartTime() * 1.0) / speed)
            + "s to t="
            + String.format("%.1f", (this.getEndTime() * 1.0) / speed) + "s";
    return result;
  }

  /**
   * Takes a speed and IShape and returns the svg representation of the animation in String form.
   *
   * @param speed given speed.
   * @param shape given IShape.
   * @return String with svg representation of animation.
   */
  @Override
  public abstract String toStringSvg(int speed, IShape shape);

  /**
   * Getter for type of this animation.
   *
   * @return type of the animation
   */
  @Override
  public AnimType getType() {
    return this.type;
  }

  /**
   * Getter for startTime of this animation.
   *
   * @return start time of the animation
   */
  @Override
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Getter for endTime of this animation.
   *
   * @return end time of the animation
   */
  @Override
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Getter for the shape ID of this animation.
   *
   * @return ID of the shape where the animation is taking place
   */
  @Override
  public String getShapeID() {
    return shapeID;
  }

  /**
   * Determine if the time interval of this animation has any overlap with the other animation, not
   * including the start time and end time. Return true if there is an overlapped time interval
   * between the two animation objects, otherwise false.
   *
   * @param other the other animation
   * @return true if there is an common time interval between the two animations, otherwise false
   */
  @Override
  public boolean isTimeIntervalOverlap(IAnimation other) {
    return !(this.endTime <= other.getStartTime() || this.startTime >= other.getEndTime());
  }


  @Override
  public abstract void updateAtTime(IShape shape, int time);


  /**
   * Calculates the current value based on the given start and end times and start and end values.
   * If the given time is greater than the end time, the value at the end time will be returned.
   *
   * @param currentTime given time point
   * @param startTime   given start time
   * @param endTime     given end time
   * @param startValue  given start value
   * @param endValue    given end value
   * @return the value corresponding to the given time
   */
  @Override
  public int formula(int currentTime, int startTime, int endTime,
                     double startValue, double endValue) {
    if (currentTime > endTime) {
      return (int) endValue;
    }

    double leftValue = (endTime - currentTime) * startValue / (endTime - startTime);
    double rightValue = (currentTime - startTime) * endValue / (endTime - startTime);
    return (int) (leftValue + rightValue);
  }

  @Override
  public boolean isAnimationPresent(int time) {
    return time >= this.startTime;
  }

}
