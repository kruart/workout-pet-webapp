<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <form action="<c:url value="/workout/saveChanges"/>" method="post">
            <input type="hidden" value="${new_or_edit_model.id}" name="id">
            <input type="text" value="${new_or_edit_model.name}" name="name">
            <input type="datetime-local" value="${new_or_edit_model.startWorkout}" name="startWorkout">
            <input type="datetime-local" value="${new_or_edit_model.endWorkout}" name="endWorkout">

            <button type="submit" class="btn btn-success">Save</button>
            <button onclick="window.history.back()" class="btn btn-warning">Cancel</button>
        </form>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
