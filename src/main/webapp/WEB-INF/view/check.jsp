<%@include file="parts/header.jsp" %>

<form action="${pageContext.request.contextPath}/api/check/product" method="post" class="form-group">
    <label for="inputName"><fmt:message key="input.code.or.name.message"/></label>
    <div class="row">
        <div class="col-8">
            <input id="inputName" type="text" name="name" class="form-control <c:if test="${requestScope.error}">is-invalid</c:if>">
            <c:if test="${requestScope.error}">
                <div class="invalid-feedback"><fmt:message key="product.not.found.message"/></div>
            </c:if>
        </div>
        <div class="col-4">
            <button class="btn btn-success btn-block"><fmt:message key="search.product.message"/></button>
        </div>
    </div>
</form>

<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="product.name.message"/></th>
        <th scope="col"><fmt:message key="product.count.message"/></th>
        <th scope="col"><fmt:message key="product.price.message"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.check.products}" var="productInCheck">
        <tr>
            <td>
                <div class="row">
                    <div class="col">
                            ${productInCheck.product.name}
                    </div>
                    <div class="col">
                        <c:if test="${sessionScope.user.isSeniorCashier()}">
                            <form action="${pageContext.request.contextPath}/api/check/remove_product" method="post">
                                <input type="hidden" value="${productInCheck.product.name}" name="name">
                                <button type="submit" class="btn btn-small btn-danger">
                                    <fmt:message key="remove.product.message"/>
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </td>
            <td>
                <c:if test="${productInCheck.product.type == 'PIECE_PRODUCT'}">
                    X${productInCheck.value}
                </c:if>
                <c:if test="${productInCheck.product.type == 'PRODUCT_BY_WEIGHT'}">
                    ${productInCheck.value} <fmt:message key="gram.message"/>
                </c:if>
            </td>
            <td>${productInCheck.price}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2"><h6><fmt:message key="total.price.message"/></h6></td>
        <td><h6>${sessionScope.check.totalPrice}</h6></td>
    </tr>
    </tbody>
</table>

<form action="${pageContext.request.contextPath}/api/close_check" method="post">
    <button class="btn btn-lg btn-success" type="submit"><fmt:message key="close.check.message"/></button>
</form>
<%@include file="parts/footer.jsp" %>