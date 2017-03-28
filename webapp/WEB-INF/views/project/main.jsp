<%-- project main page --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>  
<c:set var="logoutUrl" value="/logout"/>  
<c:set var="updateUrl" value="/user/update"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span>${user.getUsername()} 님</span>
	<form action="${root}${logoutUrl}" method="POST">
		<input type="submit" value="로그아웃"/>
	</form>
	<a href="${root}${updateUrl}">정보수정</a>
	프로젝트 메인 페이지 입니다.
	
	<div>
		${user}
	</div>
</body>
</html>