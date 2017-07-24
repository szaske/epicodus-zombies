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
	private String m_Name;
	private String m_roomDescription;
	//private List<Exit> m_Exits;

	// Full constructor
	public Location( String name, String description )
	{
		// Assign title + description
		m_Name = name;
		m_roomDescription = description;
	}

  //   GETTERS /////////////////////////////////////

	public String getName()
	{
		return m_Name;
	}

  // Returns location description
	public String getDescription()
	{
		return m_roomDescription;
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
	// 	m_Name = roomTitle;
	// }
  //
  //
  //
	// // Assigns location description
	// public void setDescription( String roomDescription )
	// {
	// 	m_roomDescription = roomDescription;
	// }
} // end of location class
