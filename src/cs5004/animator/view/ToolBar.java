package cs5004.animator.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ToolBar extends JPanel{

  private MyDrawingPanel panel;
  private JButton start;
  private JButton pause;
  private JButton restart;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;
  private List<ISubscriber> subscribers;

  public void addSubscriber(ISubscriber subscriber) {
    subscribers.add(subscriber);
  }

  private void emitEvent(String payload) {
    for(ISubscriber subscriber : subscribers) {
      subscriber.notify(payload);
    }
  }

  class ToolBarListener implements ActionListener{
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      switch(e.getActionCommand()) {
        case "startButton":
          emitEvent("START");
          break;
        case "pauseButton":
          emitEvent("PAUSE");
          break;
        case "restartButton":
          emitEvent("RESTART");
          break;
      }
    }
  }

  public ToolBar(MyDrawingPanel panel) {
    this.panel = panel;
    subscribers = new ArrayList<ISubscriber>();
    setLayout(new FlowLayout());

    ToolBarListener toolBarListener = new ToolBarListener();

    start = new JButton("Play");
    start.setActionCommand("startButton");
    start.addActionListener(toolBarListener);

    pause = new JButton("Pause");
    pause.setActionCommand("pauseButton");
    pause.addActionListener(toolBarListener);

    restart = new JButton("Restart");
    restart.setActionCommand("restartButton");
    restart.addActionListener(toolBarListener);

    add(start);
    add(pause);
    add(restart);
  }



}
