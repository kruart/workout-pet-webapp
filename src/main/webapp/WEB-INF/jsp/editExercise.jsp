<%--
  Created by IntelliJ IDEA.
  User: Arthur
  Date: 5/30/2017
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit Exercise</title>
</head>
<body>
    <form action="/exercise/saveChanges?wid=${new_or_edit_model.workout.id}" method="post">
        <input type="hidden" value="${new_or_edit_model.workout.id}" name="workoutId">
        <input type="hidden" value="${new_or_edit_model.id}" name="id"><br>
        <div id="conf" style="border: 1px solid red">
            distance <input type="checkbox" value="${new_or_edit_model.conf.distanceMeasure}" name="distanceMeasure"><br>
            repeat <input type="checkbox" value="${new_or_edit_model.conf.repeatMeasure}" name="repeatMeasure"><br>
            time <input type="checkbox" value="${new_or_edit_model.conf.timeMeasure}" name="timeMeasure"><br>
            weight <input type="checkbox" value="${new_or_edit_model.conf.weightMeasure}" name="weightMeasure"><br>
        </div>
        Name <input type="text" value="${new_or_edit_model.description.name}" name="name"><br>
        Complexity <input type="text" value="${new_or_edit_model.description.complexity}" name="complexity"><br>
        Type <input type="text" value="${new_or_edit_model.description.type}" name="type"><br>
        Desc <input type="text" value="${new_or_edit_model.description.description}" name="desc"><br>
        Comment <input type="text" value="${new_or_edit_model.comment}" name="comment"><br>
        Muscles <br>
        <c:forEach items="${new_or_edit_model.description.muscles}" var="muscle">
        <c:choose>
            <c:when test="${muscle.value eq 'main'}">
                <%--Main muscle: <input type="text" value="${muscle.key.muscle}" name="main"><br>--%>
                Main muscle: <input type="text" value="${muscle.key}" name="main"><br>
            </c:when>
            <c:otherwise>
                Optional muscle: <input type="text" value="${muscle.key}" name="optional"><br>
            </c:otherwise>
        </c:choose>
    </c:forEach>

        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.js" integrity="sha256-tA8y0XqiwnpwmOIl3SGAcFl2RvxHjA8qp0+1uCGmRmg=" crossorigin="anonymous"></script>
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
