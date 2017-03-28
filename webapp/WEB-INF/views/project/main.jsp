<%-- project main page --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>  
<c:set var="logoutUrl" value="/logout"/>  
<c:set var="adminUrl" value="/admin"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
	<c:set var="updateUrl" value="/user/update/${userDto.getUsername()}"/>
	<span>${userDto.getUsername()} 님</span>
	<h3>당신의 권한</h3>
	<c:forEach var="i" items="${userDto.getAuthorities()}" begin="0" end="${userDto.getAuthorities().size()}">
		<span>ID : ${i.getUser_id()}</span>
		<span>AUTH : ${i.getAuth_code()}</span>
		<c:if test="${i.getAuth_code().equals('ROLE_ADMIN') }">
			<br/><a href="${root}${adminUrl}">관리자 페이지</a><br/>		
		</c:if>
	</c:forEach>
	<form action="${root}${logoutUrl}" method="POST">
		<input type="submit" value="로그아웃"/>
	</form>
	<a href="${root}${updateUrl}">정보수정</a>
	</sec:authorize>
	<br/>
	
	프로젝트 메인 페이지 입니다.
</body>
</html>