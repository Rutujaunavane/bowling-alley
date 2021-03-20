package com.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {

  private String playerName;
  private int score;
  private List<Frame> strikeSpareFrames = new ArrayList<>();
  private int attempts;


}
