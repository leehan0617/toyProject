<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${root}/js/common/common.js" charset="utf-8"></script>
<div>
	<ul class="pagination">
		<li><a style="color: #E95420;" href="javascript:common.goPaging('${url}', ${prevPage}, ${pageCount})"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
		<c:forEach var = "i" begin = "${nowBlockFirst}" end = "${nowBlockLast}" step="1">
			<c:choose>
				<c:when test="${i eq seq}">
					<li class="disabled"><a href="#"  style="color:#aea79f !important;background-color:#f5f5f5">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li ><a href="javascript:common.goPaging('${url}', ${i}, ${pageCount})" style="color:#E95420 !important;">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li><a style="color: #E95420;"  href="javascript:common.goPaging('${url}', ${nextPage}, ${pageCount})"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
	</ul>
</div>