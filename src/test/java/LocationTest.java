import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LocationTest {
  // @Rule
  // public DatabaseRule database = new DatabaseRule();

  @Test
  public void location_instantiatesCorrectly_true() {
    Location testLocation = new Location("The Kitchen", "A messy kitchen");
    assertEquals(true, testLocation instanceof Location);
  }

  // @Test
  // public void getName_animalInstantiatesWithName_Deer() {
  //   Animal testAnimal = new Animal("Deer");
  //   assertEquals("Deer", testAnimal.getName());
  // }
  //
  // @Test
  // public void equals_returnsTrueIfNameIsTheSame_false() {
  //   Animal firstAnimal = new Animal("Deer");
  //   Animal anotherAnimal = new Animal("Deer");
  //   assertTrue(firstAnimal.equals(anotherAnimal));
  // }
}
