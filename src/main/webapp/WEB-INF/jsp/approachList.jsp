<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <a class="btn btn-primary" href="<c:url value="/approach/create?eid=${eid}"/>">Add new approach</a>
        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th>Repeats</th>
                <th>Weight</th>
                <th>Distance</th>
                <th>Time</th>
                <th>Edit</th>
                <th>Delete</th>

            </tr>
            </thead>
            <c:forEach items="${approachModel}" var="approach" varStatus="theCount">
                <tr>
                    <td>${theCount.index + 1}</td>
                    <td>${approach.repeats}</td>
                    <td>${approach.weight}</td>
                    <td>${approach.distance}</td>
                    <td>${approach.time}</td>
                    <td><a class="btn btn-warning" href="<c:url value="/approach/update/${approach.id}?eid=${eid}"/>">Edit</a></td>
                    <td><a class="btn btn-danger" href="<c:url value="/approach/delete/${approach.id}?eid=${eid}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
