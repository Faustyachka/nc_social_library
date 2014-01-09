/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.controller;

/**
 *
 * @author Pavel
 */
public class PAGE_UN  extends Page{

    private static final  String SEARCH_PAGE ="/unr_search.jap";
    private static final  String ADD_PAGE ="/unr_add.jsp";
    private  static final String ERROR_PAGE="/unr_error_page.jsp";
    private  static final  String NOCOMMAND_PAGE ="/unr_nocommand.jsp";
     private  static final  String VIEW_PAGE ="/unr_view.jsp";

    @Override
    public String get_ERROR_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get_NOCOMMAND_PAGE() {
       return this.NOCOMMAND_PAGE;
    }

    @Override
    public String get_SEARCH_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get_ADD_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get_DASHBOARD_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get_MY_LIBRARY_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
