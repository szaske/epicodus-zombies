import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ExitTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void exit_instantiatesCorrectly_true() {
    Exit testExit = new Exit ( "NORTH", 3, 5 );
    assertEquals(true, testExit instanceof Exit);
  }

  @Test
  public void equals_returnsTrueIfDirectionAndLocationIdAndLeadsToIdAreSame_true() {
    Exit firstExit = new Exit ( "NORTH", 3, 5 );
    Exit anotherExit = new Exit ( "NORTH", 3, 5 );
    assertTrue(firstExit.equals(anotherExit));
  }

  @Test
  public void save_returnsTrueIfExitsAretheSame() {
    Exit testExit = new Exit( "NORTH", 3, 5 );
    testExit.save();
    assertTrue(Exit.all().get(0).equals(testExit));
  }

  //will fail without test database
  @Test
  public void save_assignsIdToExit() {
    Exit testExit = new Exit( "NORTH", 3, 5 );
    testExit.save();
    Exit savedExit = Exit.all().get(0);
    assertEquals(savedExit.getId(), testExit.getId());
  }

  //will fail without test database
  @Test
  public void all_returnsAllInstancesOfExit_true() {
    Exit firstExit = new Exit( "NORTH", 3, 5 );
    firstExit.save();
    Exit secondExit = new Exit( "SOUTH", 4, 6 );
    secondExit.save();
    assertEquals(true, Exit.all().get(0).equals(firstExit));
    assertEquals(true, Exit.all().get(1).equals(secondExit));
  }

  @Test
  public void find_returnsExitWithSameId_secondExit() {
    Exit firstExit = new Exit( "NORTH", 3, 5 );
    firstExit.save();
    Exit secondExit = new Exit( "SOUTH", 4, 6 );
    secondExit.save();
    assertEquals(Exit.find(secondExit.getId()), secondExit);
  }

  @Test
  public void save_savesLocationIdIntoDB_true() {
    Location testLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    testLocation.save();
    Exit testExit = new Exit( "NORTH", testLocation.getId(), 5 );
    testExit.save();
    Exit savedExit = Exit.find(testExit.getId());
    assertEquals(savedExit.getLocationId(), testLocation.getId());
  }

  @Test
  public void save_savesLeadsToIdIntoDB_true() {
    Location testLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    testLocation.save();
    Exit testExit = new Exit( "NORTH", 3, testLocation.getId() );
    testExit.save();
    Exit savedExit = Exit.find(testExit.getId());
    assertEquals(savedExit.getLeadsToLocationId(), testLocation.getId());
  }


}
