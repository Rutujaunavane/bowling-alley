package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lane {

  private Integer laneNumber;
  private boolean isLaneFree;

  private Game game;
  private Pin pins;

  public void playGame(){
    game.start();
  }
}
