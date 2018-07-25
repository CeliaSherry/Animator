package cs5004.animator.controller;

import cs5004.animator.model.IEasyAnimatorModel;

public interface IEasyAnimatorController {
  /**
   *
   * @param model
   * @param viewMode
   * @param output
   * @param speed
   * @throws IllegalArgumentException
   */
  void start(IEasyAnimatorModel model, String viewMode,
             String output, String speed) throws IllegalArgumentException;
}
