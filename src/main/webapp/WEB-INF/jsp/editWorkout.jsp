<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <form action="<c:url value="/workout/saveChanges"/>" method="post">
            <input type="hidden" value="${workoutModel.id}" name="id"><br>
            <label><spring:message code="message.name"/> <input type="text" class="form-control" value="${workoutModel.name}" name="name"></label><br>
            <label><spring:message code="message.starts"/> <input type="datetime-local" class="form-control" value="${workoutModel.startWorkout}" name="startWorkout"></label><br>
            <label><spring:message code="message.ends"/> <input type="datetime-local" class="form-control" value="${workoutModel.endWorkout}" name="endWorkout"></label><br>

            <button type="submit" class="btn btn-success"><spring:message code="message.saveBtn"/></button>
            <button onclick="window.history.back()" class="btn btn-warning"><spring:message code="message.cancelBtn"/></button>
        </form>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
