<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/api/">CASH machine</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/api/"><fmt:message key="home.message"/> </a>
            </li>
        </ul>

        <div class="dropdown my-2 my-lg-0">
            <a class="nav-link dropdown-toggle"
               href="#" id="navbarDropdown"
               role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="language.message"/>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a id="en" class="dropdown-item" href="?lang=en">ENG</a>
                <a class="dropdown-item" href="?lang=ua">UA</a>
            </div>
        </div>

        <c:if test="${sessionScope.user != null}">
            <div class="nav-link mx-4"><a href="${pageContext.request.contextPath}/api/" style="text-decoration: none;">${sessionScope.user.email}</a></div>
            <form action="${pageContext.request.contextPath}/api/logout" class="form-inline my-2 my-lg-0" method="post">
                <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><fmt:message key="logout.message"/></button>
            </form>
        </c:if>
    </div>
</nav>

