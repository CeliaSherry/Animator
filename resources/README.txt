IAnimation Interface:  This interface contains methods that are implemented by the animation.  An animation is the way a shape is changed between its appear time and disappear time.  The methods implemented check to see if an animation conflicts with another animation that already exists and returns a toString that represents the animation portion of both the text view and the svg view.  The toStringText and toStringSvg were added so the view could get the appropriate input.

AAnimation Class:  This is an abstract class that implements the IAnimation interface.  It takes in a shapeId and the start and end of an animation.  It also determines the time interval of an animation.  The toStringText and toStringSvg were added so the view could get the appropriate input.

AnimType Enum:  This is an enum class that represents the type of animation.

ColorChangeAnimation Class:  This is an animation- it contains before and after information for the color of an ITransitionalShape.  It has a toString and toStrings for a text and svg view.  The toStringText and toStringSvg were added so the view could get the appropriate input.

MoveAnimation Class:  This is an animation- it contains the before and after information for the position of an ITransShape.  It has a toString and toStrings for a text and svg view.  The toStringText and toStringSvg were added so the view could get the appropriate input.

ScaleAnimation Class:  This is an animation- it contains before and after information for the scale of an ITransShape.  It has a toString and toStrings for a text and svg view.  The toStringText and toStringSvg were added so the view could get the appropriate input.

AnimationFileReader Class:  This class reads in a file and uses a builder interface to build a model

TweenModelBuilder Class:  This builder contains all of the methods that AnimationFileReader needs and builds a model with the appropriate parameters, then returns that model.

IEasyAnimatorController Interface:  This controller runs the animation.  It contains a start method that begins the animation.

IEasyAnimatorControllerImpl Class:  This class implements IEasyAnimatorController and contains a method that begins the animation.  It takes in a model and a string that represents whether the output should be text or svg and calls the appropriate method from the model.  It then creates the appropriate view with an inputted output file and speed.  

IEasyAnimatorModel Interface: This interface contains all of the methods implemented by the model.  This allows a shape and animation to be added to the model.   This was changed to include toStringText and toStringSvg so give the appropriate outputs for the view.  To make constructing the model easier, methods addRectangle and addOval replaced addShape and addChangeColor, addMove, and addScaleAnimation replaced addAnimation.  These make it so the user is not required to know things about a shape when the model is created.

EasyAnimatorModelImpl Class: This class implements IEasyAnimatorModel.  It stores shapes and Ids in a hashmap and stores transShapes and animations in array lists.

IEasyAnimatorView Interface:  This interface represents all of the methods required of a view, which is just a render method.  This method should render the data from the model in the user's desired output.

EasyAnimatorViewImplFile Class:  This method takes in a string that will be outputted and an output file.  It creates the output file and writes the inputted string to the file in the appropriate format (either text or svg string is inputted).

EasyAnimatorViewImplOut Class:  This method takes in a string that will be outputted and prints to System.out.

Main:  This takes the command line input and calls the parser class to put the input in the appropriate order.  It then creates the model using the AnimationFileReaders and TweenModelBuilderImpl.  It then creates the controller and calls the controller's method start, which requires the model, the view, the output file, and the speed as inputs.

IParser Interface:  This interface contains the methods required of the Parser class.  

Parser Class:  The parser class contains a method, changeOrder, that takes a command line input that can be in any order and outputs it in a known order.  It also throws exceptions if commands are inputted incorrectly.

IShape Interface:  This interface represents a shape and includes basic methods that retrieve information about the shape.

AShape Class:  This abstract class represents a general shape of a certain color, type, position, and scale.  It also contains methods to get each individual color component which is used for toStringSvg view.

Oval Class:  Contains shape specific information for an oval.

Rectangle Class: Contains shape specific information for a rectangle.

ShapeType Enum:  Enum that represents a shape type.

ITransitionalShape Interface:  An interface that represents a shape in transition phases, such as appearing and disappearing.  The methods toStringSvgAppear, toStringSvgDisappear, and toStringText were added in order to render the appropriate view output.

ITransitionalShapeImpl Class:  This class implements the ITransitionalShape interface and and implements the view required toString methods.


