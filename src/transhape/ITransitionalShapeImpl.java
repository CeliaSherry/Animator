package transhape;


/**
 * This class represents a shape with appearing and disappearing time by implementing the
 * ITransitionalShape interface. It stores the related shape by shapeID, and defines appearing and
 * disappearing time of the shape.
 */
public class ITransitionalShapeImpl implements ITransitionalShape {
  // stores shapeID.
  protected String shapeID;
  // stores appearing and disappearing time of the shape
  protected int appearTime;
  protected int disappearTime;

  /**
   * Constructor of an ITransitionalShapeImpl object based one given shape and given appearing or
   * disappearing time.
   *
   * @param shapeID       given shapeID
   * @param appearTime    given appearTime
   * @param disappearTime given disappearTime
   * @throws IllegalArgumentException if appearTime is greater or equal to disappearTime
   */
  public ITransitionalShapeImpl(String shapeID, int appearTime, int disappearTime)
          throws IllegalArgumentException {
    if (appearTime < 0 || disappearTime < 0) {
      throw new IllegalArgumentException("Appearing or disappearing time cannot be negative!");
    }
    if (appearTime >= disappearTime) {
      throw new IllegalArgumentException("DisappearTime has to be after appearTime!");
    }
    this.shapeID = shapeID;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }


  /**
   * Return the given ITransitionalShape in String form, including its appear and disappear times.
   * For example: "Appears at t=1\nDisappears at t=100".
   *
   * @return the given ITransitionalShape in string form
   */
  @Override
  public String toString() {
    String result = "";
    result += "Appears at t=" + this.appearTime + "\n"
            + "Disappears at t=" + this.disappearTime + "\n\n";
    return result;
  }

  @Override
  public String getShapeID() {
    return this.shapeID;
  }


  @Override
  public int getAppearTime() {
    return appearTime;
  }

  @Override
  public int getDisappearTime() {
    return disappearTime;
  }

  @Override
  public boolean appearBefore(ITransitionalShape other) {
    return this.getAppearTime() < other.getAppearTime();
  }


}
