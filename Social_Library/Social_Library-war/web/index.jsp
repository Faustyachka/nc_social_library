

<%--
    Document   : template
    Created on : 03.01.2014, 22:59:34
    Author     : Pavel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/ourstyle.css">
        <title>TEMPLATE Page</title>
    </head>
    <body>
        <%
        session.setAttribute("role",4);
        %>

        <div id="main">
            <div id="header">

                <div id="menu">
                    <ul>
                        <li><a href="index.jsp" >
                                <span class="refLabelText">Sign In</span>
                            </a></li>
                        <li><a href="Registration.jsp">
                                <span class="refLabelText">Registration</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand">
                                <span class="refLabelText">Add</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand" >
                                <span class="refLabelText">Search</span>
                            </a></li>
                       
                    </ul>

                </div>



            </div>

            <div id="center">
                <h1>Social Library v0.1 pre alpha</h1>
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
                
                <br>

            </div>

            <div id="footer">
                <hr>
                <p id="footerText">[ footer text ]</p>
            </div>
        </div>

    </body>
</html>
