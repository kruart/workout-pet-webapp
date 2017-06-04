<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Workout List</title>
    <style>
        h1 {
            background-color: crimson;
            text-align: center;
        }

        table {
            border-collapse: collapse;
        }

        th, td {
            border: 4px solid black;
            padding: 10px;
        }
    </style>
</head>
<body>
    <h1><b>Workout List</b></h1>

    <a href="/workout/create">Add new workout</a>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>started</th>
            <th>ended</th>
            <th>exercises</th>

        </tr>
        <c:forEach items="${workoutList}" var="workout">
            <jsp:useBean id="workout" scope="page" type="ua.kruart.workout.model.Workout"/>
            <tr>

                <td>${workout.id}</td>
                <td><a href="/exercise?wid=${workout.id}">${workout.name}</a></td>
                <td>${workout.startWorkout}</td>
                <td>${workout.endWorkout}</td>
                <td><a href="/workout/update/${workout.id}">Edit</a></td>
                <td><a href="/workout/delete/${workout.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
