package cs5004.animator.view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import shape.IShape;

public class EasyAnimatorViewGui extends JFrame implements IEasyAnimatorView {
  private static int width = 800;
  private static int height = 800;

  private final MyDrawingPanel drawingPanel = new MyDrawingPanel();

  public EasyAnimatorViewGui() {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(width,height));
    JScrollPane jScrollPane = new JScrollPane(drawingPanel);
    add(jScrollPane);
    setVisible(true);
  }

  public void setData(List<IShape> shapeData) {
    drawingPanel.updateShapes(shapeData);
    drawingPanel.repaint();
  }

  @Override
  public void render() throws IllegalArgumentException {
   /* JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(this.drawingPanel);
    frame.setSize(width,height);
    frame.setVisible(true);
    */

  }



}
