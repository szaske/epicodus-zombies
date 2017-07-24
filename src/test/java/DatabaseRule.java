import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/adventure_world_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteLocationsQuery = "DELETE FROM locations *;";
      con.createQuery(deleteLocationsQuery).executeUpdate();
      String deleteExitsQuery = "DELETE FROM exits *;";
      con.createQuery(deleteExitsQuery).executeUpdate();
    }
  }

}
