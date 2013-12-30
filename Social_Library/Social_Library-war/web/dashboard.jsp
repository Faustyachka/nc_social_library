
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
            <tr><td><a href="globallib.jsp?id=<%=request.getParameter("id")%>">Global library </a></td></tr></table>
	<br />
     		<table border=1>
                    <%
                        LibraryActions lib = new LibraryActionsImpl();
                        List<Library> books = lib.searchBooksByParameter("workflow", "1");
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
                            <input type="submit" name="publish" value="Publish">
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
<%--	<table border=1>
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
	</table>--%>
        <%
        out.println("role= "+request.getParameter("role")+"<br>id="+request.getParameter("id"));


        %>
</body>
</html>
