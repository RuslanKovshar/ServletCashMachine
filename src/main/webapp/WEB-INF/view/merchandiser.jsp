<%@include file="parts/header.jsp" %>

<button class="btn btn-primary btn-block" type="button"
        data-toggle="collapse"
        data-target="#collapseExample"
        aria-expanded="false"
        aria-controls="collapseExample">
    <fmt:message key="create.new.product.message"/>
</button>
<br>

<div class="collapse <#if createProductDTO??>show</#if>" id="collapseExample">
    <form action="${pageContext.request.contextPath}/api/merchandiser/product" method="post" class="form-group">

        <div class="row">
            <div class="col">
                <label for="inputNameUA"><fmt:message key="name.message"/>UA</label>
                <input id="inputNameUA" type="text" name=<%= RequestParams.NAME_UA %>
                       class="form-control">
        </div>
        <div class="col">
            <label for="inputNameEN"><fmt:message key="name.message"/>EN</label>
            <input id="inputNameEN" type="text" name=<%= RequestParams.NAME_EN %>
                   class="form-control">
</div>
</div>

<label for="inputCode"><fmt:message key="code.message"/></label>
<input id="inputCode" type="text" name=<%= RequestParams.CODE %>
       class="form-control">

<label for="inputPrice"><fmt:message key="product.price.message"/></label>
<input id="inputPrice" type="text" name=<%= RequestParams.PRICE %>
       class="form-control">

<label for="inputCount"><fmt:message key="count.on.stock.message"/></label>
<input id="inputCount" type="text" name=<%= RequestParams.COUNT_ON_STOCK %>
       class="form-control">

<label for="inputType"><fmt:message key="type.message"/></label>
<select id="inputType" name=<%= RequestParams.TYPE %>
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
<div class="collapse <#if notFound!false>show</#if>" id="collapseExample1">
    <form action="${pageContext.request.contextPath}/api/merchandiser/stock" method="post" class="form-group">

        <label for="inputName1"><fmt:message key="input.code.or.name.message"/></label>
        <input id="inputName1" type="text" name=<%= RequestParams.NAME %> class="form-control">

        <%--<#if notFound!false>
        <div class="invalid-feedback"><@spring.message "product.not.found.message"/></div>
    </#if>--%>

    <label for="inputCount1"><fmt:message key="product.count.message"/></label>
    <input id="inputCount1" type="number" name=<%= RequestParams.COUNT_ON_STOCK %> class="form-control" required>

    <button type="submit"
            class="btn btn-lg btn-danger btn-block mt-2"><fmt:message key="create.btn.message"/></button>
    </form>
</div>

<%@include file="parts/footer.jsp" %>