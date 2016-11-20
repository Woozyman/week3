<%-- 
    Document   : login
    Created on : 20-11-2016, 12:25:15
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Login:</p>
        <form action="Login" method="POST">
            Username: <input type="text" name="username" value="" />
            Password: <input type="text" name="password" value="" />
            <input type="submit" value="Log in" name="submit" />
        </form>
        <br>
        <p>Create User:</p>
        <form action="NewUser" method="POST">
            Username: <input type="text" name="username" value="" />
            Password: <input type="text" name="passwordOne" value="" />
            Repeat password: <input type="text" name="passwordTwo" value="" />
            <input type="submit" value="Create" name="submit" />
        </form>
    </body>
</html>
