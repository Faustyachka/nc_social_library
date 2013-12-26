<%-- 
    Document   : index
    Created on : 24.12.2013, 19:46:47
    Author     : Костя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </body>
</html>
