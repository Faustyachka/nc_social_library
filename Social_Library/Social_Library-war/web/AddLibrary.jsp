<%-- 
    Document   : AddLibrary
    Created on : 17 січ 2014, 13:57:30
    Author     : mazafaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.google.api.client.googleapis.javanet.GoogleNetHttpTransport "%>
<%@page import="com.google.api.client.json.JsonFactory"%>
<%@page import="com.google.api.client.json.jackson2.JacksonFactory"%>
<%@page import="com.google.api.services.books.Books"%>
<%@page import="com.google.api.services.books.BooksRequestInitializer"%>
<%@page import="com.google.api.services.books.Books.Volumes.List"%>
<%@page import="com.google.api.services.books.model.Volume"%>
<%@page import=" com.google.api.services.books.model.Volumes"%>
<%@page import="java.io.IOException"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <div id="content">
              Maybe you meaned:
              <br>
              <script>
              function handleResponse(response) {
              for (var i = 0; i < response.items.length; i++) {
              var item = response.items[i];
              document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title ;
              }
              }
              </script>
              <script src="https://www.googleapis.com/books/v1/volumes?q=<%=request.getParameter("title")%>&callback=handleResponse"></script>
          </div>
    

        <form action="AddLibrary.jsp" method="POST">
            <p>Title||Author||ISBN:
            <input type="text" name="title"/>
            </p>
            <button type="submit">Search</button>
            </form>
        <table border="1" align="center">
        <tbody>
        <tr>
        <th>Title:</th>
        <th>ISBN_10:</th>
        <th>ISBN_13:</th>
        <th>Pages:</th>
        <th>Genre</th>
        <th>Cover:</th>
        <th>Author</th>
        <th>Description:</th>
        </tr>
        <tr>
    <%
       String APPLICATION_NAME = "Social_Library";
       String API_KEY ="AIzaSyCZsI9e4CfhOOOKQrBXaYB3OkXdu_Qq-3Q";

       JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
       String prefix = null;
       String query = "--title:"+" "+request.getParameter("title");
       if ("--title".equals(query)) 
        {
          prefix = "intitle:";
        }
        else if ("--author".equals(query))
        {
          prefix = "inauthor:";
        }
       else if ("--isbn".equals(query))
        {
          prefix = "isbn:";
        }
        if (prefix != null)
        {
        query = prefix + query;
        }
        try
        {
        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
        .setApplicationName(APPLICATION_NAME)
        .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
        .build();

        List volumesList = books.volumes().list(query);
       // volumesList.setFilter("books");

        // Execute the query.
        Volumes volumes = volumesList.execute();
       
        for (Volume volume : volumes.getItems())
        {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            %>
            <td><%=volumeInfo.getTitle() %></td>
            <td><%=volume.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier() %></td>
            <td><%=volume.getVolumeInfo().getIndustryIdentifiers().get(1).getIdentifier() %></td>
            <td><%=volumeInfo.getPageCount() %></td>
            <td><%=volumeInfo.getCategories() %></td>
            <td><img src="http://bks3.books.google.com/books?id=<%=volume.getId()%>&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api/SmallThumbnail.jpg%>"/></td>
            <td>
            <%
            java.util.List<String> authors = volumeInfo.getAuthors();
            if (authors != null && !authors.isEmpty())
            {
                for (int i = 0; i < authors.size(); ++i)
                {
                    %>
                    <%=authors.get(i)%>
                    <%
                }
            }
            if (volumeInfo.getDescription() != null && volumeInfo.getDescription().length() > 0)
            {
                %>
                </td><td><%=volumeInfo.getDescription() %></td>
                </tr>
                <%
                
            }
            volumeInfo=null;
        return;

        }
        volumesList=null;
        volumes=null;
        
        }
        catch (IOException e)
        {
        System.err.println(e.getMessage());
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    %>
        
    </tbody>
        </table>
        
    </body>
</html>
