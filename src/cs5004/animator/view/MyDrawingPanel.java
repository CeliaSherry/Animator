package cs5004.animator.view;

import java.awt.*;

import java.util.List;
import java.util.Map;

import javax.swing.*;

import shape.IShape;
import shape.ShapeType;

public class MyDrawingPanel extends JPanel {

  private List<IShape> shapes = null;

  public MyDrawingPanel() {
  }

  public void updateShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void paintComponent(Graphics g) {
    if(this.shapes != null){
    for (IShape shape : this.shapes) {

      g.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
      if (shape.getShapeType().equals(ShapeType.Rectangle)) {
        g.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                shape.getScale().get(0).getValue().intValue(),
                shape.getScale().get(1).getValue().intValue());
      } else if (shape.getShapeType().equals(ShapeType.Oval)) {
        g.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                shape.getScale().get(0).getValue().intValue(),
                shape.getScale().get(1).getValue().intValue());
      }
    }


    }
  }
}
