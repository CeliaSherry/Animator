import java.io.StringWriter;
import java.io.Writer;

import builder.AnimationFileReader;
import builder.TweenModelBuilder;
import cs5004.animator.controller.IEasyAnimatorController;
import cs5004.animator.controller.IEasyAnimatorControllerImpl;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IEasyAnimatorModel;

public class Main {

  public static void main(String []Args) {

    //Writer ap = new StringWriter();
    IEasyAnimatorModel model;

    IParser p = new Parser();
    String []newArray = p.changeOrder(Args);

    try {
      model
              = new AnimationFileReader().readFile(newArray[0],
              new EasyAnimatorModelImpl.TweenModelBuilderImpl<>());
    } catch (Exception e) {
      throw new IllegalArgumentException("File Not Found");
    }
    IEasyAnimatorController controller = new IEasyAnimatorControllerImpl();
    //view,output file, speed
    controller.start(model,newArray[1],newArray[2],newArray[3]);
  }
}
