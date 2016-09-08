<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
        <h1>Menu</h1>

        <form action="myaccount" method="GET">
            <input type="submit" value="My Account"/>
        </form>
        <form action="generationFormLoader" method="POST">
            <input type="submit" value="Generation"/>
        </form>
        <form action="editAddressesLoader" method="GET">
            <input type="submit" value="Edit Addresses"/>
        </form>
    </body>
</html>