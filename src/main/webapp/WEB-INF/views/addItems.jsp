<%@ page contentType="text/html;charset=UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Add items</title>
</head>
<body>
<div class="container">
    <p>Let's create a new item!</p>
    <form action="${pageContext.request.contextPath}/servlet/addItems" method="post">
        <label for="name"><b>Name of item</b></label>
        <input type="text" placeholder="Enter name of item" name="name" id="name" required>
        <label for="price"><b>Price of item</b></label>
        <input type="text" placeholder="Enter price of item" name="price" id="price" required>
        <button type="submit">Add item</button>
    </form>
    <p><a href="/internetshop/allItems">All items</a></p>
    <p><a href="/internetshop/index">Main page</a></p>
</div>
</body>
</html>
