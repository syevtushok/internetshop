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
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <p>Please fill to sign in</p>
                    <label for="login"><b>Login</b></label>
                    <input type="text" placeholder="Enter login" name="login" id="login" required
                           class="form-control input-sm chat-input">
                    <label for="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="psw" id="psw" required
                           class="form-control input-sm chat-input">
                    <div class="wrapper">
            <span class="group-btn">
                <button type="submit" class="btn btn-primary btn-md">Sign In<i class="fa fa-sign-in"></i></button>
            </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
