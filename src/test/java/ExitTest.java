import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ExitTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void exit_instantiatesCorrectly_true() {
    Exit testExit = new Exit ( 1, 3, 5 );
    assertEquals(true, testExit instanceof Exit);
  }

  @Test
  public void equals_returnsTrueIfDirectionAndLocationIdAndLeadsToIdAreSame_true() {
    Exit firstExit = new Exit ( 1, 3, 5 );
    Exit anotherExit = new Exit ( 1, 3, 5 );
    assertTrue(firstExit.equals(anotherExit));
  }

}
