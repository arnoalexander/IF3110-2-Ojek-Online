<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
    </head>
    <body>
        <%@include file="validateToken.jsp" %>
        <form method="post" action="http://localhost:8080/Identity_Service/UpdateProfile">
        <input type="hidden" name="token" value="<%out.print(session.getAttribute("token"));%>">
        Name:<input type="text" name="name" /><br/>
        Phone:<input type="text" name="phone" /><br/>
        <input type="submit" value="save" />
        </form>
    </body>
</html>
