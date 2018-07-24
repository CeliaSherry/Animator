package cs5004.animator.controller;

import builder.TweenModelBuilder;
import cs5004.animator.model.IEasyAnimatorModel;
import cs5004.animator.model.IEasyAnimatorModel2;

public interface IEasyAnimatorController {
  /**
   *
   * @param model
   * @param viewMode
   * @param output
   * @param speed
   * @throws IllegalArgumentException
   */
  void start(IEasyAnimatorModel2 model, String viewMode,
             String output, String speed) throws IllegalArgumentException;
}
