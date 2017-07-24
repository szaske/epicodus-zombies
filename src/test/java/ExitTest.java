import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ExitTest {
  // @Rule
  // public DatabaseRule database = new DatabaseRule();

  @Test
  public void exit_instantiatesCorrectly_true() {
    Exit testExit = new Exit(1, 1);
    assertEquals(true, testExit instanceof Exit);
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
