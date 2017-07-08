<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
	<jsp:include page="fragments/header.jsp"/>
	<div class="container h-100">
		<div class="row justify-content-md-center">
			<div class="jumbotron" style="margin-top: 150px">
				<h3><spring:message code="message.signIn"/></h3>
				<br>
				<form method="post" action="<c:url value="/performLogin"/>">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="<spring:message code="message.enterUsername"/>" name="wUsername" value="${username}">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" placeholder="<spring:message code="message.enterPassword"/>" name="wPassword">
					</div>

					<div class="checkbox">
						<label>
							<input type="checkbox"> <spring:message code="message.rememberMe"/>
						</label>
					</div>

					<button type="submit" class="btn btn-primary form-control">Login</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
