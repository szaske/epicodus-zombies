import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

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

}
