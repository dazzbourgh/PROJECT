<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style type="text/css"> <%@include file="/css/styles.css" %> </style>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div class="container vertical-center bg-blue">
                <div class="form-group well bg-white" align="center">
        <h1>Select language:</h1>
        <form action="languageChoice" method="GET">
            <select name="language">
              <option value="english">English</option>
              <option value="russian">Русский</option>
            </select>
        <input type="submit" value="OK" class="btn btn-default btn-margin"/>
        </form>
        </div>
        </div>
    </body>
</html>