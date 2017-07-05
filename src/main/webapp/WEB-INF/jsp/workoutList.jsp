<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <a class="btn btn-primary" href="<c:url value="/workout/create"/>" role="button"><spring:message code="message.addBtn"/></a>
        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th><spring:message code="message.name"/></th>
                <th><spring:message code="message.starts"/></th>
                <th><spring:message code="message.ends"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${workoutList}" var="workout" varStatus="theCount">
                <jsp:useBean id="workout" scope="page" type="ua.kruart.workout.model.Workout"/>
                <tr>

                    <td>${theCount.index + 1}</td>
                    <td><a href="<c:url value="/exercise/all/workout/${workout.id}"/>">${workout.name}</a></td>
                    <td>${workout.startWorkout}</td>
                    <td>${workout.endWorkout}</td>
                    <td><a class="btn btn-warning" href="<c:url value="/workout/update/${workout.id}"/>"><spring:message code="message.editBtn"/></a></td>
                    <td><a class="btn btn-danger" href="<c:url value="/workout/delete/${workout.id}"/>"><spring:message code="message.deleteBtn"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
