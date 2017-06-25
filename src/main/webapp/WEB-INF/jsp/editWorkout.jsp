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
            <input type="hidden" value="${workoutModel.id}" name="id">
            <label>Name<input type="text" value="${workoutModel.name}" name="name"></label>
            <label>Starts<input type="datetime-local" value="${workoutModel.startWorkout}" name="startWorkout"></label>
            <label>Ends<input type="datetime-local" value="${workoutModel.endWorkout}" name="endWorkout"></label>

            <button type="submit" class="btn btn-success">Save</button>
            <button onclick="window.history.back()" class="btn btn-warning">Cancel</button>
        </form>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
