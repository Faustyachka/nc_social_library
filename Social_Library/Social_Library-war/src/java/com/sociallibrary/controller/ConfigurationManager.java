/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sociallibrary.controller;

import java.util.ResourceBundle;

/**
 *
 * @author Pavel
 */
public class ConfigurationManager {

    public static final String SCORE_PAGE = "/rating.jsp";
    public static final String ERROR_PAGE = "/error_page.jsp";
    public static final String USER_PAGE = "/user.jsp";
    public static final String REGISTR_PAGE = "/Registration.jsp";
    public static final String INDEX_PAGE = "/index.jsp";
    public static final String LOCAL_LIB = "/locallib.jsp";
    public static final String Dashboard_PAGE = "/dashboard.jsp";
    public static final String NOCOMMAND_PAGE = "/nocommand.jsp";

    static public Page getPageForRole(int role) {
        switch (role) {
            case 0:
                return new PAGE_ADM();
            case 1:
                return new PAGE_MOD();
            case 2:
                return new PAGE_ADV();
            case 3:
                return new PAGE_BEG();
            case 4:
                return new PAGE_UN();
            default:
                return new PAGE_UN();



        }


    }
}
