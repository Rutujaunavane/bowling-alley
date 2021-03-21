package com;

import com.core.BowlingAlley;
import com.exception.ImproperInputException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BowlingAlleyManager {

  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private BowlingAlley bowlingAlley = BowlingAlley.getInstance();

  public BowlingAlley getBowlingAlley() {
    return bowlingAlley;
  }

  public void buildGame()
      throws ImproperInputException, IOException {
    getInputFromConsole();
  }

  private void getInputFromConsole() throws IOException, ImproperInputException {
    int noOfLanes = initializeLanes();
    System.out.println("Enter the number of games to be played: ");
    int noOfGames = Integer.parseInt(reader.readLine());
    int i = 1;
    while (i <= noOfGames) {
      initializeGame(i);
      i++;
    }
    startGames(noOfLanes);
  }

  private void startGames(int noOfLanes) throws IOException, ImproperInputException {
    while (true) {
      System.out.println("Do you want to start playing the games? Press Y/N");
      String input = reader.readLine();
      if ("Y".equalsIgnoreCase(input)) {
        for (int i = 0; i < noOfLanes; i++) {
          System.out.println();
          System.out.println("Game started on lane =>" + i);
          bowlingAlley.startGame(i + 1);
        }
        break;
      } else if ("N".equalsIgnoreCase(input)) {
        System.out.println("Games not started");
        break;
      } else {
        System.out.println("Input not in correct format");
      }
    }
  }

  private void initializeGame(int game) throws IOException, ImproperInputException {
    System.out.println("Enter the number of players for game" + game);
    int noOfPlayers = Integer.parseInt(reader.readLine());
    List<String> playerNames = new ArrayList<>();
    int i = 1;
    while (i <= noOfPlayers) {
      System.out.println("Enter name of the player :" + i);
      playerNames.add(reader.readLine());
      i++;
    }
    int lane = bowlingAlley.initializeGame(playerNames);
    if (lane == -1) {
      System.out.println("No lane is free for game " + game);
    } else {
      System.out.println("Lane assigned for game " + game + " is" + lane);
    }
  }

  private int initializeLanes() throws IOException, ImproperInputException {
    System.out.println("Enter the number of lanes for bowling alley");
    int noOfLanes = Integer.parseInt(reader.readLine());
    bowlingAlley.initializeBowlingAlley(noOfLanes);
    return noOfLanes;
  }
}
