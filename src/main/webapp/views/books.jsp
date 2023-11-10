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
    <form action="<%=request.getContextPath()%>/bookController/findBook" method="get">
        <label for="bookName">Book Name</label>
        <input type="text" id="bookName" name="bookName" placeholder="input book name for search" value="${bookName}"/>
        <input type="submit" value="Search"/>
    </form>
    <label for="sortBy">Order:</label>
    <select id="sortBy" name="sortBy" onchange="changeSortBy()">
        <option value="bookId" ${sortBy=="bookId"?'selected':''}>Book Id</option>
        <option value="bookName" ${sortBy=="bookName"?'selected':''}>Book Name</option>
        <option value="price" ${sortBy=="price"?'selected':''}>Price</option>
    </select>
    <select id="direction" name="direction" onchange="changeDirection()">
        <option value="ASC" ${direction=="ASC"?'selected':''}>ASC</option>
        <option value="DESC" ${direction=="DESC"?'selected':''}>DESC</option>
    </select><br>
    <a href="<%=request.getContextPath()%>/bookController/initCreate">Create New Book</a>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Book Id</th>
                <th>Book Name</th>
                <th>Price</th>
                <th>Image</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="stt" value="0" scope="page"/>
            <c:forEach items="${listBooks}" var="book">
                <c:set var="stt" value="${stt+1}" scope="page"/>
                <tr>
                    <td>${stt}</td>
                    <td>${book.bookId}</td>
                    <td>${book.bookName}</td>
                    <th>${book.price}</th>
                    <th><img src="${book.image}" width="50" height="50"/></th>
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
        <a href="<%=request.getContextPath()%>/bookController/findBook?page=${page}">${page}</a>
    </c:forEach>
<script>
    function changeSortBy(){
        let sortBy = document.getElementById("sortBy").value;
        window.location="<%=request.getContextPath()%>/bookController/findBook?sortBy="+sortBy;
    }
    function changeDirection(){
        let direction = document.getElementById("direction").value;
        window.location="<%=request.getContextPath()%>/bookController/findBook?direction="+direction;
    }
</script>
</body>
</html>
