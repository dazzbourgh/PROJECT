<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${headerEditAddresses}</title>
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
                                        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                                        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body class="bg-blue">
    <jsp:directive.include file="header_top.jsp" />
    <div class="vertical-center bg-blue">
                <div class="form-group well bg-white" align="center">
        <h1>${headerEditAddresses}</h1>

        <form action="editAddresses" method="POST">
            <c:set var="i" value="${0}" />
            <c:forEach var="par" items="${addressList}">
                <input type="hidden" name="address_id${i}" value="${par.getAddress_id()}"/>
                <input type="text" class="text-margin" name="name${i}" value="${par.getName()}" />
                <input type="text" class="text-margin" name="address${i}" value="${par.getAddress()}" />
                <c:set var="i" value="${i+1}" />
                <br>
            </c:forEach>
            <input type="hidden" name="quantity" value="${i}" />
            <br>
            <input type="submit" class="btn btn-default btn-margin" value="${submitChangesButton}" />
        </form>
        <br>
        <c:out value="${addNewAddress}" />:
        <br>
        <form action="addAddress" method="POST">
            <c:out value="${edit_addressesLabelName}:" /><br>
            <input type="text" class="text-margin" name="name" /> <br>
            <c:out value="${edit_addressesLabelAddress}:" /><br>
            <input type="text" name="address" /> <br>
            <input type="submit" class="btn btn-default btn-margin" value="${addButton}"/>
        </form>
        </div>
        </div>
    </body>
</html>