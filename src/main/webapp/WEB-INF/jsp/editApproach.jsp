<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <form:form  method="post" modelAttribute="approachModel" action="/approach/saveChanges/exercise/${eid}" >
            <div class="form-group">
                <input type="hidden" value="${eid}" name="eid"><br>
                <form:hidden path="id"/>
                <br>

                <form:label path="distance">
                    <spring:message code="message.distance"/>
                    <form:input path="distance" class="form-control"/>
                </form:label>
                <b class="errorMessage"><form:errors path="distance"/></b>
                <br>

                <form:label path="repeats">
                    <spring:message code="message.repeats"/>
                    <form:input path="repeats" class="form-control" />
                </form:label>
                <b class="errorMessage"><form:errors path="repeats"/></b>
                <br>

                <form:label path="time">
                    <spring:message code="message.time"/>
                    <form:input path="time" class="form-control"/>
                </form:label>
                <b class="errorMessage"><form:errors path="time"/></b>
                <br>

                <form:label path="weight">
                    <spring:message code="message.weight"/>
                    <form:input path="weight" class="form-control"/>
                </form:label>
                <b class="errorMessage"><form:errors path="weight"/></b>
                <br>

                <button type="submit" class="btn btn-success"><spring:message code="message.saveBtn"/></button>
                <button onclick="window.history.back()" class="btn btn-warning"><spring:message code="message.cancelBtn"/></button>
            </div>
        </form:form>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
