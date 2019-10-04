<%@include file="parts/header.jsp" %>


<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="id.message"/></th>
        <th scope="col"><fmt:message key="email.message"/></th>
        <th scope="col"><fmt:message key="user.first.name.message"/></th>
        <th scope="col"><fmt:message key="user.second.name.message"/></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <c:choose>
                <c:when test="${param.lang=='ua'}">
                    <td>${user.firstNameUA}</td>
                    <td>${user.secondNameUA}</td>
                </c:when>
                <c:otherwise>
                    <td>${user.firstNameEN}</td>
                    <td>${user.secondNameEN}</td>
                </c:otherwise>
            </c:choose>
            <td>
                <form action="${pageContext.request.contextPath}/api/user" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" class="btn btn-small btn-dark"><fmt:message key="edit.message"/></button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="parts/footer.jsp" %>