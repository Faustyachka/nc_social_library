/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.model;

import com.sociallibrary.actions.LibraryActions;
import com.sociallibrary.entity.Library;
import java.util.List;

/**
 *
 * @author Pavel
 */
public class Dashboard {


    public List<Library> getInProgress(){

        List<Library> libs = 
                new LibraryActions().searchBooksByParameter(LibraryActions.workflow,
                                                                LibraryActions.workflowPublished);

        return libs ;

    }

}
