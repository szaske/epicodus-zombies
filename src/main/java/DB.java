import org.sql2o.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DB {

  public static Properties getInfo() {

  	Properties prop = new Properties();
  	InputStream input = null;

  	try {
  		input = new FileInputStream("config.properties");

  		// load a properties file
  		prop.load(input);

  	} catch (IOException ex) {
  		ex.printStackTrace();
  	} finally {
  		if (input != null) {
  			try {
  				input.close();
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  		}
  	}
    return prop;
  } // end of getInfo

  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://epicodus-zombies.cafkxnyet4pa.us-west-2.rds.amazonaws.com:5432/"+ DB.getInfo().getProperty("database"), DB.getInfo().getProperty("dbuser"), DB.getInfo().getProperty("dbpassword"));

} //end of class
