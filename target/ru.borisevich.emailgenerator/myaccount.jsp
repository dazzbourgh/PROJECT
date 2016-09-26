<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${myaccountLabelHeader}</title>
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
                        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body>
    <div class="container vertical-center bg-blue">
                    <div class="form-group well bg-white" align="center">
        <h1>${myaccountLabelHeader}</h1>

            <c:out value="${myaccountLabelEmailHistory}:" /><br>
            <c:set var="i" value="${1}" />
            <c:forEach var="par" items="${emailList}">
                <c:out value="${i}. To: ${par.getAddress()}"/>
                <br>
                <c:set var="i" value="${i+1}" />
            </c:forEach>
    </div>
    </div>
    </body>
</html>