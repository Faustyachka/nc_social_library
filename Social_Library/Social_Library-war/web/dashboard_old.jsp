<%-- 
    Document   : dashboard
    Created on : 06.01.2014, 22:06:41
    Author     : Pavel
--%>
<%@page import="java.util.List"%>
<%@page import="com.sociallibrary.entity.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="form" action="Controller" method="POST">
            <input type="hidden" name="command" value="rejectapprove" />
            <input type="submit" value="Change Status" />
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>TITLE</th>
                    <th>DISCRIPTION</th>
                    <th>PAGES</th>
                    <th>Reject</th>
                    <th>Approve</th>
                </tr>
            </thead>
            <tbody>
                <% List<Library> lib= (List<Library>)request.getAttribute("inprogress");
                int i=0;
                for(Library temp: lib)
                {

                %>
                <tr>
                    <td><%out.print(temp.getId()); %></td>
                    <td><%out.print(temp.getIsbn() ); %></td>
                    <td><%out.print(temp.getTitle()); %></td>
                    <td><%out.print(temp.getDescription()); %></td>
                    <td><%out.print(temp.getPages()); %></td>
                    <td><input type="radio" name="<% out.print(i);%>" value="<% out.print(temp.getId());%>" /></td>
                     <td><input type="radio" name="<% out.print(i++);%>" value="<% out.print(temp.getId());%>" /></td>
                </tr>
                <%}%>
            </tbody>
        </table>
</form>
    </body>
</html>
