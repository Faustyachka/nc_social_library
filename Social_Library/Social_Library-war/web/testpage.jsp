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
<%@page import="java.sql.*"%>
<%@page import="TransferObjectInterface.*"%>
<%@page import="OracleConnection.Oracle"%>
<%@page import="OracleDAO.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean class="ActionsImpl.LibraryActionsImpl" id="book" scope="application"/>
        <%
        for(int i = 0; i<500; i++){
        Oracle conn1 = new Oracle();
        Library library = new Library();
       Connection conn=conn1.getConnection();
       UsersDAO u = new OracleUsersDAO();
       BookWorkflowDAO w = new OracleBookWorkflowDAO();
       String selectQuery="SELECT * FROM library WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setLong(1, i);

            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                library.setId(rs.getLong(1));
                library.setIsbn(rs.getString(2));
                library.setTitle(rs.getString(3));
                library.setCover(rs.getString(4));
                library.setDescription(rs.getString(5));
                library.setPages(rs.getInt(6));
                library.setUsers(u.readUsers(rs.getInt(7)));
                library.setWorkflow(w.readBookWorkflow(rs.getInt(8)));
            }
            rs.close();

            out.println(library.getId());
            out.println(library.getTitle());
            out.println(library.getIsbn());
            out.println(library.getPages());
            out.println(library.getDescription());
            out.println(library.getWorkflow().getWorkflow());
            out.println(library.getUsers().getEmail());
            out.println(library.getRatingCollection());
            out.println("<br>");

            conn.close();
       }
        %>

                    <%
                        long i = 0;
                        LibraryActions lib = new LibraryActionsImpl();
                        out.println(i);
                        //List<Library> books = lib.getBooksByIdInInterval(10*i, 10*(i+1));
                        //out.println(books.size());
                    %>
	</body>
</html>
