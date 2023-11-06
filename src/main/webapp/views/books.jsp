<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 06/11/2023
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Book</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/bookController/sortAndPagging" method="post">
        <label for="orderDir">Order:</label>
        <select id="orderDir" name="orderDir">
            <option value="bookName">Book Name</option>
            <option value="price">Price</option>
        </select>
        <select id="orderBy" name="orderBy">
            <option value="ASC">ASC</option>
            <option value="DESC">DESC</option>
        </select><br>
        <input type="submit" value="Search"/>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>Book Id</th>
                <th>Book Name</th>
                <th>Price</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td>${book.bookId}</td>
                    <td>${book.bookName}</td>
                    <th>${book.price}</th>
                    <th>${book.status?"Active":"Inactive"}</th>
                    <th>
                        <a href="">Update</a>
                        <a href="">Delete</a>
                    </th>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:forEach items="${listPage}" var="page">
        <a href="<%=request.getContextPath()%>/bookController/findAll?page=${page}&&orderDir=bookName&&orderBy=ASC">${page}</a>
    </c:forEach>
</body>
</html>
