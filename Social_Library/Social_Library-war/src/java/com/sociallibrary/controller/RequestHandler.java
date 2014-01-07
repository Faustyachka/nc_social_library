
package com.sociallibrary.controller;

import com.sociallibrary.commands.DashboardCommand;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
//import com.sociallibrary.authorization.SignIn;
import com.sociallibrary.registration.ConfirmUser;
import com.sociallibrary.registration.Registration;


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
///Associates a key-object, which executes
private RequestHandler(){
    commands.put("rating", new ScoreCommand());
    commands.put("nocommand", new NoCommand());
    commands.put("registration", new Registration ());
    commands.put("confirmUser", new ConfirmUser () );
    commands.put("signin", new SignIn());
    commands.put("addtolocal", new AddToLocal());
    commands.put("removefromlocal", new RemoveBookFromCatalog());
    commands.put("searchinglobal", new SearchInGlobalLib());
    commands.put("searchinlocal", new SearchInLocalLib());
    commands.put("dashboard", new DashboardCommand ());
}

public Command getCommand(HttpServletRequest request){
     String action = request.getParameter("command");

     Command command = commands.get(action.toLowerCase());
      if(command==null){

          command =new NoCommand();
      }

    return command;


}


}
