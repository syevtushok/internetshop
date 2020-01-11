<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Let's create a new User!
<form action="${pageContext.request.contextPath}/register" method="post">
    <p>Please fill this form</p>
    <br>
    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter name" name="name" id="name" required>

    <label for="surname"><b>Surname</b></label>
    <input type="text" placeholder="Enter surname" name="surname" id="surname" required>

    <label for="login"><b>Login</b></label>
    <input type="text" placeholder="Enter login" name="login" id="login" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" required>

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
    <hr>
    <button type="submit">Register</button>

</form>
</body>
</html>
