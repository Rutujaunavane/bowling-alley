package com.model;

import com.constants.FrameType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Frame {

  private Integer frameNumber;
  private int currentScore = 0;
  private int attemptOneScore;
  private int attemptTwoScore;
  private int attemptThreeScore;

  public boolean isStrike() {
    if (attemptOneScore == 10) {
      return true;
    }
    return false;
  }

  public boolean isSpare() {
    if (attemptOneScore != 10 && (attemptOneScore + attemptTwoScore) == 10) {
      return true;
    }
    return false;
  }
}
