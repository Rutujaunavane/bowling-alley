package com.core;

import com.exception.ImproperInputException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lane {

  private int laneNumber;
  private boolean isLaneFree;

  private Game game;

  public void playGame() throws ImproperInputException {
    if( game==null || game.getPlayers().isEmpty()){
      throw  new ImproperInputException("Game cannot be started without adding player");
    }
    else
      game.start();
  }

  public List<ArrayList<Frame>> getGameScore() {
    return game.getPlayersFramesList();
  }

  public Player getWinningPlayer() {
    return game.getWinningPlayer();
  }
}
