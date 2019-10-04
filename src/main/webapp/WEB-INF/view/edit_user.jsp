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
        <td>${requestScope.user.id}</td>
        <td>${requestScope.user.email}</td>
        <c:choose>
            <c:when test="${param.lang=='ua'}">
                <td>${requestScope.user.firstNameUA}</td>
                <td>${requestScope.user.secondNameUA}</td>
            </c:when>
            <c:otherwise>
                <td>${requestScope.user.firstNameEN}</td>
                <td>${requestScope.user.secondNameEN}</td>
            </c:otherwise>
        </c:choose>
    </tr>
    </tbody>
</table>

<div>
    <form action="${pageContext.request.contextPath}/api/user?id=${requestScope.user.id}" method="post">
        <input type="checkbox" name="roles" value="CASHIER"
               <c:if test="${requestScope.user.isCashier()}">checked</c:if>>CASHIER<Br>
        <input type="checkbox" name="roles" value="SENIOR_CASHIER"
               <c:if test="${requestScope.user.isSeniorCashier()}">checked</c:if>>SENIOR_CASHIER<Br>
        <input type="checkbox" name="roles" value="MERCHANDISER"
               <c:if test="${requestScope.user.isMerchandiser()}">checked</c:if>>MERCHANDISER<Br>
        <input type="checkbox" name="roles" value="ADMIN"
               <c:if test="${requestScope.user.isAdmin()}">checked</c:if>>ADMIN<Br>
        <button type="submit" class="btn btn-small btn-dark"><fmt:message key="save.message"/></button>
    </form>
</div>
<%@include file="parts/footer.jsp" %>