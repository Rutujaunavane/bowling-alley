package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;

public class BowlingAlleyManager {

  public static void main(String args[]) throws IOException {
    BowlingAlley bowlingAlley = BowlingAlley.getInstance();
    InputDetails inputDetails = getInput();
    bowlingAlley.initializeBowlingAlley(inputDetails.getNoOfLanes());
    int laneNumber = bowlingAlley.initializeGame(inputDetails.getPlayerNames());
    System.out.println("Alloted Lane =>" + laneNumber);
    bowlingAlley.startGame(laneNumber);
  }

  private static InputDetails getInput() throws IOException {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    InputDetails inputDetails = mapper
        .readValue(new File("src/main/resources/application.yaml"), InputDetails.class);
    return inputDetails;
  }

}
