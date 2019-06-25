<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap.min.css">

    <title>ProjectView</title>
</head>

<body>

<script src="js/bootstrap.min.js"></script>

<nav class="navbar fixed-top navbar-dark navbar-expand bg-dark">
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="http://localhost:8080/tm/main">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/tm/project-list">PROJECT</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/tm/task-list">TASK</a>
        </li>
    </ul>
</nav>

<br>
<br>
<br>

<h1>VIEW PROJECT</h1>

<form:form method="POST" action="${pageContext.request.contextPath}/project-save/" modelAttribute="project" >
    <p>
    <div style="...">NAME</div>
    <form:form path="name" />
    </p>
    <p>
    <div style="...">DESCRIPTION</div>
    <form:form path="description" />
    </p>

    <p>
    <div style="...">ID PROJECT</div>
    <form:form path="id" />
    </p>

</form:form>

</body>
</html>
