<%--
  Created by IntelliJ IDEA.
  User: Arthur
  Date: 5/30/2017
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
    <form action="/workout/saveChanges" method="post">
        <input type="hidden" value="${new_or_edit_model.id}" name="id">
        <input type="text" value="${new_or_edit_model.name}" name="name">
        <input type="datetime-local" value="${new_or_edit_model.startWorkout}" name="startWorkout">
        <input type="datetime-local" value="${new_or_edit_model.endWorkout}" name="endWorkout">

        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</body>
</html>
