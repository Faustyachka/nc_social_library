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
            <table border="0">

                <tbody>
                    <tr>
                        <td>            Fist Name:
                        </td>
                        <td><input name="firsName"></td>
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
                </tbody>
            </table>
            <br>
            <input type="Submit">
        </form>
    </body>
</html>
