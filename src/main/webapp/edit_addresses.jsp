<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${headerEditAddresses}</title>
    </head>
    <body>
        <h1>${headerEditAddresses}</h1>

        <form action="editAddresses" method="POST">
            <c:set var="i" value="${0}" />
            <c:forEach var="par" items="${addressList}">
                <input type="hidden" name="address_id${i}" value="${par.getAddress_id()}"/>
                <input type="text" name="name${i}" value="${par.getName()}" />
                <input type="text" name="address${i}" value="${par.getAddress()}" />
                <c:set var="i" value="${i+1}" />
                <br>
            </c:forEach>
            <input type="hidden" name="quantity" value="${i}" />
            <br>
            <input type="submit" value="${submitChangesButton}" />
        </form>
        <br>
        <c:out value="${addNewAddress}" />:
        <br>
        <form action="addAddress" method="POST">
            <c:out value="${edit_addressesLabelName}:" /><br>
            <input type="text" name="name" /> <br>
            <c:out value="${edit_addressesLabelAddress}:" /><br>
            <input type="text" name="address" /> <br>
            <input type="submit" value="${addButton}"/>
        </form>
    </body>
</html>