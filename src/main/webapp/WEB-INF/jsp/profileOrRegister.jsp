<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container h-100">
        <div class="row justify-content-md-center">
            <div class="jumbotron" style="margin-top: 150px">
                <h3>
                    <c:if test="${type eq 'register'}"><spring:message code="message.register"/></c:if>
                    <c:if test="${type eq 'profile'}"><spring:message code="message.profile"/></c:if>
                </h3>
                <br>
                <form:form method="post" modelAttribute="userModel" action="/register">

                    <div class="form-group">
                        <spring:message code="message.enterUsername" var="enterUsername"/>
                        <form:input path="name" class="form-control" placeholder='${enterUsername}'/>
                        <b class="errorMessage"><form:errors path="name"/></b>
                    </div>
                    <div class="form-group">
                        <spring:message code="message.email" var="enterEmail"/>
                        <form:input path="email" type="email" class="form-control" placeholder="${enterEmail}"/>
                        <b class="errorMessage"><form:errors path="email"/></b>
                    </div>

                    <div class="form-group">
                        <spring:message code="message.enterPassword" var="enterPassword"/>
                        <form:password path="password" class="form-control" placeholder='${enterPassword}'/>
                        <b class="errorMessage"><form:errors path="password"/></b>
                    </div>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> <spring:message code="message.rememberMe"/>
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary form-control">Register</button>
                </form:form>
            </div>
        </div>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>