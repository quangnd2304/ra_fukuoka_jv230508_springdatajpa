<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 10/11/2023
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Book</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/bookController/create" method="post" enctype="multipart/form-data">
        <label for="bookName">Book Name</label>
        <input type="text" id="bookName" name="bookName"/><br/>
        <label for="price">Price</label>
        <input type="text" id="price" name="price"/><br/>
        <label for="bookImage">Image</label>
        <input type="file" id="bookImage" name="bookImage"/><br/>
        <label for="otherImages">Other Images</label>
        <input type="file" id="otherImages" name="otherImages" multiple/><br/>
        <label for="active">Status</label>
        <input type="radio" id="active" name="status"/><label for="active">Active</label>
        <input type="radio" id="inactive" name="status"/><label for="inactive">Inactive</label><br/>
        <input type="submit" value="Create"/>
    </form>
</body>
</html>
