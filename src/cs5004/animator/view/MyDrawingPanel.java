package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;


import shape.IShape;
import shape.ShapeType;

/**
 * This class represents MyDrawingPanel that extends JPanel. It takes charge of painting the shapes
 * in the model that are present at each frame tick. It also sets up the preferred size of the
 * drawing frame.
 */
public class MyDrawingPanel extends JPanel {
  private static int width = 2000;
  private static int height = 2000;
  private List<IShape> shapes = null;

  /**
   * Constructor of MyDrawingPanel. It sets up the preferred size of the drawing frame.
   */
  public MyDrawingPanel() {
    setPreferredSize(new Dimension(width, height));
  }

  /**
   * Update the field shapes with the given list of shapes, which corresponds to a copy of shapes
   * that are present in the model at each frame tick.
   *
   * @param shapes given list of shapes.
   */
  void updateShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Draw the list of shapes on the frame, including rectangles and ovals. It will draw them in the
   * order of the appearing time of the shape. If two shapes have the same appearing time, then they
   * will be drawn following the order of time being added to the model.
   *
   * @param g given graphics
   */
  @Override
  public void paintComponent(Graphics g) {
    if (this.shapes != null) {
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
