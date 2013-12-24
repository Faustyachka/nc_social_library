<%--
    Document   : register
    Created on : 24 груд 2013, 14:29:17
    Author     : Felix
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
        <form method="POST" action="NewServlet">
            Fist Name:
            <input name="firsName">
            <br>
            Last Name:
            <input name="lastName">
            <br>
            Email:
            <input name="email">
            <br>
            Login:
            <input name="login">
            <br>
            Password:
            <input name="password">
            <br>
            <input type="Submit">
        </form>
    </body>
</html>
