package com.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

  private double gameNumber;

  private List<ArrayList<Frame>> playersFramesList = new ArrayList<>();

  private List<Player> players = new ArrayList<>();
  private List<Frame> frames = new ArrayList<>(10);

  public void start() {
    for (int frameNo = 0; frameNo < 10; frameNo++) {

      for (int playerNo = 0; playerNo < players.size(); playerNo++) {
        Frame currentFrame = playersFramesList.get(playerNo).get(frameNo);
        int attempt1Score = hit(10);

        currentFrame.setAttemptOneScore(attempt1Score);
        currentFrame.setCurrentScore(attempt1Score);
        if (frameNo - 1 > -1) {
          Frame previousFrame = playersFramesList.get(playerNo).get(frameNo - 1);
          if (previousFrame.isStrike()) {
            previousFrame.setCurrentScore(
                previousFrame.getCurrentScore() + currentFrame.getAttemptOneScore());
            if (frameNo - 2 > -1) {
              Frame previousFrame2 = playersFramesList.get(playerNo).get(frameNo - 2);
              if (previousFrame2.isStrike()) {
                previousFrame2.setCurrentScore(
                    previousFrame2.getCurrentScore() + currentFrame.getAttemptOneScore());
              }
            }
          } else if (previousFrame.isSpare()) {
            previousFrame.setCurrentScore(
                previousFrame.getCurrentScore() + currentFrame.getAttemptOneScore());
          }
        }

        if (attempt1Score == 10) {
          if (frameNo == 9) {
            attempt1Score = 0;
          } else {
            continue;
          }
        }

        int attempt2Score = hit(10 - attempt1Score);

        currentFrame.setAttemptTwoScore(attempt2Score);
        currentFrame
            .setCurrentScore(currentFrame.getCurrentScore() + currentFrame.getAttemptTwoScore());

        if (frameNo == 9 && attempt2Score == 10) {
          attempt2Score = 0;
        }

        if (frameNo - 1 > -1) {
          Frame previousFrame = playersFramesList.get(playerNo).get(frameNo - 1);
          if (previousFrame.isStrike()) {
            previousFrame.setCurrentScore(
                previousFrame.getCurrentScore() + currentFrame.getAttemptTwoScore());
          }
        }
        if (frameNo == 9 & (currentFrame.isSpare() || currentFrame.isStrike())) {
          int attempt3Score = hit(10 - attempt2Score);
          currentFrame.setAttemptThreeScore(attempt3Score);
          currentFrame.setCurrentScore(
              currentFrame.getCurrentScore() + currentFrame.getAttemptThreeScore());
        }
      }
    }
    printOutput();
  }

  private void printOutput() {
    for (int i = 0; i < playersFramesList.size(); i++) {
      System.out.println(players.get(i).getPlayerName());
      List<Frame> frames = playersFramesList.get(i);
      frames.forEach(frame -> {
        System.out.println("Frame=>" + frames.indexOf(frame));
        System.out.println("Attempt 1=>" + frame.getAttemptOneScore());
        System.out.println("Attempt 2=>" + frame.getAttemptTwoScore());
        if (frames.indexOf(frame) == 9) {
          System.out.println("Attempt 3=>" + frame.getAttemptThreeScore());
        }
        System.out.println("Current Score=>" + frame.getCurrentScore());
      });
    }
  }


  public int hit(int noOfBalls) {
    return getRandomInteger(noOfBalls, 0);
  }

  public static int getRandomInteger(int maximum, int minimum) {
    return (int) Math.round(Math.random() * (maximum - minimum)) + (int) minimum;
  }

}
