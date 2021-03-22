package com.core;

import com.exception.ImproperInputException;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Lane {

  private int laneNumber;
  private boolean isLaneFree;

  private Game game;

  protected void playGame() throws ImproperInputException {
    if( game==null || game.getPlayers().isEmpty()){
      throw  new ImproperInputException("Game cannot be started without adding player.");
    }
    else {
      game.start();
      game.printWinningPlayer();
    }
  }

  protected List<ArrayList<Frame>> getGameScore() {
    return game.getPlayersFramesList();
  }

  protected Player getWinningPlayer() {
    return game.getWinningPlayer();
  }

  protected boolean isGameAssigned(){
    return game!=null;
  }
}
