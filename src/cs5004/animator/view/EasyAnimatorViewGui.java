package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import shape.IShape;

/**
 * This class represents an EasyAnimatorViewGUI.  This view renders the data from an
 * IEasyAnimatorModel and create a GUI animation based on it.
 */
public class EasyAnimatorViewGui extends JFrame implements IEasyAnimatorView {
  private static int width = 800;
  private static int height = 800;

  private final MyDrawingPanel drawingPanel = new MyDrawingPanel();
  private boolean isStart = false;
  private boolean isPause = false;
  private boolean isRestart = false;
  private boolean isIncrease = false;
  private boolean isDecrease = false;
  private boolean isSave = false;

  /**
   * This class represents a ToolBarListener that implements the ISubscriber interface. It
   * implements the method notify(String payload). When a payload is given to the method, a switch
   * statement determines if booleans should be set to true or false.  When the controller checks on
   * these values, if any are true it causes the controller to implement an action (save, pause,
   * restart, increase speed, decrease speed, etc.).
   */
  class ToolBarListener implements ISubscriber {
    @Override
    public void notify(String payload) {
      switch (payload) {
        case "START":
          setStart(true);
          break;
        case "PAUSE":
          setPause(true);
          break;
        case "RESTART":
          setRestart(true);
          break;
        case "INCREASESPEED":
          setIncrease(true);
          break;
        case "DECREASESPEED":
          setDecrease(true);
          break;
        case "SAVE":
          setSave(true);
          break;
      }
    }
  }

  /**
   * Constructor for an  EasyAnimatorViewGui object. It initializes the base setup of the JFrame
   * such as layout and dimension, and adds a toolbar and a scroll panel to the frame.
   */
  public EasyAnimatorViewGui() {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(width, height));

    JScrollPane jScrollPane = new JScrollPane(drawingPanel);

    ToolBar toolBar = new ToolBar(drawingPanel);
    toolBar.addSubscriber(new ToolBarListener());

    add(toolBar, BorderLayout.NORTH);
    add(jScrollPane);
    setVisible(true);
  }


  /**
   * Determine the set value of the save button.
   *
   * @return true if the save is set to true, otherwise false
   */
  public boolean save() {
    return this.isSave;
  }

  /**
   * Set value of the save button to be true or false.
   */
  public void setSave(boolean set) {
    this.isSave = set;
  }

  /**
   * Determine the set value of the increase button.
   *
   * @return true if the button is set to true, otherwise false
   */
  public boolean Increase() {
    return this.isIncrease;
  }

  /**
   * Set value of the increase button to be true or false.
   */
  public void setIncrease(boolean set) {
    this.isIncrease = set;
  }

  /**
   * Determine the set value of the decrease button.
   *
   * @return true if the button is set to true, otherwise false
   */
  public boolean Decrease() {
    return this.isDecrease;
  }

  /**
   * Set value of the decrease button to be true or false.
   */
  public void setDecrease(boolean set) {
    this.isDecrease = set;
  }

  /**
   * Determine the set value of the restart button.
   *
   * @return true if the button is set to true, otherwise false
   */
  public boolean Restart() {
    return this.isRestart;
  }

  /**
   * Set value of the restart button to be true or false.
   */
  public void setRestart(boolean set) {
    this.isRestart = set;
  }

  /**
   * Determine the set value of the pause button.
   *
   * @return true if the button is set to true, otherwise false
   */
  public boolean Pause() {
    return this.isPause;
  }

  /**
   * Set value of the pause button to be true or false.
   */
  public void setPause(boolean set) {
    this.isPause = set;
  }

  /**
   * Determine the set value of the start button.
   *
   * @return true if the button is set to true, otherwise false
   */
  public boolean Start() {
    return this.isStart;
  }

  /**
   * Set value of the start button to be true or false.
   */
  public void setStart(boolean set) {
    this.isStart = set;
  }


  /**
   * Set shapes to the drawing panel and paint them on the frame.
   *
   * @param shapeData shapes that are present at current time frame
   */
  public void setData(List<IShape> shapeData) {
    drawingPanel.updateShapes(shapeData);
    drawingPanel.repaint();
  }


  /**
   * A dummy render method for the view.
   *
   * @throws IllegalArgumentException exception will not be thrown here
   */
  @Override
  public void render() throws IllegalArgumentException {

  }


}
