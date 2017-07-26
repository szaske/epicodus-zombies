//import java.util.Vector;
//import java.util.Enumeration;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


//
//
// Zombie - represents a in game Zombie
//

public class Zombie
{
	public static final double ACTIVE = .5;
	// Member variables
	private String name;
	private String description;
	private int id;
	private int location;

	// Full constructor
	public Zombie(String name, String description)
	{
		// Assign title + description
		this.name = name;
		this.description = description;

	}

  //   GETTERS /////////////////////////////////////
	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getId() {
		return id;
	}

	public int getLocation() {
		return location;
	}

	// public void save() {
	// 	try(Connection con = DB.sql2o.open()) {
	// 		String sql = "INSERT INTO locations (roomtitle, roomdescription) VALUES (:roomtitle, :roomdescription)";
	// 		this.id = (int) con.createQuery(sql, true)
	// 			.addParameter("roomtitle", this.roomTitle)
	// 			.addParameter("roomdescription", this.roomDescription)
	// 			.executeUpdate()
	// 			.getKey();
	// 	}
	// }

	public static Zombie find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM zombies where id=:id";
      Zombie zombie = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Zombie.class);
      return zombie;
    }
  }

	public static void initZombies() {
    try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE zombies SET location=3 WHERE id=1";
      con.createQuery(sql)
        .executeUpdate();
			String sql2 = "UPDATE zombies SET location=17 WHERE id=2";
      con.createQuery(sql2)
        .executeUpdate();
			String sql3 = "UPDATE zombies SET location=11 WHERE id=3";
      con.createQuery(sql3)
        .executeUpdate();
    }
  }

	public static List<Integer> getExitOptions (int location) {
    try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT leadsto FROM exits where locationId=:id";
			return con.createQuery(sql)
			.addParameter("id", location)
			.throwOnMappingFailure(false)
			.executeAndFetch(Integer.class);
		}
	}

	public static void countZombies() {
    try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE zombies SET location=3 WHERE id=1";
      con.createQuery(sql)
        .executeUpdate();
			String sql2 = "UPDATE zombies SET location=17 WHERE id=2";
      con.createQuery(sql2)
        .executeUpdate();
			String sql3 = "UPDATE zombies SET location=11 WHERE id=3";
      con.createQuery(sql3)
        .executeUpdate();
    }
  }

	public static void setZombieLocation(int zombieId, int newlocation) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE zombies SET location=:newlocation WHERE id=:id";
			con.createQuery(sql)
				.addParameter("newlocation", newlocation)
				.addParameter("id", zombieId)
				.executeUpdate();
		}
	}

	public static void moveZombies() {

		for(Zombie zombie : Zombie.all()){
			//maybe move the zombies
			List<Integer> options = new ArrayList<Integer>();
			for(Integer direction : Zombie.getExitOptions(zombie.getLocation())){
				options.add(direction);
			}
			options.add(666); //Zombie stays put if this is chosen
			Random randomGenerator = new Random();
			Integer choice = randomGenerator.nextInt(options.size());
			System.out.println("Zombie " + zombie.getName() + "chose option " + options.get(choice));
			if (options.get(choice) != 666){
				Zombie.setZombieLocation(zombie.getId(), options.get(choice));
			}
		}


		//for each zombie
		// get current location
		// make a list of move options
		// get location.getExits
		// add exits to move options List
		// add stay put option to options List
		//randomly pick an option
		// if move then find leadsTo
		// update DB location to leadsTo

  }

	// public static void moveZombie(String name) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE zombies SET name=:name WHERE id=:id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .addParameter("name", name)
  //       .throwOnMappingFailure(false)
  //       .executeUpdate();
  //   }
  // }
	//
	//
	public static List<Zombie> all() {
    String sql = "SELECT * FROM zombies ORDER BY id";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql)
		 		.throwOnMappingFailure(false)
		 		.executeAndFetch(Zombie.class);
    }
  }
	//
	// public List<Exit> getExits () {
  //   try(Connection con = DB.sql2o.open()) {
	// 		String sql = "SELECT * FROM exits where locationId=:id";
	//
	// 		return con.createQuery(sql)
	// 		.addParameter("id", this.id)
	// 		.throwOnMappingFailure(false)
	// 		.executeAndFetch(Exit.class);
	// 	}
	// }
	//
	// public List<String> getExitNames () {
  //   try(Connection con = DB.sql2o.open()) {
	// 		String sql = "SELECT direction FROM exits where locationId=:id";
	//
	// 		return con.createQuery(sql)
	// 		.addParameter("id", this.id)
	// 		.throwOnMappingFailure(false)
	// 		.executeAndFetch(String.class);
	// 	}
	// }

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
