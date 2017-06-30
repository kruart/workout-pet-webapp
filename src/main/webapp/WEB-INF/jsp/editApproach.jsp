<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <br><br><br>
    <div class="container">
        <form action="<c:url value="/approach/saveChanges"/>" method="post">
            <div class="form-group">
                <input type="hidden" value="${eid}" name="eid"><br>
                <input type="hidden" value="${approachModel.id}" name="id"><br>
                <label><spring:message code="message.distance"/> <input type="text" class="form-control" value="${approachModel.distance}" name="distance"></label><br>
                <label><spring:message code="message.repeats"/> <input type="text" class="form-control" value="${approachModel.repeats}" name="repeats"></label><br>
                <label><spring:message code="message.time"/> <input type="time" class="form-control" value="${approachModel.time}" name="time"></label><br>
                <label><spring:message code="message.weight"/> <input type="text" class="form-control" value="${approachModel.weight}" name="weight"></label><br>

                <button type="submit" class="btn btn-success"><spring:message code="message.saveBtn"/></button>
                <button onclick="window.history.back()" class="btn btn-warning"><spring:message code="message.cancelBtn"/></button>
            </div>
        </form>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
