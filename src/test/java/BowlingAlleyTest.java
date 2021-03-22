
import com.core.BowlingAlleyManager;
import com.core.Frame;
import com.exception.ImproperInputException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BowlingAlleyTest {


  @Before
  public void testInitializeInput() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    bowlingAlleyManager.initializeBowlingAlley(2);
  }

  @Test
  public void testFreeLanesInitializeToNumberOfLanes() {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    Assert.assertEquals(bowlingAlleyManager.getFreeLanes().size(), 2);
  }

  @Test
  public void testGameScoreInitializedToZero() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    List<String> names = getPlayers();
    bowlingAlleyManager.initializeGame(names,1);
    List<ArrayList<Frame>> gameScore = bowlingAlleyManager.getScoreByLane(1);
    Assert.assertEquals(gameScore.get(0).get(0).getCurrentScore(), 0);
  }

  @Test(expected = ImproperInputException.class)
  public void testInvalidLaneNumber() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    List<String> names = getPlayers();
    bowlingAlleyManager.initializeGame(names,1);
    List<ArrayList<Frame>> gameScore = bowlingAlleyManager.getScoreByLane(3);
  }

  @Test
  public void checkGameStartWithoutPlayers() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    int lane = bowlingAlleyManager.initializeGame(getPlayers(),1);
    Assert.assertFalse(bowlingAlleyManager.isLaneAssignedAGame(2));

  }

  @Test()
  public void testWinningPlayerForSinglePlayer() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    List<String> names = new ArrayList<>();
    names.add("Player1");
    bowlingAlleyManager.initializeGame(names,1);
    bowlingAlleyManager.startGameByLane(1);
    Assert.assertEquals(bowlingAlleyManager.getWinningPlayerByLane(1).getPlayerName(), "Player1");
  }

  @Test()
  public void testNoOfGamesMoreThanNoOfLanes() throws ImproperInputException {
    BowlingAlleyManager bowlingAlleyManager = new BowlingAlleyManager();
    bowlingAlleyManager.initializeGame(getPlayers(), 1);
    bowlingAlleyManager.initializeGame(getPlayers(), 2);
    Assert.assertEquals(bowlingAlleyManager.initializeGame(getPlayers(), 3), -1);
  }


  private List<String> getPlayers() {
    List<String> names = new ArrayList<>();
    names.add("Player1");
    names.add("Player2");
    return names;
  }

}
