<%-- 
    Document   : newjsp
    Created on : 5 січ 2014, 0:39:48
    Author     : mazafaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="com.sociallibrary.entity.*" %>
<%@page import="com.sociallibrary.icrud.*"%>
<%@page import="com.sociallibrary.crud.*"%>
<%@page import="com.sociallibrary.iactions.*" %>
<%@page import="com.sociallibrary.actions.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" type="text/css" rel="stylesheet" />
       
        <title>Global Library</title>
    </head>
    <body>

        <div id="container">

            <div id="header" align="center"><h1>Global Library</h1></div>


<div id="wrapper">

<div id="content">
    <p>
    <table border="1" align="center">
        <tbody>
        <tr>
        <th>Id:</th>
        <th>ISBN:</th>
        <th>Title:</th>
        <th>Cover:</th>
        <th>Description:</th>
        <th>Pages:</th>
        </tr>
        <%
        int i=0;
        try
                {
        i = Integer.parseInt(request.getParameter("i"));
        }
        catch(NumberFormatException e)
                {
            e.printStackTrace();
            }
        LibraryActions ob=new LibraryActions();
        List<Library> libraries =ob.getAllBooks(10*i, 10*(i+1));
        //List<Library> libraries = ob.getBooksByIdInInterval(10*i, 10*(i+1));
        for(Library book:libraries)
        {
        %>
        <tr>
        <td><%=book.getId()%></td>
        <td><%=book.getIsbn()%></td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getCover()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPages()%></td>
        </tr>
        <%
        }
        %>
        </tbody>
        </table>
</p>
        <center>
        <%
        for(int k = i; k<i+10; k++)
        {
        %>
        <a href="?i=<%=k%>"><%=k%></a>
        <%
        }
        %>
        </center>
</div>

</div>


<div id="leftblock">
    <br>
    <a href="locallib.jsp?id=<%=request.getParameter("id")%>"> Local library </a>
    <br><a href="dashboard.jsp?id=<%=request.getParameter("id")%>">Dashboard Publish</a>
    <br><a href="dashboardApp.jsp?id=<%=request.getParameter("id")%>">Dashboard Approve </a>
    

</div>

<div id="rightblock">
    <p><form name="form1" method="post" action="SearchInGloballib">
        <input type="text" name="text">
	<input name="search" type="button" value="Search">
        </form>
   </p>

</div>

<div id="footer"><p>Blue One</p></div>

</div>
       
        
    </body>
</html>
