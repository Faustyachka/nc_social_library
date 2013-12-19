<%-- 
    Document   : index
    Created on : Dec 19, 2013, 2:53:27 PM
    Author     : Felix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="dao.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main page</title>
    </head>
    <body>
        <h1>All Role:</h1>
        <jsp:useBean class="dao.MyDAO" id="myDAO" scope="application"/>
        <table border="1">
            <%
                        for (Role r : myDAO.getAllRole()) {
            %>
            <tr>
                <td><%=r.getId()%></td>
                <td><%=r.getName()%></td>
            </tr>
            <%
                        }
                        Role role = myDAO.readRole(61);                     
            %>
            
        </table>
            <h3>Read Role</h3>
            <table>
                
            <td><%=role.getId()%></td>
            <td><%=role.getName()%></td>
            
            </table>
    </body>
</html>

