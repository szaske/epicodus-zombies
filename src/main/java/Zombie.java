import org.sql2o.*;
import java.util.*;



// Zombie - represents a in game Zombie

public class Zombie
{
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
			String sql = "UPDATE zombies SET location=5 WHERE id=1";
      con.createQuery(sql)
        .executeUpdate();
			String sql2 = "UPDATE zombies SET location=17 WHERE id=2";
      con.createQuery(sql2)
        .executeUpdate();
			String sql3 = "UPDATE zombies SET location=10 WHERE id=3";
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
			String sql = "UPDATE zombies SET location=5 WHERE id=1";
      con.createQuery(sql)
        .executeUpdate();
			String sql2 = "UPDATE zombies SET location=17 WHERE id=2";
      con.createQuery(sql2)
        .executeUpdate();
			String sql3 = "UPDATE zombies SET location=10 WHERE id=3";
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

	public static boolean checkZombieLocation (int location) {
    try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT (EXISTS (SELECT * FROM zombies WHERE location = :id))";
			return con.createQuery(sql)
			.addParameter("id", location)
			.throwOnMappingFailure(false)
			.executeAndFetchFirst(Boolean.class);
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

  }

	public static List<Zombie> all() {
    String sql = "SELECT * FROM zombies ORDER BY id";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql)
		 		.throwOnMappingFailure(false)
		 		.executeAndFetch(Zombie.class);
    }
  }

} // end of Zombie class
