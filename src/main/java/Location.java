//import java.util.Vector;
//import java.util.Enumeration;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;


//
//
// Location - represents a gaming location
//
// Last modification date : October 08, 1997
//
public class Location
{
	// Member variables
	private String roomTitle;
	private String roomDescription;
	private int id;
	//private List<Exit> m_Exits;

	// Full constructor
	public Location( String name, String description )
	{
		// Assign title + description
		this.roomTitle = name;
		this.roomDescription = description;
	}

  //   GETTERS /////////////////////////////////////

	public String getTitle()
	{
		return this.roomTitle;
	}

  // Returns location description
	public String getDescription()
	{
		return roomDescription;
	}

	public int getId() {
		return id;
	}

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

	// public Exit matchExit(String directionString) {
	// 	List<Exit> exits = this.getExits();
	// 	Exit thisExit=null;
	// 	for (Exit exit : exits) {
	// 		if (exit.getDirectionName() == directionString) {
	// 			int direction = exit.getDirection();
	// 			try(Connection con = DB.sql2o.open())  {
	// 				String sql = "SELECT * FROM exits WHERE locationId=:id AND direction = :direction";
	// 				thisExit = con.createQuery(sql, true)
	// 				.addParameter("id", this.id)
	// 				.addParameter("direction", direction)
	// 				.throwOnMappingFailure(false)
	// 				.executeAndFetchFirst(Exit.class);
	// 			}
	// 		}
	// 	}
	// 	return thisExit;
	// }
	// // Adds an exit to this location
	// public void addExit ( Exit exit )
	// {
	// 	m_Exits.addElement (exit);
	// }
  //
	// // Removes an exit from this location
	// public void removeExit ( Exit exit )
	// {
	// 	if (m_Exits.contains (exit))
	// 	{
	// 		m_Exits.removeElement (exit);
	// 	}
	// }
  //
  //
  //
  // public static List<Exit> all() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM exits where locationId=:locationId";
  //     return con.createQuery(sql)
  //       .addParameter("id", id)
  //       .throwOnMappingFailure(false)
  //       .executeAndFetch(Exit.class);
  //   }
  // }
  //
  //
  //
	// // Assigns location title
	// public void setTitle( String roomTitle )
	// {
	// 	roomTitle = roomTitle;
	// }
  //
  //
  //
	// // Assigns location description
	// public void setDescription( String roomDescription )
	// {
	// 	roomDescription = roomDescription;
	// }
} // end of location class
