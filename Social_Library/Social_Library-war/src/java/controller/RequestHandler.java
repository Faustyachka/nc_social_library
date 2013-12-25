
package Controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import model.registration.Registration;


public class RequestHandler {
    
    private static RequestHandler instance=null;
    HashMap <String, Command> commands = new HashMap<String,Command>();
    
public static RequestHandler getInstance(){
    
    if(instance==null){
        instance= new RequestHandler();
        return instance;
    }
    else{
        return instance;
    }
        
    }
private RequestHandler(){
    commands.put("rating", new ScoreCommand());
    commands.put("nocommand", new NoCommand());
    commands.put("registration ", new Registration ());
    
}

public Command getCommand(HttpServletRequest request){
     String action = request.getParameter("command");
     
     Command command = commands.get(action);
      if(command==null){
          
          command =new NoCommand();
      }
    
    return command;
    
    
}
    
    
}
