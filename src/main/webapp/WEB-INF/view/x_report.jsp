<%@include file="parts/header.jsp" %>

<div class="alert alert-info" role="alert">
    <h4 class="alert-heading">X-REPORT</h4>
    <p><fmt:message key="count.of.closed.checks.message"/> <strong>${requestScope.count}</strong></p>
    <p><fmt:message key="money.received.message"/> <strong>${requestScope.TOTAL_SUM}</strong></p>
</div>

<%@include file="parts/footer.jsp" %>