<%@include file="parts/header.jsp" %>

<div class="alert alert-info" role="alert">
    <h4 class="alert-heading">Z-REPORT</h4>
    <p><fmt:message key="count.of.closed.checks.message"/> <strong>${requestScope.count}</strong></p>
    <p><fmt:message key="money.received.message"/> <strong>${requestScope.TOTAL_SUM}</strong></p>
    <hr>
<%--    <button class="btn mb-0"
            data-toggle="collapse"
            data-target="#collapseExample"
            aria-expanded="false"
            aria-controls="collapseExample">
        <fmt:message key="show.checks.message"/>
    </button>--%>
</div>

<%@include file="parts/footer.jsp" %>