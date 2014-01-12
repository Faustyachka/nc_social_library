

<%--
    Document   : template
    Created on : 03.01.2014, 22:59:34
    Author     : Pavel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="signin.css" rel="stylesheet">
        <title>TEMPLATE Page</title>
    </head>
    <body>
        <div class="container" >



            <!-- Static navbar -->
            <div class="navbar navbar-default" role="navigation">
                <div class="navbar-header">

                    <a class="navbar-brand" >Social Libary</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Sign In</a></li>
                        <li><a href="Controller?command=nocommand">Registration</a></li>
                        <li><a href="Controller?command=nocommand">Add</a></li>


                    </ul>
                     <ul class="nav navbar-nav navbar-right">
                        <li >  <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                                <button type="submit" class="btn btn-primary" class="btn btn-default">Search</button>
                            </form></li>
                    </ul>


                </div><!--/.nav-collapse -->
            </div>
            <%
                session.setAttribute("role", 4);
            %>

            

                <form class="form-signin" role="form"  action="Controller" method="POST" style="margin-top: 200px;">

                    <input type="hidden" name="command" value="signin"/>
                    <input type="text" name="login" class="form-control" placeholder="Login" required autofocus>
                    <input type="password"  name="password" class="form-control" placeholder="Password" required>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>

            

        </div>
    </body>
</html>
