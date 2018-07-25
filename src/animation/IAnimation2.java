package animation;

import shape.IShape2;

public interface IAnimation2 extends IAnimation {

  String toStringText(int speed);

  String toStringSvg(int speed, IShape2 shape);


}
