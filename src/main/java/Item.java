import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


//
//
// Item - represents an item in the game
//

public class Item
{
	// Member variables
	private String name;
	private String description;
	private int id;
	private int location;
	private int status;
	private String userId;

	// Constructor
	public Item(String name, String description, int location, int status, String userId )
	{
		this.name = name;
		this.description = description;
		this.location = location;
		this.status = status;
		this.userId = userId;
	}

  //   GETTERS /////////////////////////////////////
	public String getName()
	{
		return this.name;
	}

	public String getDescription()
	{
		return this.description;
	}

	public int getId() {
		return this.id;
	}

	public int getLocation() {
		return this.location;
	}

	public int getStatus() {
		return this.status;
	}

	@Override
	public boolean equals(Object otherItem) {
		if(!(otherItem instanceof Item)) {
			return false;
		} else {
			Item newItem = (Item) otherItem;
			return this.getName().equals(newItem.getName()) &&
						 this.getDescription().equals(newItem.getDescription()) &&
						 this.getStatus()==newItem.getStatus();
		}
	}

	// public void save() {
	// 	try(Connection con = DB.sql2o.open()) {
	// 		String sql = "INSERT INTO items (name, description, location, status, userId) VALUES (:name, :description, :location, :status, :userId)";
	// 		this.id = (int) con.createQuery(sql, true)
	// 			.addParameter("name", this.name)
	// 			.addParameter("description", this.description)
	// 			.addParameter("location", this.location)
	// 			.addParameter("status", this.status)
	// 			.addParameter("userId", this.userId)
	// 			.executeUpdate()
	// 			.getKey();
	// 	}
	// }

	public static Item find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM items where id=:id";
      Item item = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Item.class);
      return item;
    }
  }

	public static void initNoodles() {
    try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE items SET location=16 WHERE id=1";
      con.createQuery(sql)
        .executeUpdate();
			String removeElevator = "UPDATE exits SET locationid=23 WHERE id=43";
			con.createQuery(removeElevator)
				.executeUpdate();
    }
  }

	public static void getNoodles() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE items SET location=0 WHERE id=1";
			con.createQuery(sql)
				.executeUpdate();
			String openElevator = "UPDATE exits SET locationid=18 WHERE id=43";
			con.createQuery(openElevator)
				.executeUpdate();
		}
	}

	// public static boolean checkItemLocation (int location) {
  //   try(Connection con = DB.sql2o.open()) {
	// 		String sql = "SELECT (EXISTS (SELECT * FROM items WHERE location = :id))";
	// 		return con.createQuery(sql)
	// 		.addParameter("id", location)
	// 		.throwOnMappingFailure(false)
	// 		.executeAndFetchFirst(Boolean.class);
	// 	}
	// }
	//
	public static List<String> itemsInInventory() {
    String sql = "SELECT description FROM items where location=0";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql)
		 		.throwOnMappingFailure(false)
		 		.executeAndFetch(String.class);
    }
  }

} // end of location class
