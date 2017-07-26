import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class App {

  // String codes
	public static final String[] authorizedCommands =
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


  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

		get("/death", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/death.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location startLocation = Location.find(1);
      model.put("pictureURL", "img/" + Integer.toString(startLocation.getId()) + ".jpg");
      model.put("location", startLocation);
      model.put("template", "templates/index.vtl");
			Zombie.initZombies();
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/go", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
			//get info from th post
      int fromLocId = Integer.parseInt(request.queryParams("locationId"));
      String command = request.queryParams("command").toUpperCase();

			//instaniate new items
			String zombieSounds = "";
			Location location;

      //Check to see if the command is a proper direction
      if(Location.find(fromLocId).getExitNames().contains(command)){
				// change location to the new location
				location = Location.find(Exit.leadsTo(fromLocId, command));
      } else {
				// bad command lets stay in same location
				location = Location.find(fromLocId);
      }

			Zombie.moveZombies();

			System.out.println("You're in location: " + location.getId());

			//check for death
			for (Zombie zombie : Zombie.all()){
				if(zombie.getLocation()==location.getId()){
					response.redirect("/death");
				}
			}

			//check for Zombie sounds code
			List<Exit> exits = location.getExits();

			for (Exit exit:exits) {
				if(Zombie.checkZombieLocation(exit.getLeadsToLocationId())) {
					System.out.println("A zombie to the" + exit.getDirection());
					zombieSounds+= "<br>You hear gurgles to the " + exit.getDirection();
				}
			}

			model.put("pictureURL", "img/" + location.getId() + ".jpg");
			model.put("location", location);
			model.put("zombieSounds", zombieSounds);
			model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  } //end of main
} //end of class
