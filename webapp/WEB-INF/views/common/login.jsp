<%-- 로그인 페이지 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="#{pageContext.request.contextPath }"/>
<c:set var="loginUrl" value="/login"/>
<c:set var="joinUrl" value="/user/join"/>
<%-- -<c:url var="login" value="${root}/auth/login_check?targetUrl=${targetUrl}" />
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>로그인페이지</title>
</head>
<body>
	jenkins success ssss
	<c:choose>
		<c:when test="${logout != null}">
			<h3>로그아웃 성공</h3>
		</c:when>
		<c:when test="${error != null }">
			<h3>로그인 실패 : ${error}</h3>
			<h3>원인 : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h3>
			<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
		</c:when>
	</c:choose>
		
	<form name="loginForm" method="POST" action="${root}${loginUrl}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
		<table>
			<tr>
				<td>ID</td>
				<td>
					<input type="text" name="loginId"/>
				</td>
			</tr>
			<tr>
				<td>PWD</td>
				<td>
					<input type="password" name="loginPwd"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">Remember Me : <input type="checkbox" name="remember-me"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="로그인"/></td>
				<td><a href="${root}${joinUrl}">회원가입</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
