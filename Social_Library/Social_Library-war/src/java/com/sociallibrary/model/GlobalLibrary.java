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
 * @author Антон
 */
public class GlobalLibrary {

        public List<Library> getPublished(){

         LibraryActions lib = new LibraryActions();

        List<Library> libs= lib.searchBooksByParameter(LibraryActions.workflow, LibraryActions.workflowInprogres);


        return libs ;

    }

}
