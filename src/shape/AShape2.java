package shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public abstract class AShape2 extends AShape implements IShape2 {
public AShape2(Pair<String, Point2D.Double> position, Color color, ShapeType type){
  super(position, color, type);

}


  @Override
  public int getRed() {
    return this.color.getRed();
  }

  @Override
  public int getGreen() {
    return this.color.getGreen();

  }
@Override
  public int getBlue() {
    return this.color.getBlue();

  }


}
