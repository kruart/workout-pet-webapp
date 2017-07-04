<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/headTag.jsp"/>
<body>
    <div class="container">
        <jsp:include page="../fragments/header.jsp"/>

        <div class="jumbotron">
            <div class="container">
                <h1 class="display-4">${errMsg}</h1>
            </div>
        </div>
    </div>
    <jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
