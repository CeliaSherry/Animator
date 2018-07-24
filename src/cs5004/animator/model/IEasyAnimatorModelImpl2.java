package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import animation.IAnimation;
import animation.IAnimation2;
import shape.IShape;
import shape.IShape2;
import shape.Rectangle;
import transhape.ITransitionalShape;
import transhape.ITransitionalShape2;

public class IEasyAnimatorModelImpl2 extends EasyAnimatorModelImpl implements IEasyAnimatorModel2 {

  //store shapes and corresponding IDs in a HashMap. Given an ID, returning the shape will only take
  // constant time.
  protected HashMap<String, IShape> shapes;

  //store transShapes in a list structure. Even though searching an ID takes linear time, this
  // structure can keep the order of the transShapes according to their appearing time.
  protected List<ITransitionalShape2> transShapes;

  //store animations in a list structure. Even though searching an ID takes linear time, this
  //structure can keep the order of the animations organized according to their start time.
  protected List<IAnimation2> animations;

  //keep records of the time that the last shape disappears
  protected int lastDisappearTime = 0;

  /**
   * Constructor for the EasyAnimatorModelImpl class. It initializes a new HashMap to store the
   * shape and ID combination, an ArrayList to store the appear and disappear time of the shapes,
   * and an ArrayList to store the animations.
   */
  public IEasyAnimatorModelImpl2() {
    this.shapes = new HashMap<>();
    this.transShapes = new ArrayList<>();
    this.animations = new ArrayList<>();
  }



  @Override
  public String toStringText(int speed) {


    String result = "";
    int lenShapes = transShapes.size();
    int lenAnimations = animations.size();
    if (lenShapes == 0) {
      return result;
    }
    result += "Shapes:\n";
    for (ITransitionalShape2 transShape : transShapes) {
      result += "Name: " + transShape.getShapeID() + "\n"
              + shapes.get(transShape.getShapeID()).toString()
              + transShape.toStringText(speed);
    }

    if (lenAnimations == 0) {
      return result;
    }

    String temp = "";
    for (IAnimation2 animation : animations) {
      temp += animation.toStringText(speed);
    }
    return result + temp.substring(1);
  }

  @Override
  public String toStringSvg(int speed) {
    String result = "<svg width=\"700\" height=\"500\" >\n";




  }

private static String toStringSvgShapes(IShape2 shape, String shapeID){
    String result = "";
    if(shape instanceof Rectangle){
      result += "<rect id=\"" + shapeID
              + "\" x= \"" + shape.getPosition().getX()
              + "\" y= \"" + shape.getPosition().getY() + "\""
              + "width=\"" + shape.getScale().get(0)
              + "\" height=\"" + shape.getScale().get(1)
              + "\" fill = \"rgb(" + shape.getRed() + "," + shape.getGreen() + ","
              + shape.getBlue() + ")\" visibility = \"visible\">";


    }




}
}
