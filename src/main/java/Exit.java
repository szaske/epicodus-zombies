import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
//
// Exit - represents an exit to a location
//
// Last modification date : November 13, 1997
//
public class Exit implements java.io.Serializable
{
	// Numerical codes
	public static final int UNDEFINED = 0;
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST  = 3;
	public static final int WEST  = 4;
	public static final int UP    = 5;
	public static final int DOWN  = 6;
	public static final int NORTHEAST = 7;
	public static final int NORTHWEST = 8;
	public static final int SOUTHEAST = 9;
	public static final int SOUTHWEST = 10;
	public static final int IN = 11;
	public static final int OUT = 12;

	// String codes
	public static final String[] dirName =
	{
		"UNDEFINED",
		"NORTH",
		"SOUTH",
		"EAST",
		"WEST",
		"UP",
		"DOWN",
		"NORTHEAST",
		"NORTHWEST",
		"SOUTHEAST",
		"SOUTHWEST",
		"IN",
		"OUT"
	};

	public static final String[] shortDirName =
	{
		"NULL",
		"N",
		"S",
		"E",
		"W",
		"U",
		"D",
		"NE",
		"NW",
		"SE",
		"SW",
		"I",
		"O"
	};

	// Member variables
	private int locationId;
	private int leadsTo;
	private String direction;
	private int id;

	// // Full name of direction eg SOUTHEAST
	// private String directionName;
	//
	// // Shortened version of direction eg SE
	// private String shortDirectionName;

	// Default constructor
	// public Exit()
	// {
	// 	direction = Exit.UNDEFINED;
	// 	leadsTo = null;
	// 	directionName = dirName[UNDEFINED];
	// 	shortDirectionName = shortDirName[UNDEFINED];
	// }

	// Full constructor
	public Exit( String direction, int locationId, int leadsTo )
	{
		this.locationId = locationId;
		this.direction = direction;

		// Assign direction names
		// if (direction <= dirName.length )
		// 	directionName = dirName[direction];
		// if (direction <= shortDirName.length )
		// 	shortDirectionName = shortDirName[direction];

		// Assign location
		this.leadsTo = leadsTo;
	}
	@Override
  public boolean equals(Object otherExit){
    if (!(otherExit instanceof Exit)) {
      return false;
    } else {
      Exit newExit = (Exit) otherExit;
      return this.getDirection().equals(newExit.getDirection()) &&
             this.getLeadsToLocationId() == (newExit.getLeadsToLocationId()) &&
						 this.getLocationId() == (newExit.getLocationId());
    }
  }

	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO exits (direction, locationid, leadsto) VALUES (:direction, :locationid, :leadsto)";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("direction", this.direction)
				.addParameter("locationid", this.locationId)
        .addParameter("leadsto", this.leadsTo)
				.executeUpdate()
				.getKey();
		}
	}

	public static List<Exit> all() {
    String sql = "SELECT * FROM exits";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql)
		 		.throwOnMappingFailure(false)
		 		.executeAndFetch(Exit.class);
    }
  }

	public static Exit find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM exits where id=:id";
      Exit exit = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Exit.class);
      return exit;
    }
  }

	public static int leadsTo(int locId, String direction) {
    try(Connection con = DB.sql2o.open()) {
			int dir = Arrays.asList(dirName).indexOf(direction);
      String sql = "SELECT leadsto FROM exits where locationId=:locId and direction=:dir";
      int leads = con.createQuery(sql)
				.addParameter("locId", locId)
				.addParameter("dir", dir)
        .executeAndFetchFirst(Integer.class);
      return leads;
    }
  }

	public String getDirection()
	{
		return direction;
	}

	public int getLeadsToLocationId (  )
	{
		return leadsTo;
	}

	public int getLocationId (  )
	{
		return locationId;
	}

	public int getId (  )
	{
		return id;
	}
}
