<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <label>distance <input type="text" class="form-control" value="${approachModel.distance}" name="distance"></label><br>
                <label>repeats <input type="text" class="form-control" value="${approachModel.repeats}" name="repeats"></label><br>
                <label>time <input type="time" class="form-control" value="${approachModel.time}" name="time"></label><br>
                <label>weight <input type="text" class="form-control" value="${approachModel.weight}" name="weight"></label><br>

                <button type="submit" class="btn btn-success">Save</button>
                <button onclick="window.history.back()" class="btn btn-warning">Cancel</button>
            </div>
        </form>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
