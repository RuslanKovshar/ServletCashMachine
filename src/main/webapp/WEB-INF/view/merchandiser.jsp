<%@include file="parts/header.jsp" %>

<button class="btn btn-primary btn-block" type="button"
        data-toggle="collapse"
        data-target="#collapseExample"
        aria-expanded="false"
        aria-controls="collapseExample">
    <fmt:message key="create.new.product.message"/>
</button>
<br>

<c:if test="${requestScope.productExist}">
    <div class="alert alert-danger" role="alert"><fmt:message key="product.exist.message"/></div>
</c:if>


<div class="collapse <c:if test="${requestScope.productDTO != null}">show</c:if>" id="collapseExample">
    <form action="${pageContext.request.contextPath}/api/merchandiser/product" method="post" class="form-group">

        <label for="inputName"><fmt:message key="name.message"/></label>
        <input id="inputName" type="text" name="<%= Params.NAME %>"
               class="form-control <c:if test="${requestScope.nameError != null}">is-invalid</c:if>"
               value="<c:if test="${requestScope.productDTO != null}">${requestScope.productDTO.name}</c:if>">
        <c:if test="${requestScope.nameError != null}">
            <div class="invalid-feedback"><fmt:message key="${requestScope.nameError}"/></div>
        </c:if>

        <label for="inputCode"><fmt:message key="code.message"/></label>
        <input id="inputCode" type="text" name="<%= Params.CODE %>"
               class="form-control <c:if test="${requestScope.codeError != null}">is-invalid</c:if>"
               value="<c:if test="${requestScope.productDTO != null}">${requestScope.productDTO.code}</c:if>">
        <c:if test="${requestScope.codeError != null}">
            <div class="invalid-feedback"><fmt:message key="${requestScope.codeError}"/></div>
        </c:if>

        <label for="inputPrice"><fmt:message key="product.price.message"/></label>
        <input id="inputPrice" type="text" name="<%= Params.PRICE %>"
               class="form-control <c:if test="${requestScope.priceError != null}">is-invalid</c:if>"
               value="<c:if test="${requestScope.priceError != null}">${requestScope.productDTO.price}</c:if>">
        <c:if test="${requestScope.priceError != null}">
            <div class="invalid-feedback"><fmt:message key="${requestScope.priceError}"/></div>
        </c:if>

        <label for="inputCount"><fmt:message key="count.on.stock.message"/></label>
        <input id="inputCount" type="number" name="<%= Params.COUNT_ON_STOCK %>"
               class="form-control <c:if test="${requestScope.countOfProductError != null}">is-invalid</c:if>"
               value="<c:if test="${requestScope.countOfProductError != null}">${requestScope.productDTO.code}</c:if>">
        <c:if test="${requestScope.countOfProductError != null}">
            <div class="invalid-feedback"><fmt:message key="${requestScope.countOfProductError}"/></div>
        </c:if>

        <label for="inputType"><fmt:message key="type.message"/></label>
        <select id="inputType" name="<%= Params.TYPE %>"
                class="form-control">
            <option value="PIECE_PRODUCT"><fmt:message key="count.product.message"/></option>
            <option value="PRODUCT_BY_WEIGHT"><fmt:message key="weight.product.message"/></option>
        </select>

        <button type="submit" class="btn btn-danger btn-block mt-2"><fmt:message key="create.btn.message"/></button>
    </form>
</div>

<button class="btn btn-primary btn-block" type="button"
        data-toggle="collapse"
        data-target="#collapseExample1"
        aria-expanded="false"
        aria-controls="collapseExample">
    <fmt:message key="add.products.to.stock.message"/>
</button>
<div class="collapse <c:if test="${requestScope.productNotFound}">show</c:if>" id="collapseExample1">
    <form action="${pageContext.request.contextPath}/api/merchandiser/stock" method="post" class="form-group">

        <label for="inputName1"><fmt:message key="input.code.or.name.message"/></label>
        <input id="inputName1" type="text" class="form-control
               <c:if test="${requestScope.productNotFound}">is-invalid</c:if>"
               name="<%= Params.NAME %>">

        <c:if test="${requestScope.productNotFound}">
            <div class="invalid-feedback"><fmt:message key="product.not.found.message"/></div>
        </c:if>


        <label for="inputCount1"><fmt:message key="product.count.message"/></label>
        <input id="inputCount1" type="number" name="<%= Params.COUNT_ON_STOCK %>" class="form-control" required>

        <button type="submit"
                class="btn btn-lg btn-danger btn-block mt-2"><fmt:message key="create.btn.message"/></button>
    </form>
</div>

<%@include file="parts/footer.jsp" %>