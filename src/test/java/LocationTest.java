import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class LocationTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void location_instantiatesCorrectly_true() {
    Location testLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    assertEquals(true, testLocation instanceof Location);
  }

  @Test
  public void equals_returnsTrueIfRoomTitleAndRoomDescriptionAreSame_true() {
    Location firstLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    Location anotherLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    assertTrue(firstLocation.equals(anotherLocation));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Location() {
    Location testLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    testLocation.save();
    assertTrue(Location.all().get(0).equals(testLocation));
  }

  //will fail without test database
  @Test
  public void all_returnsAllInstancesOfLocation_true() {
    Location firstLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    firstLocation.save();
    Location secondLocation = new Location ("Couch Room", "You have found yourself in a comfortable place.");
    secondLocation.save();
    assertEquals(true, Location.all().get(0).equals(firstLocation));
    assertEquals(true, Location.all().get(1).equals(secondLocation));
  }

  //will fail without test database
  @Test
  public void save_assignsIdToObject() {
    Location testLocation = new Location("Couch Room", "You have found yourself in a comfortable place.");
    testLocation.save();
    Location savedLocation = Location.all().get(0);
    assertEquals(testLocation.getId(), savedLocation.getId());
  }

  @Test
  public void find_returnsLocationWithSameId_secondLocation() {
    Location firstLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    firstLocation.save();
    Location secondLocation = new Location ("Couch Room", "You have found yourself in a comfortable place.");
    secondLocation.save();
    assertEquals(Location.find(secondLocation.getId()), secondLocation);
  }

  @Test
  public void getExits_retrievesAllExitsFromDatabase_exitsList() {
    Location testLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
    testLocation.save();
    Exit firstExit = new Exit( "NORTH", testLocation.getId(), 5 );
    firstExit.save();
    Exit secondExit = new Exit( "SOUTH", testLocation.getId(), 6 );
    secondExit.save();
    Exit[] exits = new Exit[] { firstExit, secondExit };
    assertTrue(testLocation.getExits().containsAll(Arrays.asList(exits)));
  }

  // @Test
  //   void matchExit_getsMatchingExitFromString_true() {
  //   Location testLocation = new Location ("Computer Room", "You have found yourself in a room full of rows of expensive Mac computers.");
  //   testLocation.save();
  //   Exit firstExit = new Exit( 1, testLocation.getId(), 1 );
  //   firstExit.save();
  //   Exit secondExit = new Exit( 2, testLocation.getId(), 2 ); //2 is south
  //   secondExit.save();
  //   Exit[] exits = new Exit[] { firstExit, secondExit };
  //   assertEquals(testLocation.matchExit("SOUTH"), secondExit);
  //
  // }

}
