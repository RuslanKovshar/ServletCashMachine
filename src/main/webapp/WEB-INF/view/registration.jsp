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
            <fmt:message key="user.with.email.message"/><i> ${requestScope.user.email} </i><fmt:message
                key="exist.message"/>
        </div>
    </c:if>

    <div class="form-group">
        <label for="InputEmail"><fmt:message key="email.message"/></label>
        <input type="email"
               class="form-control <c:if test="${requestScope.emailError != null}">is-invalid</c:if>"
               id="InputEmail"
               placeholder="example@mail.com"
               value="<c:if test="${requestScope.user != null}">${requestScope.user.email}</c:if>"
               name="<%= Params.EMAIL %>">
        <c:if test="${requestScope.emailError != null}">
            <div class="invalid-feedback"><fmt:message key="${requestScope.emailError}"/></div>
        </c:if>
    </div>

    <div class="form-group">
        <label for="InputPassword"><fmt:message key="password.message"/></label>
        <input type="password"
               class="form-control <c:if test="${requestScope.passwordError != null}">is-invalid</c:if>"
               id="InputPassword"
               placeholder="Password"
               name="<%= Params.PASSWORD %>">
        <c:if test="${requestScope.passwordError != null}">
            <div class="invalid-feedback"><fmt:message key="${requestScope.passwordError}"/></div>
        </c:if>
    </div>

    <div class="row">
        <div class="col">
            <div class="form-group">
                <label for="InputFirstNameUA"><fmt:message key="first.name.message"/>UA</label>
                <input id="InputFirstNameUA" type="text"
                       class="form-control <c:if test="${requestScope.firstNameUAError != null}">is-invalid</c:if>"
                       placeholder="<fmt:message key="placeholder.for.second.name.message"/>"
                       name="<%= Params.FIRST_NAME_UA %>"
                       value="<c:if test="${requestScope.user != null}">${requestScope.user.firstNameUA}</c:if>">
                <c:if test="${requestScope.firstNameUAError != null}">
                    <div class="invalid-feedback"><fmt:message key="${requestScope.firstNameUAError}"/></div>
                </c:if>
            </div>
        </div>

        <div class="col">
            <div class="form-group">
                <label for="InputFirstNameEN"><fmt:message key="first.name.message"/>EN</label>
                <input id="InputFirstNameEN" type="text"
                       class="form-control <c:if test="${requestScope.firstNameENError != null}">is-invalid</c:if>"
                       name="<%= Params.FIRST_NAME_EN %>"
                       placeholder="<fmt:message key="placeholder.for.first.name.message"/>"
                       value="<c:if test="${requestScope.user != null}">${requestScope.user.firstNameEN}</c:if>">
                <c:if test="${requestScope.firstNameENError != null}">
                    <div class="invalid-feedback"><fmt:message key="${requestScope.firstNameENError}"/></div>
                </c:if>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <div class="form-group">
                <label for="InputSecondNameUA"><fmt:message key="second.name.message"/>UA</label>
                <input id="InputSecondNameUA" type="text"
                       class="form-control <c:if test="${requestScope.secondNameUAError != null}">is-invalid</c:if>"
                       name="<%= Params.SECOND_NAME_UA %>"
                       placeholder="<fmt:message key="placeholder.for.second.name.message"/>"
                       value="<c:if test="${requestScope.user != null}">${requestScope.user.secondNameUA}</c:if>">
                <c:if test="${requestScope.secondNameUAError != null}">
                    <div class="invalid-feedback"><fmt:message key="${requestScope.secondNameUAError}"/></div>
                </c:if>
            </div>
        </div>

        <div class="col">
            <div class="form-group">
                <label for="InputSecondNameEN"><fmt:message key="second.name.message"/>EN</label>
                <input id="InputSecondNameEN" type="text"
                       class="form-control <c:if test="${requestScope.secondNameENError != null}">is-invalid</c:if>"
                       name="<%= Params.SECOND_NAME_EN %>"
                       placeholder="<fmt:message key="placeholder.for.second.name.message"/>"
                       value="<c:if test="${requestScope.user != null}">${requestScope.user.secondNameEN}</c:if>">
                <c:if test="${requestScope.secondNameENError != null}">
                    <div class="invalid-feedback"><fmt:message key="${requestScope.secondNameENError}"/></div>
                </c:if>
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