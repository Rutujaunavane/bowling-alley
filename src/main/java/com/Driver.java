package com;

import com.core.BowlingAlleyManager;
import com.exception.ImproperInputException;
import java.io.IOException;

public class Driver {

  public static void main(String args[]) throws IOException, ImproperInputException {
     BowlingAlleyManager bowlingAlleyManager =new BowlingAlleyManager();
     bowlingAlleyManager.buildGame();
  }
}
