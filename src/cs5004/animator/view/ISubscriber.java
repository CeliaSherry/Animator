package cs5004.animator.view;

/**
 * This interface represents an ISubscriber whose sole purpose is to notify any changes in status to
 * the controller.
 */
public interface ISubscriber {

  /**
   * Notify changes of status based on the information provided by a payload string
   *
   * @param payload given payload string
   */
  void notify(String payload);
}
