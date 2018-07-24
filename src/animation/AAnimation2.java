package animation;

public abstract class AAnimation2 extends AAnimation implements IAnimation2 {

  public AAnimation2(int startTime, int endTime, String shapeID, AnimType type) {
    super(startTime, endTime, shapeID, type);
  }

  @Override
  public String toStringText(int speed) {
    String result = "\n";
    result += "shape " + this.getShapeID() + " " + this.toStringAnimationChange()
            + " from t="
            + String.format("%d", (this.getStartTime()*1.0)/speed)
            + "s to t="
            + String.format("%d", (this.getEndTime()*1.0)/speed) + "s";
    return result;
  }

  @Override
  public String toStringSvg(int speed) {
    String result = "";
    result += "<animate attributeType = \"xml\" begin=\""
            + this.getStartTime()*1000.0/speed + "\" dur"


    return null; //time*1000.0/speed ;
  }




}
