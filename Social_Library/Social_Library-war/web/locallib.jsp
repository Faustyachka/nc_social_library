<%-- 
    Document   : locallib
    Created on : 25/12/2013, 11:04:28
    Author     : Антон
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.soclib.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
	<body>
            <jsp:useBean class="com.soclib.BookEntity" id="book" scope="application"/>
	<table cellspacing="5"><tr><td><font size="20">Local library</font></td></tr>
            <tr><td><a href="globallib.jsp"> Global library </a></td></tr></table>
	<br />
	<form name="form1" method="post" action="SearchInLocalLib"><br />
            <input type="text" name="text" width="40"></text>
            <input type="submit" name="search" value="Search"/>
	</form>
	<table border=1>
            <%
                for(BookEntity bookEntity: BookEntity.books){
                       if(bookEntity.isIsInLocallib()){
            %>
        	<tr>
		<td>
		<center>
		<%=bookEntity.getCover()%>
		<form name="form2" method="post" action="RateBookServlet"><br />
			<input type="text" name="rate" style="width:25px;" value="<%=bookEntity.getRate()%>"><br/>
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
                </td>
		</tr>
                <%
                    }
                }
                %>
	</table>
</body>
</html>
