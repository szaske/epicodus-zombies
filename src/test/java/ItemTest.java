import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
// import java.util.Arrays;

public class ItemTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void location_instantiatesCorrectly_true() {
    Item testItem = new Item ("Gun", "A silver pistol",16,1,"szaske");
    assertEquals(true, testItem instanceof Item);
  }

  @Test
  public void find_findsTheNoodles_Noodles() {
    assertEquals("Noodles", Item.find(1).getName());
  }

  @Test
  public void getNoodles_SetsLocationToZero_0() {
    Item.getNoodles();
    assertEquals(0, Item.find(1).getLocation());
  }

  @Test
  public void initNoodles_SetsLocationTo16_16() {
    Item.getNoodles();
    Item.initNoodles();
    assertEquals(16, Item.find(1).getLocation());
  }

  //
  // //will fail without test database
  // @Test
  // public void all_returnsAllInstancesOfLocation_true() {
  //   Item testItem = new Item ("Gun", "A silver pistol",16,1,"szaske");
  //   testItem.save();
  //   Item testItem2 = new Item ("Wrench", "rusty",13,1,"szaske");
  //   testItem2.save();
  //   assertEquals(true, Item.all().get(0).equals(testItem));
  //   assertEquals(true, Item.all().get(1).equals(testItem2));
  // }
  // @Test
  // public void find_ReturnsTheCorrectItem() {
  //   assertEquals("Small Item", Item.find(1).getName());
  // }
  //
  // @Test
  // public void initItems_ResetsLocationOfItems() {
  //   Item.initItems();
  //   assertEquals(3,Item.find(1).getLocation());
  //   assertEquals(17,Item.find(2).getLocation());
  //   assertEquals(11,Item.find(3).getLocation());
  // }
  //
  // @Test
  // public void all_returnsAllItems() {
  //   Item.all();
  //   assertEquals(3,Item.all().get(2).getId());
  // }
  //
  //
  // @Test
  // public void setItemLocation_shouldChangelocations() {
  //   Item.setItemLocation(1, 14);
  //   assertEquals(14,Item.all().get(0).getLocation());
  //   assertEquals(17,Item.all().get(1).getLocation());
  // }
  //
  // @Test
  // public void checkItemLocation_returnsTrueIfItemAtLocation_true() {
  //   Item.setItemLocation(1, 8);
  //   Location testLocation = new Location ("Joel's Room", "You have found yourself in a room full of junk.");
  //   testLocation.save();
  //   Exit firstExit = new Exit( "NORTH", testLocation.getId(), 8 );
  //   firstExit.save();
  //   Exit secondExit = new Exit( "SOUTH", testLocation.getId(), 6 );
  //   secondExit.save();
  //   assertEquals(true, Item.checkItemLocation(8));
  //
  // }

}
