package com.core;

import com.constant.ApplicationConstant;
import com.exception.ImproperInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BowlingAlley {

  private static BowlingAlley bowlingAlley;
  private Map<Integer, Lane> laneNumberLaneMap = new HashMap<>();

  public static BowlingAlley getInstance() {
    if (bowlingAlley == null) {
      bowlingAlley = new BowlingAlley();
    }

    return bowlingAlley;
  }

  public void initializeBowlingAlley(int noOfLanes) throws ImproperInputException {
    if (noOfLanes <= 0) {
      throw new ImproperInputException("No players added. Game cannot be started");
    } else {
      for (int i = 0; i < noOfLanes; i++) {
        Lane lane = new Lane();
        lane.setLaneNumber(i + 1);
        lane.setLaneFree(true);
        laneNumberLaneMap.put(i + 1, lane);
      }
    }
  }

  private Lane getFreeLane() {
    Optional<Entry<Integer, Lane>> freeLane = laneNumberLaneMap.entrySet().stream()
        .filter(e -> e.getValue().isLaneFree()).findFirst();
    return freeLane.get().getValue();
  }

  public int initializeGame(List<String> namesOfPlayer) throws ImproperInputException {
    if (Objects.isNull(namesOfPlayer) || namesOfPlayer.isEmpty()) {
      throw new ImproperInputException("No players added. Game cannot be started");
    } else {
      Lane lane = getFreeLane();
      Game game = new Game();
      game.setGameNumber(Math.random());
      game.setPlayers(getPlayers(namesOfPlayer));
      game.setFrames(getFrames());
      game.setPlayersFramesList(getPlayersFramesList(namesOfPlayer));
      lane.setGame(game);
      bowlingAlley.laneNumberLaneMap.put(lane.getLaneNumber(), lane);
      return lane.getLaneNumber();
    }
  }

  public List<ArrayList<Frame>> getPlayersFramesList(List<String> namesOfPlayer) {
    List<ArrayList<Frame>> playersFramesList = new ArrayList<>(namesOfPlayer.size());
    namesOfPlayer.forEach(player -> playersFramesList.add(getFrames()));
    return playersFramesList;
  }

  private List<Player> getPlayers(List<String> namesOfPlayer) {
    return namesOfPlayer.stream().map(name -> {
      Player p = new Player();
      p.setPlayerName(name);
      return p;
    }).collect(Collectors.toList());
  }


  private ArrayList<Frame> getFrames() {
    List<Frame> frames = new ArrayList<>();
    for (int i = 0; i < ApplicationConstant.LAST_FRAME_NUMBER; i++) {
      Frame f = new Frame();
      f.setFrameNumber(i);
      frames.add(f);
    }
    frames.add(getLastFrame());
    return (ArrayList<Frame>) frames;
  }

  private LastFrame getLastFrame() {
    LastFrame lastFrame = new LastFrame();
    lastFrame.setFrameNumber(ApplicationConstant.LAST_FRAME_NUMBER);
    return lastFrame;
  }

  public void startGame(int laneNumber) {
    if (laneNumberLaneMap.containsKey(laneNumber)) {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      lane.playGame();
    }
  }
}