<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <a class="btn btn-primary" href="<c:url value="/exercise/create?wid=${wid}"/>"><spring:message code="message.addBtn"/></a>
        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th><spring:message code="message.name"/></th>
                <th><spring:message code="message.type"/></th>
                <th><spring:message code="message.complexity"/></th>
                <th><spring:message code="message.muscles"/></th>
                <th><spring:message code="message.desc"/></th>
                <th><spring:message code="message.comment"/></th>
                <th></th>
                <th></th>

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
                    <td><a class="btn btn-warning" href="<c:url value="/exercise/update/${exercise.id}?wid=${exercise.workout.id}"/>"><spring:message code="message.editBtn"/></a></td>
                    <td><a class="btn btn-danger" href="<c:url value="/exercise/delete/${exercise.id}?wid=${exercise.workout.id}"/>"><spring:message code="message.deleteBtn"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
