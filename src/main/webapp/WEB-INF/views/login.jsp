<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Hello login page</title>
</head>
<body>
<div>${errorMsg}</div>
<div class="container">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>Please fill to sign in</p>
        <label for="login" class=" mb-2 mr-sm-2"><b>Login</b></label>
        <input type="text" placeholder="Enter login" name="login" id="login" required
               class="form-control mb-2 mr-sm-2">
        <label for="psw" class="mb-2 mr-sm-2"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" id="psw" required
               class="form-control mb-2 mr-sm-2">
        <button type="submit" class="btn btn-primary mb-2">Submit</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/index">Return to Main Page</a></p>
</div>
</div>
</body>
</html>
