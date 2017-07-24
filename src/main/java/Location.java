import java.util.Vector;
import java.util.Enumeration;

//
//
// Location - represents a gaming location
//
// Last modification date : November 13, 1997
//
public class Location implements java.io.Serializable
{
	// Member variables
	private String roomTitle;
	private String roomDescription;
	private Vector vecExits;

	// Blank constructor
	// public Location()
	// {
	// 	// Blank title + description
	// 	roomTitle = new String ();
	// 	roomDescription = new String();
	// 	vecExits = new Vector();
	// }
	//
	// // Partial constructor
	// public Location( String title )
	// {
	// 	// Assign title
	// 	roomTitle = title;
	//
	// 	// Blank description
	// 	roomDescription = new String();
	//
	// 	// Blank exits
	// 	vecExits = new Vector();
	// }

	// Full constructor
	public Location( String roomTitle, String roomDescription )
	{
		// Assign title + description
		this.roomTitle = roomTitle;
		this.roomDescription = roomDescription;

		// Blank exits
		this.vecExits = new Vector();
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

	// toString method
	public String toString()
	{
		return roomTitle;
	}

	// Adds an exit to this location
	public void addExit ( Exit exit )
	{
		vecExits.addElement (exit);
	}

	// Removes an exit from this location
	public void removeExit ( Exit exit )
	{
		if (vecExits.contains (exit))
		{
			vecExits.removeElement (exit);
		}
	}

	// Returns a vector of exits
	public Vector getExits ()
	{
		// Return a clone, as we don't want an external
		// object to modify our original vector
		return (Vector) vecExits.clone();
	}

	// Returns location title
	public String getTitle()
	{
		return roomTitle;
	}

	// Assigns location title
	public void setTitle( String roomTitle )
	{
		roomTitle = roomTitle;
	}

	// Returns location description
	public String getDescription()
	{
		return roomDescription;
	}

	// Assigns location description
	public void setDescription( String roomDescription )
	{
		roomDescription = roomDescription;
	}
}
