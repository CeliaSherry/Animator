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
  boolean isStart = false;
  boolean isPause = false;
  boolean isRestart = false;


  class ToolBarListener implements ISubscriber{
    @Override
    public void notify(String payload) {
      switch(payload) {
        case "START":
          setStart(true);
          break;
        case "PAUSE":
          setPause(true);
          break;
        case "RESTART":
          setRestart(true);
          break;
      }
    }
  }

  public boolean Restart() {
    return this.isRestart;
  }

  public void setRestart(boolean set) {
    this.isRestart = set;
  }

  public boolean Pause() {
    return this.isPause;
  }

  public void setPause(boolean set) {
    this.isPause = set;
  }

  public boolean Start() {
    return this.isStart;
  }

  public void setStart(boolean set) {
    this.isStart = set;
  }

  public EasyAnimatorViewGui() {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(width,height));

    JScrollPane jScrollPane = new JScrollPane(drawingPanel);

    ToolBar toolBar = new ToolBar(drawingPanel);
    toolBar.addSubscriber(new ToolBarListener());

    add(toolBar,BorderLayout.NORTH);
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
