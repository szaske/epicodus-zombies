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
    assertEquals(3,Zombie.find(1).getLocation());
    assertEquals(17,Zombie.find(2).getLocation());
    assertEquals(11,Zombie.find(3).getLocation());
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




}
