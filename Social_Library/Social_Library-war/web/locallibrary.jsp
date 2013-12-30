<%-- 
    Document   : locallibrary
    Created on : 30 груд 2013, 3:35:21
    Author     : mazafaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="TransferObject.Catalog"%>
<%@page import="TransferObject.Library"%>
<%@page import="OracleDAO.OracleCatalogDAO"%>
<%@page import="TransferObjectInterface.CatalogDAO"%>
<%@page import="TransferObject.Users" %>
<%@page import="java.util.Collection;" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library</title>
    </head>
    <body>
        <%
          Library library=new Library();
          //LibraryDAO dao=new OracleLibraryDAO();
          Users user=new Users();
          Catalog book=new Catalog();
        %>

        <table border="1">
        <tbody>
        <tr>
        <th>Id:</th>
        <th>ISBN:</th>
        <th>Title:</th>
        <th>Cover:</th>
        <th>Description:</th>
        <th>Pages:</th>
        </tr>
        <tr>
            <%
              Collection<Catalog> books = user.getCatalogCollection();
              while(book.getId()!=null)
              {
              for(Catalog catalog : books)
            %>
        <td><input type="text" name="id" readonly="readonly" value="<%=library.getId()%>"></td>
        <td><input type="text" name="isbn" readonly="readonly" value="<%=library.getIsbn()%>"></td>
        <td><input type="text" name="title" readonly="readonly" value="<%=library.getTitle()%>"></td>
        <td><input type="text" name="cover" readonly="readonly" value="<%=library.getCover()%>"></td>
        <td><input type="text" name="description" readonly="readonly" value="<%=library.getDescription()%>"></td>
        <td><input type="text" name="description" readonly="readonly" value="<%=library.getPages()%>"></td>
            <%
              }
            %>
        </tr>
        </tbody>
        </table>
    </body>
</html>
