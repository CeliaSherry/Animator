package animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

/**
 * This class represents a scaling animation form the original size to a bigger or smaller one at
 * certain rate by extending the AAnimation class. Besides the general information stored in
 * AAnimation class, it also stores the scale information before and after the animation.
 */
public class ScaleAnimation extends AAnimation {

  //to store the color before and after the animation
  double fromSx;
  double fromSy;
  double toSx;
  double toSy;


  //private final HashMap<String, Double> scaleRatio;
  //private final List<Pair<String, Double>> startScale;


  /**
   * Construct a animation.ScaleAnimation object that scales the shape from the start time to the
   * end time. The duration of scaling cannot be 0.
   *
   * @param startTime  given start time
   * @param endTime    given end time
   * @param shapeID    given shape ID
   * @param scaleRatio given scale ratio to be effectuate to each field in scale
   * @param startScale size information of the shape before the animation
   * @throws IllegalArgumentException if startTime >= endTime
   */
  public ScaleAnimation(int startTime, int endTime, String shapeID, double fromSx, double fromSy,
                        double toSx, double toSy)
          throws IllegalArgumentException {

    //throws exception if startTime >= endTime
    super(startTime, endTime, shapeID, AnimType.SCALE);
    this.fromSx = fromSx;
    this.fromSy = fromSy;
    this.toSx = toSx;
    this.toSy = toSy;
  }

  /**
   * Return the endScale due to the scaling animation. Each scale parameter is calculated based on:
   * startScale * scaleRatio.
   *
   * @return the endScale due to the scaling animation.
   */
  /*private List<Pair<String, Double>> calculateEndScale() {
    List<Pair<String, Double>> endScale;
    endScale = new ArrayList<>();

    for (Pair<String, Double> scale : startScale) {
      endScale.add(new Pair<>(scale.getKey(), scale.getValue() * scaleRatio.get(scale.getKey())));
    }
    return endScale;
  }*/
  @Override
  public AnimType getType() {
    return this.type;
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


}
