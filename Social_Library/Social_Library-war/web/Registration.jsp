<%--
    Document   : register
    Created on : 24 груд 2013, 14:29:17
    Author     : Костя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/ourstyle.css">
        <script src="/js/validation.js"/>
        <title>Registration</title>
    </head>
    <body>

        <div id="main">
            <div id="header">

                <div id="menu">
                    <ul>
                        <li><a href="#" id="nowopen">
                                <span class="refLabelText">Sign In</span>
                            </a></li>
                        <li><a href="#">
                                <span class="refLabelText">Search</span>
                            </a></li>
                        <li><a href="#">
                                <span class="refLabelText">Add book</span>
                            </a></li>
                        <li><a href="" >
                                <span class="refLabelText">Regiastration</span>
                            </a></li>

                </div>



            </div>

            <div id="center">
                <form method="POST" action="Servlet">
                    <input type="hidden" value="registration" name="command" />
                    <table border="0">

                        <tbody>
                            <tr>
                                <td>            First Name:
                                </td>
                                <td><input name="firstName"></td>
                            </tr>
                            <tr>
                                <td>            Last Name:
                                </td>
                                <td>  <input name="lastName"></td>
                            </tr>
                            <tr>
                                <td>            Email:
                                </td>
                                <td>      <input name="email"></td>
                            </tr>
                            <tr>
                                <td>            Login:
                                </td>
                                <td>  <input name="login"></td>
                            </tr>
                            <tr>
                                <td>            Password:
                                </td>
                                <td><input name="password" type="password"></td>
                            </tr>
                            <tr>
                                <td>
                                    Gender:
                                </td>
                                <td>
                                    <select name="gender">
                                        <option value="1">Male</option>
                                        <option value="0">Female</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Notify about new books:
                                </td>
                                <td>
                                    <select name="notify">
                                        <option value="1">Yes</option>
                                        <option value="0">No</option>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
                    <input type="Submit">
                </form>
            </div>

            <div id="footer">
                <hr>
                <p id="footerText">[ footer text ]</p>
            </div>
        </div>

    </body>
</html>