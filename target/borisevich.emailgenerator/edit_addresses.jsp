<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Addresses</title>
    </head>
    <body>
        <h1>Edit Addresses</h1>

        <form action="editAddresses" method="POST">
            <c:set var="i" value="${0}" />
            <c:forEach var="par" items="${addressList}">
                <input type="text" name="name${i}" value="${par.getName()}" />
                <input type="text" name="address${i}" value="${par.getAddress()}" />
                <c:set var="i" value="${i+1}" />
            </c:forEach>
            <br>
            <input type="submit" value="Submit changes"/>
        </form>
        <br>
        Add new address:
        <br>
        <form action="addAddress" method="POST">
            Name: <input type="text" name="name" /> <br>
            Address: <input type="text" name="address" /> <br>
            <input type="submit" value="Add"/>
        </form>
    </body>
</html>