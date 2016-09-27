<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:directive.include file="locale.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${menuLabelHeader}</title>
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
                <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>
    <body>
    <div class="vertical-center bg-blue">
                <div class="form-group well bg-white" align="center">
        <h1>${menuLabelHeader}</h1>

        <form action="myaccount" method="GET">
            <input type="submit" class="btn btn-default btn-margin" value="${menuButtonMyAccount}"/>
        </form>
        <form action="generationFormLoader" method="POST">
            <input type="submit" class="btn btn-default btn-margin" value="${menuButtonGeneration}"/>
        </form>
        <form action="editAddressesLoader" method="GET">
            <input type="submit" class="btn btn-default btn-margin" value="${menuButtonEditAddresses}"/>
        </form>
        <form action="editTemplatesLoader" method="GET">
            <input type="submit" class="btn btn-default btn-margin" value="${menuButtonEditTemplates}"/>
        </form>
        </div>
        </div>
    </body>
</html>