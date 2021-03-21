
import com.BowlingAlleyManager;
import com.core.InputDetails;
import com.exception.ImproperInputException;
import java.util.ArrayList;
import org.junit.Test;

public class BowlingAlleyTest {

  BowlingAlleyManager bowlingAlleyManager;


  public void setUp() {
    this.bowlingAlleyManager = new BowlingAlleyManager();

  }

  @Test(expected = ImproperInputException.class)
  public void testImproperInput() throws ImproperInputException {
    InputDetails inputDetails = new InputDetails();
    inputDetails.setNoOfLanes(0);
    inputDetails.setPlayerNames(new ArrayList<>());
    BowlingAlleyManager.buildGame(inputDetails);
  }

}
