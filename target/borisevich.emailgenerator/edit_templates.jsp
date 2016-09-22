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
    <body>
    <div class="container vertical-center bg-blue">
                <div class="form-group well bg-white" align="center">
        <h1>${headerEditAddresses}</h1>

        <form action="editTemplates" method="POST">
            <c:set var="i" value="${0}" />
            <c:forEach var="par" items="${templatesList}">
                <input type="hidden" name="template_id${i}" value="${par.getTemplate_id()}"/>
                <input type="text" class="text-margin" name="text${i}" value="${par.getText()}" />
                <c:set var="i" value="${i+1}" />
                <br>
            </c:forEach>
            <input type="hidden" name="templateQuantity" value="${i}" />
            <br>
            <input type="submit" class="btn btn-default btn-margin" value="${submitChangesButton}" />
        </form>
        <br>
        <c:out value="${addNewTemplate}" />:
        <br>
        <form action="addTemplate" method="POST">
            <c:out value="${edit_templatesLabelAddress}:" /><br>
            <input type="text" name="text" /> <br>
            <input type="submit" class="btn btn-default btn-margin" value="${addButton}"/>
        </form>
        </div>
        </div>
    </body>
</html>