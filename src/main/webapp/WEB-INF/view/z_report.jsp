<%@include file="parts/header.jsp" %>

<div class="alert alert-warning" role="alert">
    <h4 class="alert-heading">Z-REPORT</h4>
    <p><fmt:message key="count.of.closed.checks.message"/> <strong>${requestScope.count}</strong></p>
    <p><fmt:message key="money.received.message"/> <strong>${requestScope.TOTAL_SUM}</strong></p>
</div>

<form action="${pageContext.request.contextPath}/api/logout" class="form-inline my-2 my-lg-0" method="post">
    <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><fmt:message key="logout.message"/></button>
</form>

<%@include file="parts/footer.jsp" %>