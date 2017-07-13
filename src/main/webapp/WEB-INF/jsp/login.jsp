<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
	<jsp:include page="fragments/header.jsp"/>
	<div class="container h-100">
		<div class="row justify-content-md-center">
			<div class="jumbotron" style="margin-top: 150px">
				<h3><spring:message code="message.signIn"/></h3>
				<h5>
					<c:if test="${param.afterR != null}">
						<b class="successMessageProcess"><spring:message code="message.afterRegister"/></b>
					</c:if>
				</h5>
				<br>
				<form method="post" action="<c:url value="/performLogin"/>">
					<sec:csrfInput/>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="<spring:message code="message.email"/>" name="wUsername" value="${param.username}">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" placeholder="<spring:message code="message.enterPassword"/>" name="wPassword">
					</div>

					<div class="checkbox">
						<label>
							<input type="checkbox"> <spring:message code="message.rememberMe"/>
						</label>
					</div>
					<div>
						<c:if test="${param.error != null}">
							<b class="errorMessage"><spring:message code="message.invalidCredentials"/></b>
						</c:if>
					</div>

					<button type="submit" class="btn btn-primary form-control">Login</button>
				</form>
				<div class="socials">
					<a class="btn btn-block btn-social btn-github" href="<c:url value="/oauth/github//authorize"/>" role="button">
						<span class="fa fa-github"></span> <spring:message code="message.singInGithub"/>
					</a>
					<a class="btn btn-block btn-social btn-facebook" href="<c:url value="/oauth/facebook//authorize"/>" role="button">
						<span class="fa fa-facebook"></span> <spring:message code="message.singInFacebook"/>
					</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
