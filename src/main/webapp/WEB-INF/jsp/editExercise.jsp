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
        <form action="<c:url value="/exercise/saveChanges"/>" method="post">
            <input type="hidden" value="${exerciseModel.workout.id}" name="workoutId">
            <input type="hidden" value="${exerciseModel.id}" name="id"><br>
            <div class="form-check form-check-inline">
                <label class="form-check-label"><input type="checkbox" class="form-check-input" value="${exerciseModel.conf.distanceMeasure}" name="distanceMeasure"> <spring:message code="message.distance"/></label><br>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label"><input type="checkbox" class="form-check-input" value="${exerciseModel.conf.repeatMeasure}" name="repeatMeasure"> <spring:message code="message.repeats"/></label><br>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label"><input type="checkbox" class="form-check-input" value="${exerciseModel.conf.timeMeasure}" name="timeMeasure"> <spring:message code="message.time"/></label><br>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label"><input type="checkbox" class="form-check-input" value="${exerciseModel.conf.weightMeasure}" name="weightMeasure"> <spring:message code="message.weight"/></label><br>
            </div>

            <br>
            <label><spring:message code="message.name"/><input type="text" class="form-control" value="${exerciseModel.description.name}" name="name"></label><br>
            <label><spring:message code="message.complexity"/><input type="text" class="form-control" value="${exerciseModel.description.complexity}" name="complexity"></label><br>
            <label><spring:message code="message.type"/> <input type="text" class="form-control" value="${exerciseModel.description.type}" name="type"></label><br>
            <label><spring:message code="message.desc"/> <input type="text" class="form-control" value="${exerciseModel.description.description}" name="desc"></label><br>
            <label> <spring:message code="message.comment"/> <input type="text" class="form-control" value="${exerciseModel.comment}" name="comment"></label><br>
            <spring:message code="message.muscles"/> <br>

            <c:if test="${exerciseModel.description.muscles == null}">
                <label><spring:message code="message.musclesMain"/>: <input type="text" class="form-control" value="" name="main"></label><br>
                <label><spring:message code="message.musclesOptional"/>: <input type="text" class="form-control" value="" name="optional"></label><br>
            </c:if>

            <c:forEach items="${exerciseModel.description.muscles}" var="muscle">
                <c:choose>
                    <c:when test="${muscle.value eq 'main'}">
                        <%--Main muscle: <input type="text" value="${muscle.key.muscle}" name="main"><br>--%>
                        <label><spring:message code="message.musclesMain"/>: <input type="text" class="form-control" value="${muscle.key}" name="main"></label><br>
                    </c:when>
                    <c:otherwise>
                        <label><spring:message code="message.musclesOptional"/>: <input type="text" class="form-control" value="${muscle.key}" name="optional"></label><br>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <button type="submit" class="btn btn-success"><spring:message code="message.saveBtn"/></button>
            <button onclick="window.history.back()" class="btn btn-warning"><spring:message code="message.cancelBtn"/></button>
        </form>
    </div>

    <jsp:include page="fragments/footer.jsp"/>

    <script>
        $(document).ready(function () {
            $('input:checkbox[value=true]').prop('checked', 'checked');

            $('input:checkbox').click(function (e) {
                $(this).attr('value', this.checked ? 'true' : 'false');
            });
        });
    </script>
</body>
</html>
