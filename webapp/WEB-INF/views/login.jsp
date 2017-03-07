<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="root" value="#{pageContext.request.contextPath }"/>
 <c:set var="loginUrl" value="/login"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>login page</title>
</head>
<body>
	<form id="loginfrm" name="loginfrm" method="POST" action="${root}${loginUrl}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<table>
			<tr>
				<td>ID</td>
				<td>
					<input type="text" id="loginid" name="loginid" />
				</td>
			</tr>
			<tr>
				<td>PWD</td>
				<td>
					<input type="password" id="loginpwd" name="loginpwd" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="loginbtn" value="로그인"/>
				</td>
			</tr>
		</table>
		<c:if test="${param.error == 1}">
			<p style="color:red;">잘못된 입력</p>
			<p style="color:red;">원인 : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
			<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
		</c:if>
	</form>
</body>
</html>