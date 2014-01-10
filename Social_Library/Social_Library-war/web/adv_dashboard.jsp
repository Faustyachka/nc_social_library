<%--
    Document   : nocommand
    Created on : 07.01.2014, 0:27:40
    Author     : Pavel
--%>
<%@page import="java.util.List"%>
<%@page import="com.sociallibrary.entity.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/ourstyle.css">
        <title>TEMPLATE Page</title>
    </head>
    <body>

        <div id="main">
            <div id="header">

                <div id="menu">
                    <ul>
                        <li><a href="Controller?command=dashboard" >
                                <span class="refLabelText">Dashboard</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand">
                                <span class="refLabelText">Add</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand">
                                <span class="refLabelText">Search</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand" >
                                <span class="refLabelText">My library</span>
                            </a></li>
                        <li><a href="index.jsp" >
                                <span class="refLabelText">Sign out</span>
                            </a></li>
                    </ul>

                </div>



            </div>

            <div id="center">

                <p1>DASHBOARD!!!</p1>

                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>ISBN</th>
                            <th>TITLE</th>
                            <th>DISCRIPTION</th>
                            <th>PAGES</th>
                            <th>Reject</th>
                            <th>Publish</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Library> lib = (List<Library>) request.getAttribute("inprogress");
            int i = 0;
            for (Library temp : lib) {

                        %>
                        <tr>
                            <td><%out.print(temp.getId());%></td>
                            <td><%out.print(temp.getIsbn());%></td>
                            <td><%out.print(temp.getTitle());%></td>
                            <td><%out.print(temp.getDescription());%></td>
                            <td><%out.print(temp.getPages());%></td>

                            <td> <form name="form" action="Controller" method="POST">
                                    <input type="hidden" name="command" value="reject" />
                                    <input type="hidden" name="reject" value="<% out.print(temp.getId());%>" />
                                    <input type="submit" value="Reject!" />
                                </form></td>
                            <td> <form name="form" action="Controller" method="POST">
                                    <input type="hidden" name="command" value="publish" />
                                    <input type="hidden" name="publish" value="<% out.print(temp.getId());%>" />
                                    <input type="submit" value="Publish!" />
                                </form></td>


                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>

            <div id="footer">
                <hr>
                <p id="footerText">[ footer text ]</p>
            </div>
        </div>

    </body>
</html>
