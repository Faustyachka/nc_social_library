<%-- 
    Document   : error_page
    Created on : Dec 24, 2013, 1:03:22 AM
    Author     : П
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <h1>ERROR</h1>
        <hr/>
        <jsp:expression>
        request.getAttribute("error")!=null?
        request.getAttribute("error"): "unknown"
        </jsp:expression>
        <hr/>
    </body>
</html>
