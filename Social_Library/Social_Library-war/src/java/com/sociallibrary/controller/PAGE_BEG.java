/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.controller;

/**
 *
 * @author Pavel
 */
class PAGE_BEG extends Page{

     private  static final  String NOCOMMAND_PAGE ="/beg_nocommand.jsp";
     private  static final  String Mylibary_PAGE ="/beg_mylibrary.jsp";

    public PAGE_BEG() {
    }

    @Override
    public String get_ERROR_PAGE() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get_NOCOMMAND_PAGE() {
        return PAGE_BEG.NOCOMMAND_PAGE;
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
        return PAGE_BEG.Mylibary_PAGE;
    }

}