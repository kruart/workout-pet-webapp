<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <a class="btn btn-primary" href="<c:url value="/approach/create?eid=${eid}"/>"><spring:message code="message.addBtn"/></a>
        <table class="table">
            <thead class="thead-inverse">
            <tr>
                <th>#</th>
                <th><spring:message code="message.repeats"/></th>
                <th><spring:message code="message.weight"/></th>
                <th><spring:message code="message.distance"/></th>
                <th><spring:message code="message.time"/></th>
                <th></th>
                <th></th>

            </tr>
            </thead>
            <c:forEach items="${approachList}" var="approach" varStatus="theCount">
                <tr>
                    <td>${theCount.index + 1}</td>
                    <td>${approach.repeats}</td>
                    <td>${approach.weight}</td>
                    <td>${approach.distance}</td>
                    <td>${approach.time}</td>
                    <td><a class="btn btn-warning" href="<c:url value="/approach/update/${approach.id}?eid=${eid}"/>"><spring:message code="message.editBtn"/></a></td>
                    <td><a class="btn btn-danger" href="<c:url value="/approach/delete/${approach.id}?eid=${eid}"/>"><spring:message code="message.deleteBtn"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
