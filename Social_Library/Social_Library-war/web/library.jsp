<%-- 
    Document   : library
    Created on : 29 груд 2013, 23:08:08
    Author     : mazafaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="TransferObject.Library"%>
<%@page import="OracleDAO.OracleLibraryDAO"%>
<%@page import="TransferObjectInterface.LibraryDAO"%>
<%@page import="TransferObject.BookWorkflow" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library</title>
    </head>
    <body>
        <%
          Library library=new Library();
          BookWorkflow bookworkflow=new BookWorkflow();
          LibraryDAO dao=new OracleLibraryDAO();
         // List<Library> lList = library.searchLibraryByParameter(library.setWorkflow(bookworkflow.setWorkflow("published")));
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
            <% //for(Library library1 : lList)
            %>
        <td><input type="text" name="id" readonly="readonly" value="<%=library.getId()%>"></td>
        <td><input type="text" name="isbn" readonly="readonly" value="<%=library.getIsbn()%>"></td>
        <td><input type="text" name="title" readonly="readonly" value="<%=library.getTitle()%>"></td>
        <td><input type="text" name="cover" readonly="readonly" value="<%=library.getCover()%>"></td>
        <td><input type="text" name="description" readonly="readonly" value="<%=library.getDescription()%>"></td>
        <td><input type="text" name="description" readonly="readonly" value="<%=library.getPages()%>"></td>
        </tr>
        </tbody>
        </table>
    </body>
</html>
