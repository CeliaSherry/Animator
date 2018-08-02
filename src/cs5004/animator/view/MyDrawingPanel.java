package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import shape.IShape;
import shape.ShapeType;

public class MyDrawingPanel extends JPanel {

  List<IShape> shapes;

  public MyDrawingPanel() {

  }

  public void updateShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void paintComponent(Graphics g) {
    for (IShape shape : shapes) {
      if (shape.getShapeType().equals(ShapeType.Rectangle)) {
        g.drawRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                shape.getScale().get(0).getValue().intValue(),
                shape.getScale().get(1).getValue().intValue());
      } else if (shape.getShapeType().equals(ShapeType.Oval)) {
        g.drawOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                shape.getScale().get(0).getValue().intValue(),
                shape.getScale().get(1).getValue().intValue());
      }


    }
  }
}
