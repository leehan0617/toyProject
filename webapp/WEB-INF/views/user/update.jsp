<%-- 회원 수정 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="#{pageContext.request.contextPath }"/>
<c:set var="updateUrl" value="/user/${userDto.getUsername()}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	회원수정페이지
	<form action="${root}${updateUrl}" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<table>
			<tr>
				<td>ID</td>
				<td>${userDto.getUsername()}</td>
			</tr>
			<tr>
				<td>PASSWORD</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td>NAME</td>
				<td>${userDto.getUser_name()}</td>
			</tr>
			<tr>
				<td>EMAIL</td>
				<td><input type="text" name="email" value="${userDto.getEmail()}"/></td>
			</tr>
			<tr>
				<td>DEPARTMENT</td>
				<td><input type="text" name="depart_code" value="${userDto.getDepart_code()}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"/></td>
			</tr>
		</table>
	</form>
</body>
</html>