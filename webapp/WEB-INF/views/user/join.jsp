<%-- 회원가입 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="#{pageContext.request.contextPath }"/>
<c:set var="joinUrl" value="/user/join"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	join page
	<form action="${root}${joinUrl}" method="POST" onsubmit="test();">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>PASSWORD</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>EMAIL</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td>DEPARTMENT</td>
				<td>
					<select name="departCode">
						<option>-</option>
						<option value="dev">개발</option>
						<option value="sal">영업</option>
						<option value="art">디자인</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입"/></td>
			</tr>
		</table>
	</form>
</body>
</html>