<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${page.totalPages > 0}">
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${page.totalPages}" step="1" varStatus="loop">
            <li class="page-item">
                <a class="page-link" href="?page=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
</c:if>