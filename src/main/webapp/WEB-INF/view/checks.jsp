<%@include file="parts/header.jsp" %>
<c:forEach items="${requestScope.checks}" var="check">

<div class="card-columns">
    <div class="card border-dark mt-4">
        <div class="card-header">
            <fmt:message key="check.number"/> ${check.id}
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><fmt:message key="name.message"/></th>
                    <th scope="col"><fmt:message key="product.price.message"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${check.products}" var="productIncheck" varStatus="loop" >
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${productIncheck.product.nameEN}</td>
                    <td>${productIncheck.product.price}</td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"><h6><fmt:message key="total.price.message"/></h6></td>
                    <td>${check.totalPrice}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</c:forEach>
<%@include file="parts/footer.jsp" %>