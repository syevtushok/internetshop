<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <h3>Let's create a new User!</h3>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <p>Please fill this form</p>
        <br>
        <div class="form-group">
            <label for="name"><b>Name</b></label>
            <input type="text" class="form-control" placeholder="Enter name" name="name" id="name" required>
            <label for="surname"><b>Surname</b></label>
            <input type="text" class="form-control" placeholder="Enter surname" name="surname" id="surname" required>

            <label for="login"><b>Login</b></label>
            <input type="text" class="form-control" placeholder="Enter login" name="login" id="login" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="psw" id="psw" required>

            <label for="psw-repeat"><b>Repeat Password</b></label>
            <input type="password" class="form-control" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat"
                   required>
            <hr>
            <button type="submit">Register</button>
        </div>
    </form>
    <p><a href="${pageContext.request.contextPath}/index">Return to Main Page</a></p>
</div>
</body>
</html>
