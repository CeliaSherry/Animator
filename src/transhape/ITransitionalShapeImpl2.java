package transhape;

public class ITransitionalShapeImpl2 extends ITransitionalShapeImpl implements ITransitionalShape2 {

public ITransitionalShapeImpl2(String shapeID, int appearTime, int disappearTime) {
  super(shapeID,appearTime,disappearTime);
}

  @Override
  public String toStringText(int speed) {
    String result = "";
    result += "Appears at t=" + (this.appearTime*1.0/speed) + "s\n"
            + "Disappears at t=" + (this.disappearTime*1.0)/speed + "s\n\n";
    return result;
  }


}
