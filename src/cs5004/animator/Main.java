package cs5004.animator;

import javax.swing.*;

import builder.AnimationFileReader;
import cs5004.animator.controller.IEasyAnimatorController;
import cs5004.animator.controller.IEasyAnimatorControllerImpl;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IEasyAnimatorModel;
import parser.IParser;
import parser.Parser;

public class Main {

  public static void main(String []Args) {

    //Writer ap = new StringWriter();
    IEasyAnimatorModel model;
    String[] argsInOrder = null;

    IParser p = new Parser();
    try {
      argsInOrder = p.changeOrder(Args);
    } catch (IllegalArgumentException e) {

      JOptionPane.showMessageDialog(new JFrame(), "Invalid command line input!");
    }

    try {
      model = new AnimationFileReader().readFile(argsInOrder[0],
              new EasyAnimatorModelImpl.TweenModelBuilderImpl<>());
    } catch (Exception e) {
      throw new IllegalArgumentException("File Not Found");
    }
    IEasyAnimatorController controller = new IEasyAnimatorControllerImpl();

    //view,output file, speed
    controller.start(model,argsInOrder[1],argsInOrder[2],argsInOrder[3]);
  }
}
