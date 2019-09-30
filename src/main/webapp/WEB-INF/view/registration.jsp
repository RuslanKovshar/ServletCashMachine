<%@ page import="ruslan.kovshar.view.Params" %>
<%@include file="parts/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form class="form-signin" action="${pageContext.request.contextPath}/api/registration" method="post">
    <c:if test="${requestScope.success != null}">
        <div class="alert alert-success" role="alert">
            <fmt:message key="success.message"/>
            <a href="${pageContext.request.contextPath}/api/login"><fmt:message key="go.to.login.page.message"/></a>
        </div>
    </c:if>

    <c:if test="${requestScope.error != null}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="user.with.email.message"/>email<%--<i>${createUserDTO.email}</i>--%> <fmt:message key="exist.message"/>
        </div>
    </c:if>

    <div class="form-group">
        <label for="InputEmail"><fmt:message key="email.message"/></label>
        <input type="email"
               class="form-control"
               id="InputEmail"
               placeholder="example@mail.com"
               name=<%= Params.EMAIL %>>
        <%--value="<#if createUserDTO??>${createUserDTO.email}</#if>">--%>
        <%--    <#if emailError??>
            <div class="invalid-feedback">${emailError}</div>
        </
        #if>--%>
    </div>

    <div class="form-group">
        <label for="InputPassword"><fmt:message key="password.message"/></label>
        <input type="password"
               class="form-control"
               id="InputPassword"
               placeholder="Password"
               name=<%= Params.PASSWORD %>>
    </div>

    <div class="row">
        <div class="col">
            <div class="form-group">
                <label for="InputFirstNameUA"><fmt:message key="first.name.message"/>UA</label>
                <input id="InputFirstNameUA" type="text"
                       class="form-control"
                       placeholder="<fmt:message key="placeholder.for.second.name.message"/>"
                       name=<%= Params.FIRST_NAME_UA %>>
            </div>
        </div>

        <div class="col">
            <div class="form-group">
                <label for="InputFirstNameEN"><fmt:message key="first.name.message"/>EN</label>
                <input id="InputFirstNameEN" type="text"
                       class="form-control"
                       name=<%= Params.FIRST_NAME_EN %>
                               placeholder="<fmt:message key="placeholder.for.first.name.message"/>">
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <div class="form-group">
                <label for="InputSecondNameUA"><fmt:message key="second.name.message"/>UA</label>
                <input id="InputSecondNameUA" type="text"
                       class="form-control"
                       name=<%= Params.SECOND_NAME_UA %>
                               placeholder="<fmt:message key="placeholder.for.second.name.message"/>">
            </div>
        </div>

        <div class="col">
            <div class="form-group">
                <label for="InputSecondNameEN"><fmt:message key="second.name.message"/>EN</label>
                <input id="InputSecondNameEN" type="text"
                       class="form-control"
                       name=<%= Params.SECOND_NAME_EN %>
                               placeholder="<fmt:message key="placeholder.for.second.name.message"/>">
            </div>
        </div>
    </div>

    <button type="submit" class="btn btn-dark btn-lg btn-block">
        <fmt:message key="create.account.message"/>
    </button>
</form>

<form class="form-signin mt-3" action="${pageContext.request.contextPath}/api/login">
    <button type="submit" class="btn btn-success btn-lg btn-block">
        <fmt:message key="back.button.message"/>
    </button>
</form>
<%@include file="parts/footer.jsp" %>