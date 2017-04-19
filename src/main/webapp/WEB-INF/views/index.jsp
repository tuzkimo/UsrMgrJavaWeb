<%--
  Created by IntelliJ IDEA.
  User: tuzkimo
  Date: 2017-03-21
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>User Manager</title>
    <link href="<c:url value="/style/main.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="main">
        <h2 class="title"><span>User Manager</span></h2>
        <table class="tab">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>description</th>
                <th>photo</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.description}</td>
                    <td><img src="<c:url value="/photos/${user.photo}"/>" title="${user.name}" alt="${user.name}"></td>
                    <td>
                        <a class="abtn" href="<c:url value="/edit"><c:param name="id" value="${user.id}"/></c:url>">Edit</a>
                        <a class="abtn" href="<c:url value="/upPhoto"><c:param name="id" value="${user.id}"/></c:url>">UpPhoto</a>
                        <a class="abtn" href="<c:url value="/delete"><c:param name="id" value="${user.id}"/></c:url>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p>
            <a class="abtn" href="<c:url value="/add"/>">add</a>
        </p>
        <div class="pagination"></div>
    </div>
    <script src="<c:url value="/scripts/jquery-3.1.1.min.js"/>"></script>
    <script>
        var pageNo = ${pageNo};
        var pages = ${pages};
        var pagination = $(".pagination");

        $(document).ready(function() {
            pagination.append('<a href="#" class="prev">Prev</a>');
            for (var i=1; i <= pages; i++ ) {
                pagination.append('<a href="/?pageNo=' + i + '" class="numlink">' + i + '</a>');
            }
            pagination.append('<a href="#" class="next">Next</a>');
            pagination.append('<div style="clear: both">');

            if (pageNo == 1) {
                $('.prev').hide();
            } else {
                $('.prev').attr("href","/?pageNo=" + (pageNo-1));
            }

            if (pageNo == pages) {
                $('.next').hide();
            } else {
                $('.next').attr("href","/?pageNo=" + (pageNo+1));
            }

            $('.numlink:eq('+(pageNo-1)+')').addClass('current');
        })
    </script>
</body>
</html>
