<%-- 
    Document   : index
    Created on : 24.12.2013, 19:46:47
    Author     : Костя
--%>

<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/ourstyle.css">
        <title>TEMPLATE Page</title>
    </head>
    <body>
        <h1>Social Library v0.1 pre alpha</h1>
        <h2>Sign in</h2>
        <form name="SignIn" action="Servlet" method="POST">
            <input type="hidden" name="command" value="signin"/>
            <table border="0">
                <tbody>
                    <tr>
                        <td><input type="text" name="login" style="color: #777;"
                                   value="Login" onfocus="if (this.value == 'Login')

        <div id="main">
            <div id="header">

                <div id="menu">
                    <ul>
                        <li><a href="Controller?command=dashboard" >
                                <span class="refLabelText">Dashboard</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand">
                                <span class="refLabelText">Add</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand">
                                <span class="refLabelText">Search</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand" >
                                <span class="refLabelText">My library</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand" >
                                <span class="refLabelText">Sign out</span>
                            </a></li>
                    </ul>

                </div>



            </div>

            <div id="center">
                [Center]
            </div>
            <div id="left">
                        <h2>Sign in</h2>
        <form name="SignIn" action="Controller" method="POST">
            <input type="hidden" name="command" value="signin"/>
            <table border="0">
                <tbody>
                    <tr>
                        <td><input type="text" name="login" style="color: #777;"
                                   value="Login" onfocus="if (this.value == 'Login')
                                       {this.value = ''; this.style.color = '#000';}" onblur="
                                       if (this.value == '') {this.value = 'Login';
                                           this.style.color = '#777';}" /></td>
                       </tr>
                    <tr>
                        <td><input type="password" name="password" style="color: #777;"
                                   value="Password" onfocus="if (this.value == 'Password')
                                       {this.value = ''; this.style.color = '#000';}" onblur="
                                       if (this.value == '') {this.value = 'Password';
                                           this.style.color = '#777';}" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="Submit" value="Sign In!"/>
        </form>
        <a href="Registration.jsp">Registration</a>
        <br>
                    </tr>
                </tbody>
            </table>
            <input type="Submit" value="Sign In!"/>
        </form>
            </div>

            <div id="footer">
                <hr>
                <p id="footerText">[ footer text ]</p>
            </div>
        </div>




    </body>
</html>
