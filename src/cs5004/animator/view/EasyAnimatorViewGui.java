package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import shape.IShape;

public class EasyAnimatorViewGui extends JFrame implements IEasyAnimatorView {

  MyDrawingPanel drawingPanel = new MyDrawingPanel();

  public EasyAnimatorViewGui() {
    setSize(new Dimension(800,800));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void setData(List<IShape> shapeData) {
    drawingPanel.updateShapes(shapeData);
    drawingPanel.repaint();
  }

  @Override
  public void render() throws IllegalArgumentException {

  }



}
