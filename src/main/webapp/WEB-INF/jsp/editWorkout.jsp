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
        <form:form method="post" modelAttribute="workoutModel" action="/workout/saveChanges">
            <form:hidden path="id"/>
            <br>
            <form:label path="name"><spring:message code="message.name"/>
                <form:input path="name" class="form-control"/>
            </form:label>
            <b class="errorMessage"><form:errors path="name"/></b>
            <br>
            <form:label path="startWorkout"><spring:message code="message.starts"/>
                <form:input path="startWorkout" type="datetime-local" class="form-control" value="${workoutModel.startWorkout}"/>
            </form:label>
            <b class="errorMessage"><form:errors path="startWorkout"/></b>
            <br>
            <form:label path="endWorkout"><spring:message code="message.ends"/>
                <form:input path="endWorkout" type="datetime-local" class="form-control" value="${workoutModel.endWorkout}"/>
            </form:label>
            <b class="errorMessage"><form:errors path="endWorkout"/></b>
            <br>

            <button type="submit" class="btn btn-success"><spring:message code="message.saveBtn"/></button>
            <button onclick="window.history.back()" class="btn btn-warning"><spring:message code="message.cancelBtn"/></button>
        </form:form>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
