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

  protected void initializeBowlingAlley(int noOfLanes) throws ImproperInputException {
    if (noOfLanes <= 0) {
      throw new ImproperInputException("No number of lanes added. Game cannot be started");
    } else {
      for (int i = 0; i < noOfLanes; i++) {
        Lane lane = new Lane();
        lane.setLaneNumber(i + 1);
        lane.setLaneFree(true);
        laneNumberLaneMap.put(i + 1, lane);
      }
    }
  }

  protected List<Lane> getFreeLanes(){
    List<Lane> freeLanes = new ArrayList<>();

    laneNumberLaneMap.forEach((k,v)->{
      if(v.isLaneFree())
         freeLanes.add(v);
    });
   return freeLanes;
  }

  private Lane getFreeLane() {
    Optional<Entry<Integer, Lane>> freeLane = laneNumberLaneMap.entrySet().stream()
        .filter(e -> e.getValue().isLaneFree()).findFirst();
    if(freeLane.isPresent())
      return freeLane.get().getValue();
    return null;
  }

  protected int initializeGame(List<String> namesOfPlayer) throws ImproperInputException {
    if (Objects.isNull(namesOfPlayer) || namesOfPlayer.isEmpty()) {
      throw new ImproperInputException("No players added. Game cannot be started");
    } else {
      Lane lane = getFreeLane();
      if(Objects.isNull(lane)){
        return -1;
      }
      else {
        Game game = new Game();
        game.setGameNumber(lane.getLaneNumber());
        game.setPlayers(getPlayers(namesOfPlayer));
        game.setFrames(getFrames());
        game.setPlayersFramesList(getPlayersFramesList(namesOfPlayer));
        lane.setGame(game);
        lane.setLaneFree(false);
        bowlingAlley.laneNumberLaneMap.put(lane.getLaneNumber(), lane);
        return lane.getLaneNumber();
      }
    }
  }

  protected Player getWinningPlayerByLane(int laneNumber) throws ImproperInputException {
    if(!laneNumberLaneMap.containsKey(laneNumber)){
      throw new ImproperInputException("Lane number not present in the alley");
    }
    else {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      return lane.getWinningPlayer();
    }
  }

  protected List<ArrayList<Frame>> getScoreByLane(int laneNumber) throws ImproperInputException {
    if(!laneNumberLaneMap.containsKey(laneNumber)){
      throw new ImproperInputException("Lane number not present in the alley");
    }
    else {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      return lane.getGameScore();
    }
  }

  protected int getScoreByPlayerAndLane(int laneNumber,String playerName)
      throws ImproperInputException {
    if(!laneNumberLaneMap.containsKey(laneNumber)){
      throw new ImproperInputException("Lane number not present in the alley");
    }
    else {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      List<Player> players = lane.getGame().getPlayers();
      for(Player p : players){
        if(p.getPlayerName().equalsIgnoreCase(playerName))
          return p.getCurrentScore();
      }

    }
    return laneNumber;
  }

  protected List<Frame> getScoresOfAllFramesForPlayerAndLane(int laneNumber,String playerName)
      throws ImproperInputException {
    if(!laneNumberLaneMap.containsKey(laneNumber)){
      throw new ImproperInputException("Lane number not present in the alley");
    }
    else {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      List<Player> players = lane.getGame().getPlayers();
      for(Player p : players){
        if(p.getPlayerName().equalsIgnoreCase(playerName))
          lane.getGame().getFrameScoreByPlayerName(playerName);
      }

    }
    return new ArrayList<>();
  }


  private List<ArrayList<Frame>> getPlayersFramesList(List<String> namesOfPlayer) {
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

  protected void startGame(int laneNumber) throws ImproperInputException {
    if (laneNumberLaneMap.containsKey(laneNumber)) {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      if (lane.isGameAssigned())
        lane.playGame();
      else {
        System.out.println("No Game assigned on lane:"+ laneNumber);
      }
    }
  }

  protected boolean isLaneAssignedAGame(int laneNumber) throws ImproperInputException {
    if (laneNumberLaneMap.containsKey(laneNumber)) {
      Lane lane = laneNumberLaneMap.get(laneNumber);
      if (lane.isGameAssigned())
        return true;
      else {
        return false;
      }
    }
    else
      throw new ImproperInputException("Lane number not present in the alley");
  }
}
