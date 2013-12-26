/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ResourceBundle;

/**
 *
 * @author П
 */
public class ConfigurationManager {
    
    private static ConfigurationManager instance=null;
    private ResourceBundle resourcebundle=null;
    //This is all page for forward after action is completed
    public static final String SCORE_PAGE ="/rating.jsp";
    public static final String ERROR_PAGE="/error_page.jsp";
    public static final String USER_PAGE="/user.jsp";
    public static final String REGISTR_PAGE="/registration.jsp";
    public static final String INDEX_PAGE="/index.jsp";
    
//    
//    public static ConfigurationManager getInstance(){
//        
//        if(instance==null){
//           instance=new  ConfigurationManager ();
//          instance.resourcebundle=ResourceBundle.getBundle("config");
//        return instance;
//        }
//        return instance;   
//          
//    }
//    
//public  String getProperty(String key){
//    
//    
//    return (String) this.resourcebundle.getObject(key);
//}
    
    
    
    
}
