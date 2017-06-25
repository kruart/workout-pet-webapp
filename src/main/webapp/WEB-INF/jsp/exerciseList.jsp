<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <a class="btn btn-primary" href="/exercise/create?wid=${wid}">Add new exercise</a>
        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Type</th>
                <th>Complexity</th>
                <th>Muscles</th>
                <th>Desc</th>
                <th>Comment</th>
                <th>Edit</th>
                <th>Delete</th>

            </tr>
            </thead>
            <c:forEach items="${exerciseList}" var="exercise" varStatus="theCount">
                <tr>
                    <td>${theCount.index + 1}</td>
                    <td><a href="<c:url value="/approach?eid=${exercise.id}"/>">${exercise.description.name}</a></td>
                    <td>${exercise.description.type}</td>
                    <td>${exercise.description.complexity}</td>
                    <td>${exercise.description.muscles}</td>
                    <td>${exercise.description.description}</td>
                    <td>${exercise.comment}</td>
                    <td><a class="btn btn-warning" href="<c:url value="/exercise/update/${exercise.id}?wid=${exercise.workout.id}"/>">Edit</a></td>
                    <td><a class="btn btn-danger" href="<c:url value="/exercise/delete/${exercise.id}?wid=${exercise.workout.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
