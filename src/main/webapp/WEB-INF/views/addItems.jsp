<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Let's create a new item!
<form action="${pageContext.request.contextPath}/servlet/addItems" method="post">
    <label for="name"><b>Name of item</b></label>
    <input type="text" placeholder="Enter name of item" name="name" id="name" required>
    <label for="price"><b>Price of item</b></label>
    <input type="text" placeholder="Enter price of item" name="price" id="price" required>
    <button type="submit">Add item</button>
</form>
<p><a href="/internetshop/allItems">All items</a></p>
<p><a href="/internetshop/index">Main page</a></p>
</body>
</html>
