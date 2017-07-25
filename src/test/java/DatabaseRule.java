import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new  Sql2o("jdbc:postgresql://epicodus-zombies.cafkxnyet4pa.us-west-2.rds.amazonaws.com:5432/epicodus_zombies_test", "epicodus_student", "stayfocused");
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
