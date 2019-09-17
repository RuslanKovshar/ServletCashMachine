<%@include file="parts/header.jsp" %>


<form action="${pageContext.request.contextPath}/api/check/add_product" method="post">
    <div class="row">
        <div class="col-4">
            <h2><em><fmt:message key="name.message"/></em></h2>
        </div>
        <div class="col-8">
            <h2>${sessionScope.product.nameEN}</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-4">
            <c:if test="${sessionScope.product.type == 'PIECE_PRODUCT'}">
                <h2><em><fmt:message key="price.for.one.product.message"/></em></h2>
            </c:if>
            <c:if test="${sessionScope.product.type == 'PRODUCT_BY_WEIGHT'}">
                <h2><em><fmt:message key="price.for.one.kg.message"/></em></h2>
            </c:if>
        </div>
        <div class="col-8">
            <h2>${sessionScope.product.price}</h2>
        </div>
    </div>

    <c:if test="${sessionScope.product.type == 'PIECE_PRODUCT'}">
        <label for="inputNumber"><fmt:message key="input.count.message"/></label>
    </c:if>
    <c:if test="${sessionScope.product.type == 'PRODUCT_BY_WEIGHT'}">
        <label for="inputNumber"><fmt:message key="input.weight.message"/></label>
    </c:if>

<div class="row">
    <div class="col-8">
        <input type="number" id="inputNumber" name="number" min="1" required
               class="form-control <%--<#if error>is-invalid</#if>--%>">
        <%--<#if error>
        <div class="invalid-feedback"><fmt:message key="not.enough.product.message"/></div>
    </#if>--%>
    </div>
    <div class="col-4">
        <button class="btn btn-success btn-block"><fmt:message key="add.product.message"/></button>
    </div>
</div>
</form>

<%@include file="parts/footer.jsp" %>