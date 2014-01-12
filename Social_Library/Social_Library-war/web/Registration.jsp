<%--
    Document   : register
    Created on : 24 груд 2013, 14:29:17
    Author     : Костя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.sociallibrary.constants.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/ourstyle.css">
        <script src="javascripts/validator.js">
        </script>
        <script src="javascripts/facebook.js">
        </script>
        <title>Registration</title>
    </head>
    <body>

        <div id="main">
            <div id="header">

                <div id="menu">
                    <ul>
                        <li><a href="index.jsp" >
                                <span class="refLabelText">Sign In</span>
                            </a></li>
                        <li><a href="Registration.jsp">
                                <span class="refLabelText">Registration</span>
                            </a></li>
                        <li><a href="Controller?command=nocommand">
                                <span class="refLabelText">Add</span>
                            </a></li>
                        <li><a href="Controller?command=nocammand" >
                                <span class="refLabelText">Search</span>
                            </a></li>
                    </ul>

                </div>



            </div>

            <div id="center">
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
                <a href="https://accounts.google.com/o/oauth2/auth?redirect_uri=<%=Const.HOST%>Controller?command=googleLogin&response_type=code&client_id=<%=Const.G_APP_ID%>&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile">GOOGLE</a>
                <a href="http://oauth.vk.com/authorize?client_id=4114473&redirect_uri=<%=Const.HOST%>Controller?command=vkLogin&response_type=code" title="vk">VK</a>
            </div>

            <div id="footer">
                <hr>
                <p id="footerText">[ footer text ]</p>
            </div>
        </div>

    </body>
</html>