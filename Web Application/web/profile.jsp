<%-- 
    Document   : profile
    Created on : 16-Nov-2017, 00:43:21
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        Cek token expired dulu <br>
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
        <h3>MY PROFILE</h3>
        Query ke identitydb
        
        <br>
        <br>
        
        <br>
        <%
            if ((Boolean) session.getAttribute("isDriver")) {
              out.print("Preffered Location<br>");
              out.print(">>  Query ke preffered location webservicedb");
            }
        %>
        
        
    </body>
</html>
