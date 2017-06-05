<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Exercise List</title>
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
    <h1><b>Exercise List</b></h1>

    <a href="/exercise/create?wid=${exerciseList.get(0).workout.id}">Add new exercise</a>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Type</th>
            <th>Complexity</th>
            <th>muscles</th>
            <th>Desc</th>
            <th>comment</th>
            <th>Edit</th>
            <th>Delete</th>

        </tr>
        <c:forEach items="${exerciseList}" var="exercise">
            <tr>
                <td>${exercise.id}</td>
                <td>${exercise.description.name}</td>
                <td>${exercise.description.type}</td>
                <td>${exercise.description.complexity}</td>
                <td>${exercise.description.muscles}</td>
                <td>${exercise.description.description}</td>
                <td>${exercise.comment}</td>
                <td><a href="/exercise/update/${exercise.id}?wid=${exercise.workout.id}">Edit</a></td>
                <td><a href="/exercise/delete/${exercise.id}?wid=${exercise.workout.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
