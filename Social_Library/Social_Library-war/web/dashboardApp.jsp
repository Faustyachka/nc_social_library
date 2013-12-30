
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ActionsImpl.*"%>
<%@page import="ActionsInterfaces.*"%>
<%@page import="TransferObject.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
	<body>
	<table cellspacing="5"><tr><td><font size="20">Dashboard</font></td></tr>
            <tr><td><a href="globallib.jsp?id=<%=request.getParameter("id")%>">Global library </a></td></tr>
            <tr><td><a href="localllib.jsp?id=<%=request.getParameter("id")%>">Local library </a></td></tr>
            <tr><td><a href="dashboard.jsp?id=<%=request.getParameter("id")%>">Dashboard Publish </a></td></tr>
        </table>

	<br />
     		<table border=1>
                    <%
                        LibraryActions lib = new LibraryActionsImpl();
                        List<Library> books = lib.searchBooksByParameter("workflow", "3");
                        out.println(books.size());

                        for(Library bookEntity : books){
                    %>
                    <tr>
			<td>
			<center>
			<%=bookEntity.getCover()%>

			</center>
			</td>
			<td>
                            <%for(Author ba : lib.getAuthorsList(bookEntity.getId())){%>
                            <%= ba.getAuthor()%><%}%><br/>
			<%=bookEntity.getTitle()%><br/>
			<%=bookEntity.getDescription()%><br/>
			<br/>
			<form name="form3" method="post" command="publish"><br/>
                            <input type="submit" name="approve" value="Approve!">
                            <input type="submit" name="regect" value="Reject!">
                            <input type="hidden" name="book_id" value="<%=bookEntity.getId() %>"/>
			</form>
			</td>
                    </tr>
                    <%
                        }
                    %>
		</table>
	<form name="form1" method="post" action="SearchInLocalLib"><br />
            <input type="text" name="text" width="40"></text>
            <input type="submit" name="search" value="Search"/>
	</form>