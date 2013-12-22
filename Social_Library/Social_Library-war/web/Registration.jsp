<%-- 
    Document   : Registration
    Created on : Dec 22, 2013, 2:58:54 PM
    Author     : Felix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <form method="POST" action="RegisterServlet">
            Login:
            <input name="login">
            <br>
            First Name:
            <input name="firstName">
            <br>
            Last Name:
            <input name="lastName">
            <br>
            E-mail:
            <input name="email">
            <br>
            Password:
            <input name="password">
            <br>
            Confirm pass:
            <input name="passwordConfirm">
            <br>
            <input type=submit value="Sign Up">
        </form>
    </body>
</html>
