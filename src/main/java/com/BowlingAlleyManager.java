package com;

import com.core.BowlingAlley;
import com.core.InputDetails;
import com.exception.ImproperInputException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;

public class BowlingAlleyManager {

  public static void main(String args[])
      throws ImproperInputException {
    InputDetails inputDetails = getInput();
    buildGame(inputDetails);
  }

  public static void buildGame(InputDetails inputDetails)
      throws ImproperInputException {
    BowlingAlley bowlingAlley = BowlingAlley.getInstance();
    bowlingAlley.initializeBowlingAlley(inputDetails.getNoOfLanes());
    int laneNumber = bowlingAlley.initializeGame(inputDetails.getPlayerNames());
    System.out.println("Alloted Lane =>" + laneNumber);
    bowlingAlley.startGame(laneNumber);
  }

  private static InputDetails getInput() throws ImproperInputException {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    InputDetails inputDetails = null;
    try {
      inputDetails = mapper
          .readValue(new File("src/main/resources/application.yaml"), InputDetails.class);
    } catch (IOException e) {
      throw new ImproperInputException("Input not in proper format. Game cannot be started");
    }
    return inputDetails;
  }

}
