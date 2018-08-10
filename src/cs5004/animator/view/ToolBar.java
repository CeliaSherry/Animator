package cs5004.animator.view;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class represents a toolbar which extends JPanel. A ToolBar is created to implement the
 * buttons.  All of the buttons are added to the tool bar.  When a button is pressed, an action
 * command is passed to a tool bar listener.  The Tool Bar has a subscriber pattern.  A list of
 * subscribers is stored in the class and there is a method to implement additional subscribers.
 */
public class ToolBar extends JPanel {


  private List<ISubscriber> subscribers;


  /**
   * Add a subscriber to the list of subscribers of this object.
   *
   * @param subscriber given subscriber
   */
  void addSubscriber(ISubscriber subscriber) {
    subscribers.add(subscriber);
  }


  /**
   * Notify a list of subscribers when an action occurs based on the given payload.
   *
   * @param payload given payload
   */
  private void emitEvent(String payload) {
    for (ISubscriber subscriber : subscribers) {
      subscriber.notify(payload);
    }
  }


  /**
   * This class represents a ToolBarListener that implements the ActionListener interface. It
   * implements the method actionPerformed. When an action on any button occurs, the view will be
   * notified, as well as all subscribers.
   */
  class ToolBarListener implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "startButton":
          emitEvent("START");
          break;
        case "pauseButton":
          emitEvent("PAUSE");
          break;
        case "restartButton":
          emitEvent("RESTART");
          break;
        case "increaseSpeed":
          emitEvent("INCREASESPEED");
          break;
        case "decreaseSpeed":
          emitEvent("DECREASESPEED");
          break;
        case "save":
          emitEvent("SAVE");
          break;
        default://do nothing by default
          break;
      }
    }
  }

  /**
   * Constructor for a ToolBar object. The tool bar is to be painted on the given panel and includes
   * command buttons such as start, play, pause, restart, increase and decrease speed.
   *
   * @param panel given panel
   */
  public ToolBar(MyDrawingPanel panel) {
    //MyDrawingPanel panel;
    JButton start;
    JButton pause;
    JButton restart;
    JButton increaseSpeed;
    JButton decreaseSpeed;
    JButton save;

    //this.panel = panel;
    subscribers = new ArrayList<>();
    setLayout(new FlowLayout());

    ToolBarListener toolBarListener = new ToolBarListener();

    start = new JButton("Play");
    start.setActionCommand("startButton");
    start.addActionListener(toolBarListener);

    pause = new JButton("pause");
    pause.setActionCommand("pauseButton");
    pause.addActionListener(toolBarListener);

    restart = new JButton("restart");
    restart.setActionCommand("restartButton");
    restart.addActionListener(toolBarListener);

    increaseSpeed = new JButton("increase Speed");
    increaseSpeed.setActionCommand("increaseSpeed");
    increaseSpeed.addActionListener(toolBarListener);

    decreaseSpeed = new JButton("decrease Speed");
    decreaseSpeed.setActionCommand("decreaseSpeed");
    decreaseSpeed.addActionListener(toolBarListener);

    save = new JButton("Save");
    save.setActionCommand("save");
    save.addActionListener(toolBarListener);

    add(start);
    add(pause);
    add(restart);
    add(increaseSpeed);
    add(decreaseSpeed);
    add(save);
  }


}
