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
    <title>Edit Exercise</title>
</head>
<body>
    <form action="/exercise/saveChanges?wid=${new_or_edit_model.workout.id}" method="post">
        <input type="text" value="${new_or_edit_model.workout.id}" name="workoutId">
        <input type="text" value="${new_or_edit_model.id}" name="id">
        <input type="text" value="${new_or_edit_model.conf.distanceMeasure}" name="distanceMeasure">
        <input type="text" value="${new_or_edit_model.conf.repeatMeasure}" name="repeatMeasure">
        <input type="text" value="${new_or_edit_model.conf.timeMeasure}" name="timeMeasure">
        <input type="text" value="${new_or_edit_model.conf.weightMeasure}" name="weightMeasure">
        <input type="text" value="${new_or_edit_model.description.name}" name="name">
        <input type="text" value="${new_or_edit_model.description.complexity}" name="complexity">
        <input type="text" value="${new_or_edit_model.description.type}" name="type">
        <input type="text" value="${new_or_edit_model.description.description}" name="desc">
        Comment <input type="text" value="${new_or_edit_model.comment}" name="comment">

        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</body>
</html>
