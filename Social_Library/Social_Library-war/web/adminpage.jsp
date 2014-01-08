<%-- 
    Document   : adminpage
    Created on : 8/1/2014, 4:58:11
    Author     : Антон
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="com.sociallibrary.entity.*" %>
<%@page import="com.sociallibrary.icrud.*"%>
<%@page import="com.sociallibrary.crud.*"%>
<%@page import="com.sociallibrary.iactions.*" %>
<%@page import="com.sociallibrary.actions.*" %>
<%@page import="com.sociallibrary.model.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" type="text/css" rel="stylesheet" />

        <title>Global Library</title>
    </head>
    <body>

        <div id="container">

            <div id="header" align="center"><h1>Global Library</h1></div>


<div id="wrapper">

<div id="content">
    <%
        User current_user = (User)request.getSession().getAttribute("user");
        AdminPage bizLog = new AdminPage();
        List<User> users = bizLog.getAllUsers();
/*        boolean isSearch = Boolean.valueOf(request.getParameter("search"));
        if(isSearch)
            users = bizLog.getSearch_users_result();
        else
            users = bizLog.getAllUsers();
*/
        for(User user : users){
    %>
    <table style="width:500px;" cellpadding="0" cellspacing="0" border="1" align="center">
        <tr><td width="70%">
                <table style="margin:10px;" width="90%" cellpadding="0" cellspacing="0" border="0" align="center">
                <tr bgcolor="#ffffd0">
                    <td>First name:&nbsp;</td>
                    <td><%=user.getFirstName()%></td>
                </tr>
                <tr>
                    <td>Last name:&nbsp;</td>
                    <td><%=user.getLastName()%></td>
                </tr>
                <tr bgcolor="#ffffd0">
                    <td>E-mail:&nbsp;</td>
                    <td><%=user.getEmail()%></td>
                </tr>
                <tr>
                    <td>Login:&nbsp;</td>
                    <td><%=user.getLogin()%></td>
                </tr>
                <tr bgcolor="#ffffd0">
                    <td>Gender:&nbsp;</td>
                    <td><%=user.getFirstName()%></td>
                </tr>
                <tr>
                    <td>Registration date:&nbsp;</td>
                    <td><%=user.getRegistrationDate()%></td>
                </tr>
                <tr bgcolor="#ffffd0">
                    <td>Confirmed:&nbsp;</td>
                    <td><%=user.isConfirmed()?"yes":"no"%></td>
                </tr>
                <tr>
                    <td>Banned:&nbsp;</td>
                    <td><%=user.isBanned()?"yes":"no"%></td>
                </tr>
                <tr bgcolor="#ffffd0">
                    <td>Notify:&nbsp;</td>
                    <td><%=user.isNotify()?"yes":"no"%></td>
                </tr>
            </table>
        </td>
        <td style="padding:10px;" width="">
            <form name="assignNewRole" method="post" action="Controller">
                <input type="hidden" name="user_id" value="<%=user.getId()%>"/><br/>
                <input type="hidden" name="command" value="assignrole"/><br/>
                <input name="administrator" type="checkbox" value="<%=bizLog.isAdmin(user)?"1":"0"%>" <%=bizLog.isAdmin(user)?"checked":""%> onclick="this.checked=<%=bizLog.isAdmin(user)?"true":"false"%>"/>Administrator<br/>
                <input name="moderator" type="checkbox" value="<%=bizLog.isModerator(user)?"1":"0"%>" <%=bizLog.isModerator(user)?"checked":""%> onclick="this.value=this.checked?1:0"/>Moderator<br/>
                <input name="advancedUser" type="checkbox" value="<%=bizLog.isAdvancedUser(user)?"1":"0"%>" <%=bizLog.isAdvancedUser(user)?"checked":""%> onclick="this.value=this.checked?1:0"/>Advanced user<br/>
                <input name="beginnerUser" type="checkbox" value="<%=bizLog.isBeginnerUser(user)?"1":"0"%>" <%=bizLog.isBeginnerUser(user)?"checked":""%> onclick="this.value=this.checked?1:0"/>Beginner user<br/>
                <input align="right" type="submit" value="Assign roles"/>
            </form>
        </td></tr>
    </table>
    <%
        }
    %>
</div>

<div id="rightblock">
    <p>
       <form name="form1" method="post" action="Controller">
        <input type="text" name="search_users" value="">
        <input type="hidden" name="command" value="searchusers"/>
        <input type="submit" value="Search">
       </form>
    </p>
</div>

<div id="footer"><p>Blue One</p></div>

</div>


    </body>
</html>
