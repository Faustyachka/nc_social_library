<%-- 
    Document   : globallib
    Created on : 25/12/2013, 11:04:44
    Author     : Антон
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8" import="search.*"%>--%>
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
        <jsp:useBean class="ActionsImpl.LibraryActionsImpl" id="book" scope="application"/>
		<table cellspacing="5"><tr><td><font size="20">Global library</font></td></tr>
                    <tr><td><a href="locallib.jsp"> Local library </a></td></tr></table>
		<br/>
		<form name="form1" method="post" action="SearchInGloballib"><br />
			<input type="text" name="text" width="40"></text>
			<input name="search" type="submit" value="Search"/>
		</form>
		<table border=1>
                    <%
                    long i=1;
                    try{
                        i = Long.parseLong(request.getParameter("i"));
                    }catch(Exception e){i=1;}
                        LibraryActions lib = new LibraryActionsImpl();
                        List<Library> books = lib.getBooksByIdInInterval(10*(i-1), 10*i);
                        out.println(books.size());

                        for(Library bookEntity : books){
                    %>
                    <tr>
			<td>
			<center>
			<%=bookEntity.getCover()%>
			<form name="form2" method="post" action="RateBookServlet"><br />
                		<input type="text" name="rate" style="width:25px;" value="
                                       <%
                                       out.print(lib.getAverageRate(bookEntity.getId()));
                                       %>"><br/>
				<input type="submit" name="search" value="Change rating"/>
                                <input type="hidden" name="book_id" value="<%=bookEntity.getId()%>"/>
                                <input type="hidden" name="user_id" value="1"/>
			</form>
			</center>
			</td>
			<td>
                            <%for(Author ba : lib.getAuthorsList(bookEntity.getId())){%>
                            <%= ba.getAuthor()%><%}%><br/>
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
                <center>
                <%
                long i_k = (i>5)?i-4:i;
                for(long k = i_k; k<i_k+10; k++){%>
                <a href="?i=<%=k%>"><%=k%></a>
                <%}%>
                </center>
	</body>
</html>
