<jsp:useBean id="error_msg" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Something bad happened</title>
</head>
<body>
<p>Something wrong with data base</p>
<h3>${error_msg}</h3><br>
</body>
</html>
