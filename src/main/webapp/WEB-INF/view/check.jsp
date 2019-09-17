<%@include file="parts/header.jsp" %>

<form action="${pageContext.request.contextPath}/api/check/product" method="post" class="form-group">

    <label for="inputName"><fmt:message key="input.code.or.name.message"/></label>
    <div class="row">
        <div class="col-8">
            <input id="inputName" type="text" name="name" class="form-control <%--<#if notFound>is-invalid</#if>--%>">
            <%--            <#if notFound>
                        <div class="invalid-feedback"><fmt:message key="product.not.found.message"/></div>
                    </#if>--%>
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
            <td>${productInCheck.product.nameUA}</td>
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