<%--
  Created by IntelliJ IDEA.
  User: tuzkimo
  Date: 2017-03-24
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add User</title>
    <link href="<c:url value="/style/main.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main">
    <h2 class="title"><span>Add User</span></h2>
    <form action="<c:url value="/add"/>" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>user</legend>
            <p>
                <label for="name">Name: </label>
                <input type="text" name="name" id="name" value="${user.name}"/>
            </p>
            <p>
                <label for="password">Password: </label>
                <input type="password" name="password" id="password"/>
            </p>
            <p>
                <label for="description">Description: </label>
                <textarea name="description" id="description">${user.description}</textarea>
            </p>
            <p>
                <label for="description">Photo: </label>
                <input type="file" name="photo"/>
            </p>
            <p>
                <input type="submit" value="save">
            </p>
        </fieldset>
    </form>
    <p>
        <a class="abtn" href="<c:url value="/index"/>">Return</a>
    </p>
</div>
</body>
</html>
