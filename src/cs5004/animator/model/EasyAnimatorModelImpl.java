package cs5004.animator.model;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import animation.ColorChangeAnimation;
import animation.IAnimation;
import animation.MoveAnimation;
import animation.ScaleAnimation;
import builder.TweenModelBuilder;
import shape.IShape;
import shape.Oval;
import shape.Rectangle;
import transhape.ITransitionalShape;
import transhape.ITransitionalShapeImpl;

/**
 * This class represents an easy animator tool that creates and stores information of a series of
 * animations on different shapes by implementing the IEasyAnimatorModel interface. It supports
 * returning the description of the animations and the shapes in string form.
 */
public final class EasyAnimatorModelImpl implements IEasyAnimatorModel {

  //store shapes and corresponding IDs in a HashMap. Given an ID, returning the shape will only take
  // constant time.
  private final HashMap<String, IShape> shapes;

  //store transShapes in a list structure. Even though searching an ID takes linear time, this
  // structure can keep the order of the transShapes according to their appearing time.
  private final List<ITransitionalShape> transShapes;

  //store animations in a list structure. Even though searching an ID takes linear time, this
  //structure can keep the order of the animations organized according to their start time.
  private final List<IAnimation> animations;

  //keep records of the time that the last shape disappears
  private int lastDisappearTime = 0;

  /**
   * Constructor for the EasyAnimatorModelImpl class. It initializes a new HashMap to store the
   * shape and ID combination, an ArrayList to store the appear and disappear time of the shapes,
   * and an ArrayList to store the animations.
   */
  public EasyAnimatorModelImpl() {
    this.shapes = new HashMap<>();
    this.transShapes = new ArrayList<>();
    this.animations = new ArrayList<>();
  }

  /**
   * Add an IShape to the hashMap that stores all shape and IDs, and store it with the given ID. If
   * the shapeID already existed in the hashMap, an exception will be thrown.
   *
   * @param aShape  given shape
   * @param shapeID given ID
   * @throws IllegalArgumentException if shape is null
   * @throws IllegalArgumentException if shapeID or shape already exists in the hashMap
   */

 private void addShape(IShape aShape, String shapeID) throws IllegalArgumentException {
    if (aShape == null) {
      throw new IllegalArgumentException("shape is null!");
    }
    if (shapes.containsKey(shapeID) || (shapes.containsValue(aShape))) {
      throw new IllegalArgumentException("ID or this shape exists!");
    }
    this.shapes.put(shapeID, aShape);
  }


  /**
   * Add a rectangle to the collection of ITransitional shape
   * @param name
   * @param lx
   * @param ly
   * @param width
   * @param height
   * @param red
   * @param green
   * @param blue
   * @param startOfLife
   * @param endOfLife
   */
  public void addRectangle(String name,
                           float lx, float ly,
                           float width, float height,
                           float red, float green, float blue,
                           int startOfLife, int endOfLife)
  throws IllegalArgumentException{
    IShape rectangle = new Rectangle(new Point2D.Double(lx, ly),  new Color(red, green, blue),
            width, height);
    this.addShape(rectangle, name);
    this.addTransShape(name, startOfLife, endOfLife);
  }





  public void addOval(String name,
                      float cx, float cy,
                      float xRadius, float yRadius,
                      float red, float green, float blue,
                      int startOfLife, int endOfLife)
  throws IllegalArgumentException {
    IShape oval = new Oval(new Point2D.Double(cx, cy),  new Color(red, green, blue),
            xRadius, yRadius);
    this.addShape(oval, name);
    this.addTransShape(name, startOfLife, endOfLife);
  }








  /**
   * Add the given ITransitionalShape to ArrayList transShapes according to the appear time of the
   * given ITransitionalShape. Insertion occurs at the position where all ITransitionalShapes in the
   * list appearing at the same time or before the given ITransitionalShape are also before it in
   * the list, and all ITransitionalShapes in the ist appearing after the given ITransitionalShape
   * are behind it in the list.
   *
   * @param shapeID       given ID of the shape to be assigned appear and disappear time
   * @param appearTIme    appearing time to be assigned
   * @param disappearTime disappearing time to be assigned
   * @throws IllegalArgumentException if the given shapeID doesn't exist
   * @throws IllegalArgumentException if shapeID has been assigned already
   * @throws IllegalArgumentException if appear time is not before disappear time
   * @throws IllegalArgumentException if appear time or disappear time is negative
   */

  private void addTransShape(String shapeID, int appearTIme, int disappearTime)
          throws IllegalArgumentException {
    if (!shapes.containsKey(shapeID)) {
      throw new IllegalArgumentException("There is no shape under the given ID! "
              + "Can't add transShape\n");
    }

    if (this.containsID(shapeID)) {
      throw new IllegalArgumentException("There is already a ITransShape object using this ID! "
              + "Can't add transShape\n");
    }

    ITransitionalShape transShape = new ITransitionalShapeImpl(shapeID, appearTIme, disappearTime);

    int len = this.transShapes.size();
    int i = 0;
    while (i < len && (!transShape.appearBefore(transShapes.get(i)))) {
      i++;
    }
    transShapes.add(i, transShape);

    //update lastDisappearTime
    if (transShape.getDisappearTime() > this.lastDisappearTime) {
      this.lastDisappearTime = transShape.getDisappearTime();
    }
  }


  /**
   * Return whether this.transShapes contains an ITransitionalShape of the given shapeID.
   *
   * @param shapeID given shapeID
   * @return true if this shapeID is contained in the list, otherwise false
   */
  private boolean containsID(String shapeID) {
    if (this.transShapes.size() == 0) {
      return false;
    }
    for (ITransitionalShape transShape : transShapes) {
      if (shapeID.equals(transShape.getShapeID())) {
        return true;
      }
    }
    return false;
  }



  @Override
  public void addMove(String name,
                      float moveFromX, float moveFromY, float moveToX, float moveToY,
                      int startTime, int endTime) {
    IAnimation move = new MoveAnimation(startTime,endTime,name,
            new Point2D.Double(moveFromX,moveFromY), new Point2D.Double(moveToX,moveToY));

    addAnimation(move);
  }

  @Override
  public void addChangeColor(String name,
                             float oldR, float oldG, float oldB, float newR, float newG,
                             float newB, int startTime, int endTime) {
    IAnimation changeColor = new ColorChangeAnimation(startTime,endTime,
            name,new Color(oldR,oldG,oldB), new Color(newR,newG,newB));
    addAnimation(changeColor);
  }

  @Override
  public void addScaleAnimation(String name, float fromSx, float fromSy, float toSx,
                                float toSy, int startTime, int endTime) {

    IAnimation scale = new ScaleAnimation(startTime,endTime,name,fromSx,fromSy,toSx,toSy);
    addAnimation(scale);
  }




  /**
   * Add the given IAnimation to ArrayList animations according to the appear time of the given
   * IAnimation. Insertion occurs at the position where all IAnimations in the list appearing at the
   * same time or before the given IAnimation are also before it in the list, and all IAnimations in
   * the ist appearing after the given ITransitionalShape are behind it in the list. If there is a
   * time conflict between the given animation and the animations in the current list, an exception
   * will be thrown.
   *
   * @param animation given IAnimation
   * @throws IllegalArgumentException if given animation is null
   * @throws IllegalArgumentException if time conflict with other animations of the same type
   * @throws IllegalArgumentException if the shapeID of the animation doesn't exist
   * @throws IllegalArgumentException if start and end time are not within shape existing time
   */
  @Override
  public void addAnimation(IAnimation animation) throws IllegalArgumentException {
    if (animation == null) {
      throw new IllegalArgumentException("animation to be added is null!");
    }

    //find the corresponding transShape in transShapes stored in the model
    ITransitionalShape correspondTransShape = animation.findCorrespondingShape(this.transShapes);
    if (correspondTransShape == null) {
      throw new IllegalArgumentException("There is no shape under the given ID!"
              + "Can't add animation\n");
    }
    if (animation.illegalAnimationTime(correspondTransShape.getAppearTime(),
            correspondTransShape.getDisappearTime())) {
      throw new IllegalArgumentException("The animation has to take place between the "
              + "appear and disappear time of the shape!");
    }

    int len = animations.size();
    //length 0, the animation can be added directly.
    if (len == 0) {
      animations.add(animation);
      return;
    }
    if (animation.isTimeConflictWithList(animations)) {
      // check if there is time conflict with other animations first
      throw new IllegalArgumentException("There is a time conflict with other animations!"
              + " Cannot add animation!");
    } else {
      int i = 0;
      while (i < len && (!animation.startBefore(animations.get(i)))) {
        i++;
      }
      animations.add(i, animation);
    }
  }


  @Override
  public String toString() {
    String result = "";
    int lenShapes = transShapes.size();
    int lenAnimations = animations.size();
    if (lenShapes == 0) {
      return result;
    }
    result += "Shapes:\n";
    for (ITransitionalShape transShape : transShapes) {
      result += "Name: " + transShape.getShapeID() + "\n"
              + shapes.get(transShape.getShapeID()).toString()
              + transShape.toString();
    }

    if (lenAnimations == 0) {
      return result;
    }

    String temp = "";
    for (IAnimation animation : animations) {
      temp += animation.toString();
    }
    return result + temp.substring(1);
  }




  public static final class TweenModelBuilderImpl<T> implements TweenModelBuilder<T> {

    IEasyAnimatorModel model;


    public TweenModelBuilderImpl() {
      this.model = new EasyAnimatorModelImpl();
    }
    /**
     * Add a new oval to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param cx          the x-coordinate of the center of the oval
     * @param cy          the y-coordinate of the center of the oval
     * @param xRadius     the x-radius of the oval
     * @param yRadius     the y-radius of the oval
     * @param red         the red component of the color of the oval
     * @param green       the green component of the color of the oval
     * @param blue        the blue component of the color of the oval
     * @param startOfLife the time tick at which this oval appears
     * @param endOfLife   the time tick at which this oval disappears
     * @return the builder object
     */
    @Override
    public TweenModelBuilder<T> addOval(String name, float cx, float cy, float xRadius,
                                        float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {

      model.addOval(name,cx,cy,xRadius,yRadius,red,green,blue,startOfLife,endOfLife);
      return this;

    }

    /**
     * Add a new rectangle to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param lx          the minimum x-coordinate of a corner of the rectangle
     * @param ly          the minimum y-coordinate of a corner of the rectangle
     * @param width       the width of the rectangle
     * @param height      the height of the rectangle
     * @param red         the red component of the color of the rectangle
     * @param green       the green component of the color of the rectangle
     * @param blue        the blue component of the color of the rectangle
     * @param startOfLife the time tick at which this rectangle appears
     * @param endOfLife   the time tick at which this rectangle disappears
     * @return the builder object
     */
    @Override
    public TweenModelBuilder<T> addRectangle(String name, float lx, float ly, float width, float height, float red, float green, float blue, int startOfLife, int endOfLife) {
      model.addRectangle(name,lx,ly,width,height,red,green,blue,startOfLife,endOfLife);
      return this;
    }

    /**
     * Move the specified shape to the given position during the given time interval.
     *
     * @param name      the unique name of the shape to be moved
     * @param moveFromX the x-coordinate of the initial position of this shape. What this x-coordinate
     *                  represents depends on the shape.
     * @param moveFromY the y-coordinate of the initial position of this shape. what this y-coordinate
     *                  represents depends on the shape.
     * @param moveToX   the x-coordinate of the final position of this shape. What this x-coordinate
     *                  represents depends on the shape.
     * @param moveToY   the y-coordinate of the final position of this shape. what this y-coordinate
     *                  represents depends on the shape.
     * @param startTime the time tick at which this movement should start
     * @param endTime   the time tick at which this movement should end
     */
    @Override
    public TweenModelBuilder<T> addMove(String name, float moveFromX, float moveFromY,
                                        float moveToX, float moveToY, int startTime, int endTime) {
      model.addMove(name,moveFromX,moveFromY,moveToX,moveToY,startTime,endTime);

      return this;
    }

    /**
     * Change the color of the specified shape to the new specified color in the specified time
     * interval.
     *
     * @param name      the unique name of the shape whose color is to be changed
     * @param oldR      the r-component of the old color
     * @param oldG      the g-component of the old color
     * @param oldB      the b-component of the old color
     * @param newR      the r-component of the new color
     * @param newG      the g-component of the new color
     * @param newB      the b-component of the new color
     * @param startTime the time tick at which this color change should start
     * @param endTime   the time tick at which this color change should end
     */
    @Override
    public TweenModelBuilder<T> addColorChange(String name, float oldR, float oldG, float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      model.addChangeColor(name,oldR,oldG,oldB,newR,newG,newB,startTime,endTime);
      return this;
    }

    /**
     * Change the x and y extents of this shape from the specified extents to the specified target
     * extents. What these extents actually mean depends on the shape, but these are roughly the
     * extents of the box enclosing the shape
     */
    @Override
    public TweenModelBuilder<T> addScaleToChange(String name, float fromSx, float fromSy, float toSx, float toSy, int startTime, int endTime) {
      model.addScaleAnimation(name,fromSx,fromSy,toSx,toSy,startTime,endTime);
      return this;
    }

    /**
     * Return the model built so far.
     *
     * @return the model that was constructed so far
     */
    @Override
    public T build() {
      return (T)model;
    }
  }



}


