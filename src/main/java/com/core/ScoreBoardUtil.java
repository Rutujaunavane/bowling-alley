package com.core;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardUtil {

  public static void printWinner(Player winningPlayer, int gameNumber) {
    System.out.println("\033[0m");
    System.out.format("Winning Player for game %s is \033[0;31m%s", gameNumber,winningPlayer.getPlayerName());
  }

  public static void printScoreByFrames(List<Player> players, int frameNo,
      List<ArrayList<Frame>> playersFramesList) {
    System.out.println("\033[0m");
    printHeader();
    for (int i = 0; i < players.size(); i++) {
      int score = 0;
      System.out.format("\033[0m%5s", players.get(i).getPlayerName());
      for (Frame frame : playersFramesList.get(i)) {
        if (frame.getFrameNumber() <= frameNo) {
          score = score + frame.getCurrentScore();
        } else {
          score = 0;
        }
        printFrameScore(score, frame);
      }
      System.out.println();
      System.out.println();
      System.out.println(
          "\033[0m------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
  }

  public static void printHeader() {
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
          getDataToPrint(frame.getAttemptOneScore(), 1, frame),
          getDataToPrint(frame.getAttemptTwoScore(), 2, frame),
          getDataToPrint(((LastFrame) frame).getAttemptThreeScore(), 3, frame), score);
    } else {
      System.out.format("\033[0m%6s|\033[0m%1s|\033[0;31m%s",
          getDataToPrint(frame.getAttemptOneScore(), 1, frame),
          getDataToPrint(frame.getAttemptTwoScore(), 2, frame), score);
    }
  }

  private static String getDataToPrint(int score, int attempt, Frame frame) {
    if (frame.isStrike() && attempt == 1) {
      return "X";
    } else if (frame.isSpare() && attempt == 2) {
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
