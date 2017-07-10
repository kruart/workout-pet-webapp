<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="<c:url value="/"/>"><spring:message code="message.mainPage"/></a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#"><spring:message code="message.homeMenuItem"/> <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/workout"/>"><spring:message code="message.workoutMenuItem"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><spring:message code="message.aboutMenuItem"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><spring:message code="message.contactsMenuItem"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="message.optionsMenuItem"/> </a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="#"><spring:message code="message.myProgramsMenuItem"/></a>
                    <a class="dropdown-item" href="<c:url value="/workout/create"/>"><spring:message code="message.createNewWorkoutMenuItem"/></a>
                    <a class="dropdown-item" href="#"><spring:message code="message.addOwnExerciseMenuItem"/></a>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav">
            <jsp:include page="lang.jsp"/>
            <li class="nav-item">
                <a class="nav-link" href="#">Username</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/register"/>">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/logout"/>">Logout</a>
            </li>
        </ul>
    </div>
</nav>
