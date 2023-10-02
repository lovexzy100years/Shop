<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="${page.url}1">首页</a>
<c:if test="${page.curPage > 1}">
    <a href="${page.url}${page.curPage-1}">上一页</a>
</c:if>
<c:if test="${page.curPage < page.totalPage}">
    <a href="${page.url}${page.curPage+1}">下一页</a>
</c:if>
<a href="${page.url}${page.totalPage}">尾页</a>