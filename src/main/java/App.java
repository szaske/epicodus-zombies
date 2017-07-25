import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location startLocation = Location.find(1);
      model.put("location", startLocation);
      model.put("template", "templates/start.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/go", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int fromLocId = Integer.parseInt(request.queryParams("locationId"));
      String command = request.queryParams("command");
      int newLocation = Exit.leadsTo(fromLocId, command);
      Location location = Location.find(newLocation);
      model.put("location", location);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  } //end of main
} //end of class
