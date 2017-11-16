<%-- 
    Document   : order3
    Created on : 16-Nov-2017, 00:46:14
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="validateToken.jsp" %>
        hi,
        <b><%
            out.print(session.getAttribute("username"));  
        %></b>
        <br>
        <a href="logout.jsp">Logout</a>
        <br>
        <br>
        
        <a href="order1.jsp">ORDER</a>        
        <br>
        <a href="history_user.jsp">HISTORY</a>
        <br>
        <a href="profile.jsp">MY PROFILE</a>
        <br>
        
        <br>
        <h3>Complete Your Order</h3>
    </body>
</html>
