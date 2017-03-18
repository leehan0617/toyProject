<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="root" value="#{pageContext.request.contextPath }"/>
 <c:set var="addUrl" value="/save"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>add page</title>
</head>
<body>
	<form id="addfrm" name="addfrm" method="POST" action="${addUrl}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<table>
			<tr>
				<td>ID</td>
				<td>
					<input type="text" id="user_id" name="user_id" />
				</td>
			</tr>
			<tr>
				<td>NAME</td>
				<td>
					<input type="text" id="user_name" name="user_name" />
				</td>
			</tr>
			<tr>
				<td>PWD</td>
				<td>
					<input type="password" id="password" name="password" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="loginbtn" value="회원가입"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>