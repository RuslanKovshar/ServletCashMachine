<%@ page import="ruslan.kovshar.view.Params" %>
<%@include file="parts/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.redirectURI != null}">
    <div class="alert alert-warning">
        <c:out value="${sessionScope.redirectURI}"/>
    </div>
</c:if>

<div class="container mt-5" style="width: 40%">
    <form class="form-signin" method="post" action="${pageContext.request.contextPath}/api/login?redirectURI=${sessionScope.redirectURI}">
        <c:if test="${requestScope.error}">
            <div class="alert alert-danger">
                <fmt:message key="after.error.message"/>
            </div>
        </c:if>

        <c:if test="${requestScope.logout}">
            <div class="alert alert-info">
                <fmt:message key="after.logout.message"/>
            </div>
        </c:if>
        <h2 class="form-signin-heading"><fmt:message key="welcome.message"/></h2>
        <p>
            <label for="username" class="sr-only">Email</label>
            <input type="text" id="username" name=<%= Params.EMAIL %> class="form-control"
                   placeholder="Email"
                   pattern="([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})"
                   title="Must look like example@mail.com"
                   required
                   autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name=<%= Params.PASSWORD %> class="form-control" placeholder="Password"
                   required>
        </p>


        <%--<label for="remember-me"><@spring.message "remember.me.message"/></label>
        <input id="remember-me" type="checkbox" name="remember-me" value="true"/>--%>


        <button class="btn btn-lg btn-dark btn-block" type="submit"><fmt:message key="login.message"/></button>
    </form>
    <form class="form-signin mt-3" action="${pageContext.request.contextPath}/api/registration">
        <button class="btn btn-lg btn-success btn-block" type="submit"><fmt:message key="create.account.message"/>
        </button>
    </form>
</div>
<%@include file="parts/footer.jsp" %>
