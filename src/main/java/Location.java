import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// Location - represents a gaming location

public class Location
{
	// Member variables
	private String roomTitle;
	private String roomDescription;
	private int id;

	// constructor
	public Location( String name, String description )
	{
		// Assign title + description
		this.roomTitle = name;
		this.roomDescription = description;
	}

  //   Getter methods /////////////////////////////////////

	public String getTitle()
	{
		return this.roomTitle;
	}

	public String getDescription()
	{
		return roomDescription;
	}

	public int getId() {
		return id;
	}

	// Other public methods

  public static Location find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations where id=:id";
      Location location = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Location.class);
      return location;
    }
  }

	@Override
	public boolean equals(Object otherLocation) {
		if(!(otherLocation instanceof Location)) {
			return false;
		} else {
			Location newLocation = (Location) otherLocation;
			return this.getTitle().equals(newLocation.getTitle()) &&
						 this.getDescription().equals(newLocation.getDescription());
		}
	}

	//currently only used for test database since dev database is updated directly:Michael
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO locations (roomtitle, roomdescription) VALUES (:roomtitle, :roomdescription)";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("roomtitle", this.roomTitle)
				.addParameter("roomdescription", this.roomDescription)
				.executeUpdate()
				.getKey();
		}
	}

	public static List<Location> all() {
    String sql = "SELECT * FROM locations";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql)
		 		.throwOnMappingFailure(false)
		 		.executeAndFetch(Location.class);
    }
  }

	public List<Exit> getExits () {
    try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM exits where locationId=:id";

			return con.createQuery(sql)
			.addParameter("id", this.id)
			.throwOnMappingFailure(false)
			.executeAndFetch(Exit.class);
		}
	}

	public List<String> getExitNames () {
    try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT direction FROM exits where locationId=:id";

			return con.createQuery(sql)
			.addParameter("id", this.id)
			.throwOnMappingFailure(false)
			.executeAndFetch(String.class);
		}
	}
} // end of location class
