package animation;

import shape.IShape2;
import shape.Rectangle;
import shape.ShapeType;

public abstract class AAnimation2 extends AAnimation implements IAnimation2 {

  public AAnimation2(int startTime, int endTime, String shapeID, AnimType type) {
    super(startTime, endTime, shapeID, type);
  }

  @Override
  public String toStringText(int speed) {
    String result = "\n";
    result += "shape " + this.getShapeID() + " " + this.toStringAnimationChange()
            + " from t="
            + String.format("%d", (this.getStartTime() * 1.0) / speed)
            + "s to t="
            + String.format("%d", (this.getEndTime() * 1.0) / speed) + "s";
    return result;
  }

  @Override
  public abstract String toStringSvg(int speed, IShape2 shape);








}
