<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<ul class="pagination">
		<li><a href="javascript:issuePage.goPaging('${url}', ${prevPage}, ${pageCount})"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
		<c:forEach var = "i" begin = "${nowBlockFirst}" end = "${nowBlockLast}" step="1">
			<c:choose>
				<c:when test="${i eq seq}">
					<li class="disabled"><a href="#"  style="background-color:#FF3333 !important; color:white !important">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li ><a href="javascript:issuePage.goPaging('${url}', ${i}, ${pageCount})">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li><a href="javascript:issuePage.goPaging('${url}', ${nextPage}, ${pageCount})"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
	</ul>
</div>