package com;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BowlingAlleyManager {



  private List<String> namesOfPlayer;
  private int noOfPlayers;
  private int noOfLanes;


  public static void main(String args[]){
    List<String> namesOfPlayer = new ArrayList<>();
    namesOfPlayer.add("Rutuja");
    namesOfPlayer.add("Vikram");
    namesOfPlayer.add("Aditya");
    BowlingAlley bowlingAlley = BowlingAlley.getInstance();
    bowlingAlley.initializeBowlingAlley(10);
    int laneNumber = bowlingAlley.initializeGame(namesOfPlayer);
    bowlingAlley.startGame(laneNumber);
  }

}
