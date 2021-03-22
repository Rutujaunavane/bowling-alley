package com.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PROTECTED)
@Getter
public class Frame {

  private Integer frameNumber;
  private int currentScore = 0;
  private int attemptOneScore  = -1;
  private int attemptTwoScore = -1;

  protected boolean isStrike() {
    return attemptOneScore == 10;
  }

  protected boolean isSpare() {
    return (attemptOneScore != 10 && (attemptOneScore + attemptTwoScore) == 10);
  }


}
