

In our program, all of the features in the assignment work, as outlined below:

1.  When -iv visual is passed into the command line, a GUI opens and shows the animation.  
2.  The user can play, pause, resume, and restart the animation using the buttons.  When the program is opened, the user must press play to begin.
3.  The user can increase or decrease the speed of the animation by pressing the increase and decrease speed buttons.
4.  The user can save the animation as an SVG file by pressing the button and inputting the name of the new file.

More information about how the code is implemented is below:

In order to implement the GUI, we created a new view that implements the IView interface.  This GUI view (EasyAnimatorViewGui) extends JFrame and creates a MyDrawingPanel.  MyDrawingPanel extends JPanel and implements the painComponent(Graphics g) method.  MyDrawingPanel takes a list of IShapes and checks their type, drawing either a rectangle or an oval.

A ToolBar is created to implement the buttons.  All of the buttons are added to the tool bar.  When a button is pressed, an action command is passed to a tool bar listener.  The Tool Bar has a subscriber pattern.  A list of subscribers is stored in the class and there is a method to implement additional subscribers.  In the GUI view, a ToolBarListener class is created that implements ISubscriber and the method notify(String payload).  When a payload is given to the method, a switch statement determines if booleans should be set to true or false.  When the controller checks on these values, if any are true it causes the controller to implement an action (save, pause, restart, increase speed, decrease speed, etc.).  In the EasyAnimatorViewGui constructor, a layout is implemented, dimensions are set, and a scroll bar is created, taking in the drawing panel.  A ToolBar is also created and the ToolBarListener class is added as a subscriber.  Finally, the toolbar and scroll pane are added to the JFrame and it is set to visible.

We used the subscriber pattern because if the buttons need to influence any other classes, they can be added as subscribers and easily implemented. This makes the code adaptable and each to extend.

The Controller (IEasyAnimatorControllerImpl) checks if a GUI view should be created, and if so, creates the GUI view.  It then starts a timer that acts as an overall timer.  This prompts the controller to continually check if a button in the GUI has been pressed.  If a button has been pressed, it sets the appropriate changes in the view.  The controller also starts another timer.  This timer is what is paused and restarted when the pause and restart buttons are pressed.  This timer also checks into the model at each tick and gets a list of all shapes at that time.  These shapes are copies of the original shapes and are directly changed at each tick.  At each tick, the controller sets the shapes in the view to be the shapes at the correct tick and calls a method that repaints the drawing panel.  This updates what is shown to the user.




See below for text from previous README.txt for Assignment 8:

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


