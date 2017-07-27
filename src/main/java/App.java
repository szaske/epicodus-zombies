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
		tempHM.put("GET","GET");
		tempHM.put("GET NOODLES","GET");
		tempHM.put("NOODLES","GET");
		tempHM.put("INV","INVENTORY");
		tempHM.put("INVENTORY","INVENTORY");
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
		"Before you can take another step you feel a cold hand grab you by your wrist. You try to pull away, but the strength of the undead is too strong and you are forced to the ground. The last thing you experience before the darkness takes you is the pain of your scalp being bitten into and a faint voice mumbling about not enough commits…",
		"From out of the blackness, a dark figure stumbles into your path. Your screams are muffled as you are immediately set upon by several cold bodies that begin to claw and chew through your neck and shoulders. As your life flows out of you, there is just enough time to reflect on what brought you to this fate...Java and noodles.",
		"There is a sudden growl from behind you. Before you have a chance to turn, you feel teeth tearing into your right thigh. You immediately drop to your hands and knees, paralyzed on the bloody carpet. As you are slowly dragged back to the corner of Elysia’s office by your ankles, you wonder if you can get an extension on your Friday project…",
		"You try to sneak by a zombie that is eating one of your former classmates. Unfortunately for you, you didn’t put away your phone before class like you were supposed to, and the text notification from your mom gives away your location. The zombie rips into your abdomen, and just before you close your eyes, you see a petite zombie wearing a big cozy sweater, eating your liver.",
		"There is a sharp pain in your right ankle where a zombie has just taken a nibble. You run frantically through the dark, retracing your steps back out to the hallway with the zombie giving chase. You scramble for the bathroom key in your bag as the gurgling draws closer. Too bad you were too cheap to pay the $25 replacement fee for the bathroom key you lost 2 weeks ago.",
	};


  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {

			//initialize the game world
			Item.initNoodles();
			Zombie.initZombies();

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

		get("/elevator-death", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/elevator-death.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
      
		get("/inventory", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			int fromLocId = Integer.parseInt(request.queryParams("locationId"));
			model.put("location", Location.find(fromLocId));
			model.put("template", "templates/inventory.vtl");
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

			Location location = Location.find(fromLocId); //default set

			//check to see if command is a proper command
			if (authorizedCommands.containsKey(command)){
				System.out.println("your command is approved");
				command=authorizedCommands.get(command);
				System.out.println("command is now " + command);

				switch (command){
					case "INV":
					case "INVENTORY":
					if(Item.itemsInInventory().size()==0){
						List<String> arrOfItems = new ArrayList<>();
						arrOfItems.add("nothing");
						model.put("items", arrOfItems);
					}
					else {
						model.put("items", Item.itemsInInventory());
					}
						model.put("template", "templates/inventory.vtl");
						break;
					case "GET":
					case "GET NOODLES":
					case "NOODLES":
						if (fromLocId==16){
							Item.getNoodles();
							System.out.println("you got the noodles");
							model.put("items", Item.itemsInInventory());
							model.put("template", "templates/inventory.vtl");
							location = Location.find(fromLocId); //and stay in location
						}
						else {
							//not in kitchen
							System.out.println("you're not in the kitchen");
							commandError += "You cannot reach the noodles";
							model.put("template", "templates/index.vtl");

						}
						location = Location.find(fromLocId); //and stay in location
						break;
					default:
						model.put("template", "templates/index.vtl");

						//Check to see if the room has an exit in that direction
						if(Location.find(fromLocId).getExitNames().contains(command)){
							location = Location.find(Exit.leadsTo(fromLocId, command));
							break;
						} //end of exit validation (if)
						else {
							location = Location.find(fromLocId); //and stay in location
							break;
						}
				} //end of switch
			} //end of If
			else {
				// bad command lets stay in same location
				location = Location.find(fromLocId);
				if (!command.equals("RETURN")){
					// commandError = "Don't know what the f%#k you want";
					commandError += "Dunno what the f&^k '" + command + "' means.";
				}

				model.put("template", "templates/index.vtl");
      }

			Zombie.moveZombies();

			System.out.println("You're in location: " + location.getId());

			//check for elevator death
			if (location.getId()==19){
				response.redirect("/elevator-death");
			}

			// check for Zombie death
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
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  } //end of main
} //end of class
