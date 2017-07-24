import org.sql2o.*;

public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://epicodus-zombies.cafkxnyet4pa.us-west-2.rds.amazonaws.com:5432/epicodus_zombies", "epicodus_student", "stayfocused");
}
