<%@ page import="ru.kravchenko.spring.entity.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap.min.css">
    <title>Task</title>
</head>

<body>

<script src="js/bootstrap.min.js"></script>

<nav class="navbar fixed-top navbar-dark navbar-expand bg-dark">
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="main">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="project-list">PROJECT</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="task-list">TASK</a>
        </li>
    </ul>
</nav>

<h1>TASK MANAGEMENT</h1>

<div class="col-lg-9 col-md-8">
    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="thead-inverse">

            <tr>
                <th width="60" nowrap="nowrap" align="center">№</th>
                <th width="200" nowrap="nowrap" align="left">ID</th>
                <th width="300" nowrap="nowrap" align="left">NAME</th>
                <th width="100%" align="left">DESCRIPTION</th>
                <th width="80" nowrap="nowrap" align="center">VIEW</th>
                <th width="80" nowrap="nowrap" align="center">EDIT</th>
                <th width="80" nowrap="nowrap" align="center">REMOVE</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="task" items="${tasks}" varStatus="status">
                <tr>
                    <td align="center" nowrap="nowrap">${status.index + 1}.</td>
                    <td align="left">${task.id}</td>
                    <td align="left">${task.name}</td>
                    <td align="left">${task.description}</td>
                    <td align="center" nowrap="nowrap">
                        <a href="${pageContext.request.contextPath}/task-view/${task.id}">VIEW</a>
                    </td>

                    <td align="center" nowrap="nowrap">
                        <a href="${pageContext.request.contextPath}/task-edit/${task.id}">EDIT </a>
                    </td>
                    <td align="center" nowrap="nowrap">
                        <a href="${pageContext.request.contextPath}/task-delete/${task.id}">REMOVE </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<br />

<table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td nowrap="nowrap">
            <form action="task-create">
                <button type="submit" class="green">CREATE TASK</button>
            </form>
        </td>
        <td width="20" nowrap="nowrap">&nbsp;</td>
        <td nowrap="nowrap">
            <form>
                <input type="hidden" name="time" value="<%=System.currentTimeMillis()%>" />
                <button type="submit" class="gray">REFRESH</button>
            </form>
        </td>
        <td width="100%">
            &nbsp;
        </td>
    <tr/>
</table>

</body>
</html>
