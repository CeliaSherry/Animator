package animation;

/**
 * This Enum represents the type of an animation. It can be extended as needed. It is used in the
 * toString method, which requires adding the type information according to the specific type of
 * animation. It is also used in determining whether there is a time conflict while adding a new
 * animation, because time conflict only exists among animations of the same type.
 */
public enum AnimType {
  MOVE, SCALE, COLORCHANGE
}
