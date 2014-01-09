/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.controller;

/**
 *
 * @author Page
 */
class PAGE_ADM extends Page {


     private  static final  String NOCOMMAND_PAGE ="/adm_nocommand.jsp";

    public PAGE_ADM() {
    }

    @Override
    public String get_ERROR_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get_NOCOMMAND_PAGE() {
       return PAGE_ADM.NOCOMMAND_PAGE;
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
