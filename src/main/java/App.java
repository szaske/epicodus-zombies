import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class App {

  // String codes
	public static final Map<String, String> authorizedCommands;
	static {
		Map<String, String> tempHM = new HashMap<String, String>();
		tempHM.put("N","NORTH");
		tempHM.put("NORTH","NORTH");
		tempHM.put("S","SOUTH");
		tempHM.put("SOUTH","SOUTH");
		tempHM.put("W","WEST");
		tempHM.put("WEST","WEST");
		tempHM.put("E","EAST");
		tempHM.put("EAST","EAST");
		authorizedCommands = Collections.unmodifiableMap(tempHM);
	}

	public static final String[] zombieSoundOptions =
	{
		"gurgles",
		"muffled steps",
		"moaning",
		"chewing",
		"growling",
		"a raspy voice whisper 'must...<breathing>commit'"
	};

	public static final String[] deaths =
	{
		"Before you can take another step you feel a cold and slimey hand grab you by the arm. You try to pull away, but the strength of the undead is too strong and you are forced to the ground. The last thing you experience before the darkness takes you is the pain of your neck being bitten into and a faint voice mumbling about not enough commits",
		"From out of the blackness, a dark figure stumbles into your path. Before you can back away the figure grabs you by the ankles and pulls your feet out from under you. You are immeditely set upon by several cold bodies that begin to claw and chew through your skin. As your life flows out of you from your wounds you have just enough time to reflect briefly on what brought you to this fate...Java and noodles.",
		"You pause to glance around you, trying to make your eyes take in more details of what is around you. There is a sudden noise behind you. Before you have a chance to turn, you feel teeth tearing into your throat. There is a spray of arterial blood as a zombie you did not see earlier rips your carotid artery out an begins chewing. You immedialy drop to the ground and feel coldness spread through your body as your blood supply sprays onto the zombie and the ground around you.",
		"You try to sneak by a zombie that is eating one of your former classmates. The slight tremble of the floor caused by your steps triggers an attack. The zombie rips into your abdomen. Before you have a chance to die, you get to see a smallish tatooed zombie eating your liver.",
		"You feel a sharp pain in your right leg. You look down and see that there is a zombie biting into your calf. You feel a cold anger building throughout your being. You kick the zombie in the head and stumble away. You develop a fever and you feel a strange craving...for...brains. Brains are life, nothing else matters.",
	};


  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location startLocation = Location.find(1);
			String commandError = "";
      model.put("pictureURL", "img/" + Integer.toString(startLocation.getId()) + ".jpg");
      model.put("location", startLocation);
			model.put("zombieSounds", "");
      model.put("template", "templates/index.vtl");
			model.put("commandError", commandError);
			Zombie.initZombies();
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

		get("/death", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			Random randomGenerator = new Random();
			Integer choice = randomGenerator.nextInt(deaths.length);
			String death = deaths[choice];
			model.put("death", death);
			model.put("template", "templates/death.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

    post("/go", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
			//get info from th post
      int fromLocId = Integer.parseInt(request.queryParams("locationId"));
      String command = request.queryParams("command").toUpperCase();

			//instaniate new variables
			String zombieSounds = "";
			String commandError = ""; // initialize error message

			Location location = null;

			//check to see if command is a proper command
			if (authorizedCommands.containsKey(command)){
				System.out.println("your command is approved");
				command=authorizedCommands.get(command);
				System.out.println("command is now " + command);

				//Check to see if the command is a proper direction
				if(Location.find(fromLocId).getExitNames().contains(command)){
					// change location to the new location
					location = Location.find(Exit.leadsTo(fromLocId, command));
				}
			}
			else {
				// bad command lets stay in same location
				location = Location.find(fromLocId);
				// commandError = "Don't know what the f%#k you want";
				commandError += "Dunno what the f&^k '" + command + "' means.";
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

					//get a random zombie sound
					Random randomGenerator = new Random();
					zombieSounds+= "<br>You hear "+ zombieSoundOptions[randomGenerator.nextInt(zombieSoundOptions.length)]+ " to the " + exit.getDirection();
				}
			}

			model.put("pictureURL", "img/" + location.getId() + ".jpg");
			model.put("location", location);
			model.put("commandError", commandError);
			model.put("zombieSounds", zombieSounds);
			model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  } //end of main
} //end of class
