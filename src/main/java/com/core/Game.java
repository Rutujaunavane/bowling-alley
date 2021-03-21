package com.core;

import com.constant.ApplicationConstant;
import com.util.NumberUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

  private double gameNumber;
  private List<ArrayList<Frame>> playersFramesList = new ArrayList<>();
  private List<Player> players = new ArrayList<>();
  private List<Frame> frames = new ArrayList<>(ApplicationConstant.NO_OF_FRAMES);


  public void start()  {
    for (int frameNo = 0; frameNo < ApplicationConstant.NO_OF_FRAMES; frameNo++) {
      playForFrame(frameNo);
      System.out.println();
      System.out.format("\t\t****************Round %s ******************",frameNo+1);
      System.out.println();
      ScoreBoardUtil.printScoreByFrames(players, frameNo,playersFramesList);
      System.out.println();
      System.out.println();
      moveAheadInput();
    }
    Player winningPlayer = getWinningPlayer(players, playersFramesList);
    ScoreBoardUtil.printWinner(winningPlayer);
  }

  /**
   * This method finds the winning player
   * @param players
   * @param playersFramesList
   * @return
   */
  private Player getWinningPlayer(List<Player> players, List<ArrayList<Frame>> playersFramesList) {
    for (int i = 0; i < players.size(); i++) {
      int score = 0;
      for (Frame frame : playersFramesList.get(i)) {
        score = score + frame.getCurrentScore();
      }
      players.get(i).setCurrentScore(score);
    }
    return players.stream().max(Comparator.comparing(Player::getCurrentScore)).get();
  }

  private void playForFrame(int frameNo) {
    for (int playerNo = 0; playerNo < players.size(); playerNo++) {
      Frame currentFrame = playersFramesList.get(playerNo).get(frameNo);

      int attempt1Score = playAttemptOne(frameNo, playerNo, currentFrame);

      if (attempt1Score == ApplicationConstant.STRIKE_SPARE_SCORE) {
        if (isLastFrame(currentFrame)) {
          attempt1Score = 0;
        } else {
          continue;
        }
      }

      int attempt2Score = playAttemptTwo(currentFrame, attempt1Score);

      if (frameNo - 1 > -1) {
        Frame previousFrame = playersFramesList.get(playerNo).get(frameNo - 1);
        if (previousFrame.isStrike()) {
          previousFrame.setCurrentScore(
              previousFrame.getCurrentScore() + currentFrame.getAttemptTwoScore());
        }
      }
      handleLastFrame(currentFrame, attempt2Score);
    }
  }

  /**
   * This method handles the score for last frame
   * @param currentFrame
   * @param attempt2Score
   */
  private void handleLastFrame(Frame currentFrame, int attempt2Score) {
    if (isLastFrame(currentFrame) & (currentFrame.isSpare() || currentFrame
        .isStrike())) {
      int attempt3Score = hit(ApplicationConstant.NO_OF_PINS - attempt2Score);
      ((LastFrame) currentFrame).setAttemptThreeScore(attempt3Score);
      currentFrame.setCurrentScore(
          currentFrame.getCurrentScore() + ((LastFrame) currentFrame).getAttemptThreeScore());
    }
  }

  /**
   * This method handles the score as per second attempt
   * @param currentFrame
   * @param attempt1Score
   * @return
   */
  private int playAttemptTwo(Frame currentFrame, int attempt1Score) {
    int attempt2Score = hit(ApplicationConstant.NO_OF_PINS - attempt1Score);

    currentFrame.setAttemptTwoScore(attempt2Score);
    currentFrame
        .setCurrentScore(currentFrame.getCurrentScore() + currentFrame.getAttemptTwoScore());

    if (isLastFrame(currentFrame)
        && attempt2Score == ApplicationConstant.STRIKE_SPARE_SCORE) {
      attempt2Score = 0;
    }

    return attempt2Score;
  }

  /**
   * This method calculates score in attempt one
   * @param frameNo
   * @param playerNo
   * @param currentFrame
   * @return
   */
  private int playAttemptOne(int frameNo, int playerNo, Frame currentFrame) {
    int attempt1Score = hit(ApplicationConstant.NO_OF_PINS);
    currentFrame.setAttemptOneScore(attempt1Score);
    currentFrame.setCurrentScore(attempt1Score);
    if (frameNo - 1 > -1) {
      Frame previousFrame = playersFramesList.get(playerNo).get(frameNo - 1);
      if (previousFrame.isStrike()) {
        handleStrikeHit(frameNo, playerNo, currentFrame, previousFrame);
      } else if (previousFrame.isSpare()) {
        previousFrame.setCurrentScore(
            previousFrame.getCurrentScore() + currentFrame.getAttemptOneScore());
      }
    }
    return attempt1Score;
  }


  /**
   * This method calculates score when strike is hit
   * @param frameNo
   * @param playerNo
   * @param currentFrame
   * @param previousFrame
   */
  private void handleStrikeHit(int frameNo, int playerNo, Frame currentFrame, Frame previousFrame) {
    previousFrame.setCurrentScore(
        previousFrame.getCurrentScore() + currentFrame.getAttemptOneScore());
    if (frameNo - 2 > -1) {
      Frame previousFrame2 = playersFramesList.get(playerNo).get(frameNo - 2);
      if (previousFrame2.isStrike()) {
        previousFrame2.setCurrentScore(
            previousFrame2.getCurrentScore() + currentFrame.getAttemptOneScore());
      }
    }
  }

  /**
   * This method checks if the frame is the last frame
   * @param frame
   * @return
   */
  private boolean isLastFrame(Frame frame) {
    return frame instanceof LastFrame;
  }


  /**
   * This method returns the randomly generated number
   * @param noOfPins
   * @return
   */
  private int hit(int noOfPins) {
    return NumberUtil.getRandomInteger(noOfPins, 0);
  }

  private void moveAheadInput(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Press enter to move to the next frame");
    String readString = scanner.nextLine();
  }
}
