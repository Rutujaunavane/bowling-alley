
import static org.mockito.BDDMockito.given;

import com.BowlingAlleyManager;
import com.core.BowlingAlley;
import com.core.Frame;
import com.exception.ImproperInputException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class BowlingAlleyTest {

  BowlingAlleyManager bowlingAlleyManager;


  public void setUp() {
    this.bowlingAlleyManager = new BowlingAlleyManager();

  }


  @Test()
  public void testInitializeInput() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    BowlingAlley bowlingAlley = bowlingAlleyManager.getBowlingAlley();
    bowlingAlley.initializeBowlingAlley(2);
  }


  @Test
  public void checkFreeLanesInitializeToNumberOfLanes() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    BowlingAlley bowlingAlley = bowlingAlleyManager.getBowlingAlley();
    bowlingAlley.initializeBowlingAlley(10);
    Assert.assertEquals(bowlingAlley.getFreeLanes().size(), 10);
  }

  @Test
  public void testGameScoreInitializedToZero() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    BowlingAlley bowlingAlley = bowlingAlleyManager.getBowlingAlley();
    bowlingAlley.initializeBowlingAlley(1);
    List<String> names = getPlayers();
    bowlingAlley.initializeGame(names);
    List<ArrayList<Frame>> gameScore = bowlingAlley.getScoreByLane(1);
    Assert.assertEquals(gameScore.get(0).get(0).getCurrentScore(), 0);
  }

  @Test(expected = ImproperInputException.class)
  public void invalidLaneNumber() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    BowlingAlley bowlingAlley = bowlingAlleyManager.getBowlingAlley();
    bowlingAlley.initializeBowlingAlley(1);
    List<String> names = getPlayers();
    bowlingAlley.initializeGame(names);
    List<ArrayList<Frame>> gameScore = bowlingAlley.getScoreByLane(2);
  }

  @Test(expected = ImproperInputException.class)
  public void checkGameStartWithoutPlayers() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    BowlingAlley bowlingAlley = bowlingAlleyManager.getBowlingAlley();
    bowlingAlley.initializeBowlingAlley(1);

    bowlingAlley.startGame(1);
  }

  private List<String> getPlayers() {
    List<String> names = new ArrayList<>();
    names.add("Player1");
    names.add("Player2");
    return names;
  }

}
