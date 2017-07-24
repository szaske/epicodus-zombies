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
	
  // Returns a vector of exits
	// public Vector getExits ()
	// {
  //   // Databse code here to get arary (vector) of exits
	// 	return 5;
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
