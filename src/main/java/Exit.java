import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// Exit - represents an exit to a location
public class Exit
{
	// Member variables
	private int locationId;
	private int leadsTo;
	private String direction;
	private int id;

	// constructor
	public Exit( String direction, int locationId, int leadsTo )
	{
		this.locationId = locationId;
		this.direction = direction;

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

	//currently only used for test database since dev database is updated directly:Michael
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
      String sql = "SELECT leadsto FROM exits where locationId=:locId and direction=:dir";
      int leads = con.createQuery(sql)
				.addParameter("locId", locId)
				.addParameter("dir", direction)
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
