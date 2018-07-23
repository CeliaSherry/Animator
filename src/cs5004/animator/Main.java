package cs5004.animator;

import java.io.StringWriter;
import java.io.Writer;

import builder.AnimationFileReader;
import builder.TweenModelBuilder;
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

    IParser p = new Parser();
    String []argsInOrder = p.changeOrder(Args);

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
