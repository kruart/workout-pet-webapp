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
        <form:form method="post" modelAttribute="exerciseModel" action="/exercise/saveChanges/workout/${wid}">
            <input type="hidden" value="${wid}" name="wid">
            <form:hidden path="id"/><br>
            <div class="form-check form-check-inline">
                <form:label path="conf.distanceMeasure" class="form-check-label">
                    <form:checkbox path="conf.distanceMeasure" class="form-check-input"/>
                    <spring:message code="message.distance"/>
                </form:label>
            </div>

            <div class="form-check form-check-inline">
                <form:label path="conf.repeatMeasure" class="form-check-label">
                    <form:checkbox path="conf.repeatMeasure" class="form-check-input"/>
                    <spring:message code="message.repeats"/>
                </form:label>
            </div>

            <div class="form-check form-check-inline">
                <form:label path="conf.timeMeasure" class="form-check-label">
                    <form:checkbox path="conf.timeMeasure" class="form-check-input"/>
                    <spring:message code="message.time"/>
                </form:label>
            </div>

            <div class="form-check form-check-inline">
                <form:label path="conf.weightMeasure" class="form-check-label">
                    <form:checkbox path="conf.weightMeasure" class="form-check-input"/>
                    <spring:message code="message.weight"/>
                </form:label>
            </div>
            <br>
            <form:label path="description.name">
                <spring:message code="message.name"/>
                <form:input path="description.name" class="form-control"/>
            </form:label>
            <b class="errorMessage"><form:errors path="description.name"/></b>
            <br>

            <form:label path="description.complexity">
                <spring:message code="message.complexity"/>
                <form:input path="description.complexity" class="form-control"/>
            </form:label>
            <b class="errorMessage"><form:errors path="description.complexity"/></b>
            <br>

            <form:label path="description.type">
                <spring:message code="message.type"/>
                <form:input path="description.type" class="form-control"/>
            </form:label>
            <b class="errorMessage"><form:errors path="description.type"/></b>
            <br>

            <form:label path="description.description">
                <spring:message code="message.desc"/>
                <form:input path="description.description" class="form-control"/>
            </form:label>
            <br>

            <form:label path="comment">
                <spring:message code="message.comment"/>
                <form:input path="comment" class="form-control"/>
            </form:label>
            <b class="errorMessage"><form:errors path="comment"/></b>
            <br>

            <spring:message code="message.muscles"/> <br>

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

            <c:if test="${!exerciseModel.description.muscles.containsValue('main')}">
                <label><spring:message code="message.musclesMain"/>: <input type="text" class="form-control" value="" name="main"></label><br>
            </c:if>

            <c:if test="${!exerciseModel.description.muscles.containsValue('optional')}">
                <label><spring:message code="message.musclesOptional"/>: <input type="text" class="form-control" value="" name="optional"></label><br>
            </c:if>

            <button type="submit" class="btn btn-success"><spring:message code="message.saveBtn"/></button>
            <button onclick="window.history.back()" class="btn btn-warning"><spring:message code="message.cancelBtn"/></button>
        </form:form>
    </div>

    <jsp:include page="fragments/footer.jsp"/>

    <script>
        $(document).ready(function () {
            $('input:checkbox').click(function (e) {
                $(this).attr('value', this.checked ? 'true' : 'false');
            });
        });
    </script>
</body>
</html>
