<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Workout List</title>
</head>
<body>
    <h1 style="background-color: crimson" align="center"><b>Workout List</b></h1>

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
                <td><a href="/workout/id=${workout.id}">${workout.name}</a></td>
                <td>${workout.startWorkout}</td>
                <td>${workout.endWorkout}</td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>
