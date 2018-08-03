package cs5004.animator;



import javax.swing.JOptionPane;
import javax.swing.JFrame;

import builder.AnimationFileReader;
import cs5004.animator.controller.IEasyAnimatorController;
import cs5004.animator.controller.IEasyAnimatorControllerImpl;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IEasyAnimatorModel;
import parser.IParser;
import parser.Parser;

/**
 * This class represents the Main.
 */
public class Main {

  /**
   * This method parses the command line input and creates the model and controller.
   * It then passes control to the controller along with the output file, input file,
   * view type, and speed.
   *
   * @param args given input array.
   */
  public static void main(String []args) {

    IEasyAnimatorModel model;
    String[] argsInOrder = null;

    IParser p = new Parser();
    try {
      argsInOrder = p.changeOrder(args);
    } catch (IllegalArgumentException e) {

      JOptionPane.showMessageDialog(new JFrame(), "Invalid command line input!");
    }

    try {
      //System.out.println("Filename is: " + argsInOrder[0] + "\n");
      model = new AnimationFileReader().readFile(argsInOrder[0],
              new EasyAnimatorModelImpl.TweenModelBuilderImpl<>());
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    IEasyAnimatorController controller = new IEasyAnimatorControllerImpl();

    //view,output file, speed
    controller.start(model,argsInOrder[1],argsInOrder[2],argsInOrder[3]);
  }
}
