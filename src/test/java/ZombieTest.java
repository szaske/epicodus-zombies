import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
// import java.util.Arrays;

public class ZombieTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void find_ReturnsTheCorrectZombie() {
    assertEquals("Small Zombie", Zombie.find(1).getName());
  }

  @Test
  public void initZombies_ResetsLocationOfZombies() {
    Zombie.initZombies();
    assertEquals(5,Zombie.find(1).getLocation());
    assertEquals(17,Zombie.find(2).getLocation());
    assertEquals(10,Zombie.find(3).getLocation());
  }

  @Test
  public void all_returnsAllZombies() {
    Zombie.all();
    assertEquals(3,Zombie.all().get(2).getId());
  }


  @Test
  public void setZombieLocation_shouldChangelocations() {
    Zombie.setZombieLocation(1, 14);
    assertEquals(14,Zombie.all().get(0).getLocation());
    assertEquals(17,Zombie.all().get(1).getLocation());
  }

  @Test
  public void checkZombieLocation_returnsTrueIfZombieAtLocation_true() {
    Zombie.setZombieLocation(1, 8);
    Location testLocation = new Location ("Joel's Room", "You have found yourself in a room full of junk.");
    testLocation.save();
    Exit firstExit = new Exit( "NORTH", testLocation.getId(), 8 );
    firstExit.save();
    Exit secondExit = new Exit( "SOUTH", testLocation.getId(), 6 );
    secondExit.save();
    assertEquals(true, Zombie.checkZombieLocation(8));

  }

}
