package com.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PROTECTED)
@Getter
public class Player {

  private String playerName;
  private int currentScore = 0;
}
