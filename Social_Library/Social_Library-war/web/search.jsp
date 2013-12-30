<%--
    Document   : globallib
    Created on : 25/12/2013, 11:04:44
    Author     : Антон
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="ActionsInterfaces.*"%>
<%@page import="ActionsImpl.*"%>
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
        <jsp:useBean class="search.BookEntity" id="book" scope="application"/>
		<table cellspacing="5"><tr><td><a href="globallib.jsp">Global library</a></td></tr>
                    <tr><td><a href="locallib.jsp"> Local library </a></td></tr></table>
		<br/>
		<font size="16">Search results</font>
		<table border=1>
                    <%
                    String text = request.getParameter("text");
                    Library library = new Library();
                    LibraryActionsImpl libraryActionsImpl = new LibraryActionsImpl();
                    List<Library> bookEntitys = new ArrayList<Library>();
                    for(Library bookEntity: bookEntitys){
                        if(bookEntity.getWorkflow().getWorkflow().toLowerCase()!="published") continue;
                    %>
                    <tr>
			<td>
			<center>
			<%=bookEntity.getCover()%>
			<form name="form2" method="post" action="RateBookServlet"><br />
                            <input type="text" name="rate" style="width:25px;" value="<%=libraryActionsImpl.getAverageRate(bookEntity.getId())%>"><br/>
				<input type="submit" name="search" value="Change rating"/>
                                <input type="hidden" name="book_id" value="<%=bookEntity.getId()%>"/>
                                <input type="hidden" name="user_id" value="1"/>
			</form>
			</center>
			</td>
			<td>
			<%=bookEntity.getAuthor()%><br/>
			<%=bookEntity.getTitle()%><br/>
			<%=bookEntity.getDescription()%><br/>
			<br/>
			<form name="form3" method="post" action="AddToLocalLibraryServlet"><br />
				<input type="submit" name="addlocal" value="Add to Local Library"/>
                                <input type="hidden" name="book_id" value="<%=bookEntity.getId()%>"/>
			</form>
			</td>
                    </tr>
                    <%
                        }
                    %>
		</table>
	</body>
</html>
