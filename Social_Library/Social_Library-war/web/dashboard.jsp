<%@page import="java.util.List"%>
<%@page import="com.sociallibrary.entity.*"%>


<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">


                <table   class="table table-hover">
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
