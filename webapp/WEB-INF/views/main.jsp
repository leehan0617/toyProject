<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <c:set var="root" value="#{pageContext.request.contextPath }"/>
 <c:set var="logoutUrl" value="/logout"/>
 <c:set var="updateMyInfoUrl" value="/updateMyInfo"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main page</title>
</head>
<body>
	main page
	<a href="${root}${logoutUrl}">logout</a>
	 <a href="${root}${updateMyInfoUrl}">정보수정</a>
	
	<br/>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		당신은 ADMIN 권한 입니다.
		        <a href="${root}/admin">admin 페이지로 가기</a>
		
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		당신은 USER 권한 입니다.
		        <a href="${root}/admin">admin 페이지로 가기</a>
		
	</sec:authorize>
	<br/>
	<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
		여기는 ADMIN 또는 USER 권한 영역입니다.
	</sec:authorize>
	    <sec:authorize access="isAnonymous()">
    <a href="/login" target="_self">Login</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/logout" target="_self">Logout</a>
    </sec:authorize>
	
	<div>
	${user}
	</div>
	
	<div>
		<span>채팅방이동</span>
		<a href="${root}/chatRoom">클릭</a>
	</div>
</body>
</html>