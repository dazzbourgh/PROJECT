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
        <style>
            p    {color: red;}
        </style>
    </head>
    <body>
    <jsp:directive.include file="header_top.jsp" />
    <div class="vertical-center bg-blue">
    <div class="form-group well bg-white" align="center">
        <h1>${headerEditAddresses}</h1>

        <form action="editTemplates" method="POST">
            <c:set var="i" value="${0}" />
            <c:forEach var="par" items="${templatesList}">
                <input type="hidden" name="template_id${i}" value="${par.getTemplate_id()}"/>
                <textarea rows="10" cols="30" class="text-margin" name="text${i}">${par.getText()}</textarea>
                <br>
                <input type="submit" name="delete${i}" class="btn btn-default btn-margin" value="Delete" />
                <c:set var="i" value="${i+1}" />
                <br>
            </c:forEach>
            <input type="hidden" name="templateQuantity" value="${i}" />
            <br>
            <c:if test="${i > 0}">
                <input type="submit" name="submit" class="btn btn-default btn-margin" value="${submitChangesButton}" />
            </c:if>
        </form>
        <br>
        <c:out value="Add new template:" />
        <br>
        <form action="addTemplate" method="POST">
            <textarea rows="10" cols="30" class="text-margin" name="newTemplate">Enter template...</textarea>
            <br>
            <input type="submit" class="btn btn-default btn-margin " value="${addButton}"/>
        </form>
        <c:if test="${templateError != null}">
            <p>${templateError}</p>
        </c:if>
        </div>
        </div>
    </body>
</html>