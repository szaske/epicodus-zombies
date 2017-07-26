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
      int fromLocId = Integer.parseInt(request.queryParams("locationId"));
      String command = request.queryParams("command").toUpperCase();

			int newLocation = Exit.leadsTo(fromLocId, command);
			Location location = Location.find(newLocation);


      //Check to see if the command is a proper direction

      if(Location.find(fromLocId).getExitNames().contains(command)){
        model.put("location", location);
      } else {
				// stay in the same location
        Location oldLocation = Location.find(fromLocId);
        model.put("location", oldLocation);
      }
      model.put("pictureURL", "img/" + Integer.toString(Exit.leadsTo(fromLocId, command)) + ".jpg");
      model.put("template", "templates/index.vtl");
			Zombie.moveZombies();

			List<Exit> exits = location.getExits();
			String zombieSounds = "";

			for (Exit exit:exits) {
				if(Zombie.checkZombieLocation(exit.getLeadsToLocationId())) {
					System.out.println("A zombie to the" + exit.getDirection());
					zombieSounds+= "<br>You hear gurgles to the " + exit.getDirection();
				}
			}
			model.put("zombieSounds", zombieSounds);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  } //end of main
} //end of class
