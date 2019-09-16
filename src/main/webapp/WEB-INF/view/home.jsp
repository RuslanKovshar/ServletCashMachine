<%@include file="parts/header.jsp" %>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="id.message"/></th>
        <th scope="col"><fmt:message key="email.message"/></th>
        <th scope="col"><fmt:message key="user.first.name.message"/></th>
        <th scope="col"><fmt:message key="user.second.name.message"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${sessionScope.user.id}</td>
        <td>${sessionScope.user.email}</td>
        <c:choose>
            <c:when test="${param.lang=='ua'}">
                <td>${sessionScope.user.firstNameUA}</td>
                <td>${sessionScope.user.secondNameUA}</td>
            </c:when>
            <c:otherwise>
                <td>${sessionScope.user.firstNameEN}</td>
                <td>${sessionScope.user.secondNameEN}</td>
            </c:otherwise>
        </c:choose>
    </tr>
    </tbody>
</table>

<c:if test="${sessionScope.user.isCashier()}">
    <form action="/open_check">
        <button class="btn btn-lg btn-success btn-block mt-2"><fmt:message key="open.check.message"/></button>
    </form>
</c:if>

<c:if test="${sessionScope.user.isMerchandiser()}">
    <form action="${pageContext.request.contextPath}/api/merchandiser">
        <button class="btn btn-lg btn-success btn-block mt-2"><fmt:message key="merchandiser.menu.message"/></button>
    </form>
</c:if>

<c:if test="${sessionScope.user.isSeniorCashier()}">
    <form action="/senior_cashier/x-report">
        <button type="submit" class="btn btn-success btn-lg btn-block mt-2">
            <fmt:message key="x-report.message"/>
        </button>
    </form>

    <form action="/senior_cashier/z-report">
        <button type="submit" class="btn btn-success btn-lg btn-block mt-2">
            <fmt:message key="z-report.message"/>
        </button>
    </form>
</c:if>
<%@include file="parts/footer.jsp" %>
