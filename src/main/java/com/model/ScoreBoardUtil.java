package com.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardUtil {


  public static void printScoreBoard(List<ArrayList<Frame>> playersFramesList,
      List<Player> players, Player winningPlayer) {
    printHeader();
    printRows(playersFramesList, players);
    System.out.println("Winning Player is => \033[0;31m" + winningPlayer.getPlayerName());
  }

  private static void printRows(List<ArrayList<Frame>> playersFramesList, List<Player> players) {
    for (int i = 0; i < players.size(); i++) {
      int score = 0;
      System.out.format("\033[0m%5s", players.get(i).getPlayerName());
      for (Frame frame : playersFramesList.get(i)) {
        score = score + frame.getCurrentScore();
        printFrameScore(score, frame);
      }
      System.out.println();
      System.out.println();
      System.out.println(
          "\033[0m------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
  }

  private static void printHeader() {
    System.out
        .printf("%5s %11s %11s %11s %11s %11s %11s %11s %11s %11s %11s", "", "Frame 1", "Frame 2",
            "Frame 3", "Frame 4", "Frame 5", "Frame 6", "Frame 7", "Frame 8", "Frame 9",
            "Frame 10");
    System.out.println();
    System.out.println(
        "------------------------------------------------------------------------------------------------------------------------------------------------------------");
  }

  private static void printFrameScore(int score, Frame frame) {
    if (frame instanceof LastFrame) {
      System.out.format("\033[0m%6s|\033[0m%1s|\033[0m%s|\033[0;31m%s",
          getDataToPrint(frame.getAttemptOneScore(), 1),
          getDataToPrint(frame.getAttemptTwoScore(), 2),
          getDataToPrint(((LastFrame) frame).getAttemptThreeScore(), 3), score);
    } else {
      System.out.format("\033[0m%6s|\033[0m%1s|\033[0;31m%s",
          getDataToPrint(frame.getAttemptOneScore(), 1),
          getDataToPrint(frame.getAttemptTwoScore(), 2), score);
    }
  }

  private static String getDataToPrint(int score, int attempt) {
    if (score == 10 && attempt == 1) {
      return "X";
    } else if (score == 10 && attempt == 2) {
      return "/";
    } else if (score == 0) {
      return "-";
    } else if (score == -1) {
      return " ";
    } else {
      return Integer.toString(score);
    }
  }

}
