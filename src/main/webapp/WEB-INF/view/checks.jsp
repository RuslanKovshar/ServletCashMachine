<%@include file="parts/header.jsp" %>
<%@include file="parts/pagination.jsp"%>
<div class="card-columns">
    <c:forEach items="${requestScope.page.content}" var="check">
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
                    <c:forEach items="${check.products}" var="productIncheck" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${productIncheck.product.name}</td>
                            <td>${productIncheck.product.price}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="2"><h6><fmt:message key="total.price.message"/></h6></td>
                        <td>${check.totalPrice}</td>
                    </tr>
                    </tbody>
                </table>
                <form action="${pageContext.request.contextPath}/api/cancel_check?id=${check.id}"
                      method="post">
                    <button type="submit">Cancel</button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

<%@include file="parts/footer.jsp" %>