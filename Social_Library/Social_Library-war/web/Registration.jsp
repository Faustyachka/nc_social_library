

<%--
    Document   : template
    Created on : 03.01.2014, 22:59:34
    Author     : Pavel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sociallibrary.constants.*" %>
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
                        <li><a href="Registration.jsp">Registration</a></li>
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
                <form method="POST" action="Controller" name="registration" onSubmit="return formValidation();">
                    <input type="hidden" value="registration" name="command" />
                    <table border="0">

                        <tbody>
                            <tr>
                                <td>            First Name:
                                </td>
                                <td><input name="firstName"></td>
                            </tr>
                            <tr>
                                <td>            Last Name:
                                </td>
                                <td>  <input name="lastName"></td>
                            </tr>
                            <tr>
                                <td>            Email:
                                </td>
                                <td>      <input name="email"></td>
                            </tr>
                            <tr>
                                <td>            Login:
                                </td>
                                <td>  <input name="login"></td>


                            </tr>
                            <tr>
                                <td>            Password:
                                </td>
                                <td><input name="password" type="password"></td>
                            </tr>
                            <tr>
                                <td>
                                    Gender:
                                </td>
                                <td>
                                    <select name="gender">
                                        <option selected="selected" value="1">Male</option>
                                        <option value="0">Female</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Notify about new books:
                                </td>
                                <td>
                                    <select name="notify">
                                        <option selected="selected" value="1">Yes</option>
                                        <option value="0">No</option>
                                    </select>
                                </td>
                            </tr>


                        </tbody>
                    </table>
                    <br>
                    <input type="Submit">
                </form>
                <%--<fb:registration redirect_uri=<%=Const.HOST%>Registration.jsp fields="name,first_name,last_name,email,gender,password" />--%>
                <a href="https://www.facebook.com/dialog/oauth?client_id=<%=Const.FB_APP_ID%>&redirect_uri=<%=Const.HOST%>Controller?command=fbLogin&response_type=code">FB</a>
                <a href="https://accounts.google.com/o/oauth2/auth?redirect_uri=<%=Const.HOST%>tester&response_type=code&client_id=<%=Const.G_APP_ID%>&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile">GOOGLE</a>
                <a href="http://oauth.vk.com/authorize?client_id=4114473&redirect_uri=<%=Const.HOST%>tester&response_type=code" title="vk">VK</a>
            </div>

           
        </div>
    </body>
</html>