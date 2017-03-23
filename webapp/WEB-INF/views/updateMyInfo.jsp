<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <c:set var="root" value="#{pageContext.request.contextPath }"/>
  <c:set var="updateUrl" value="/update"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMyInfopagess</title>
</head>
<body>
	<div>
		<form id="addfrm" name="addfrm" method="POST" action="${root}${updateUrl}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<table>
				<tr>
					<td>ID</td>
					<td>
						 <input type="text" id="user_id" name="user_id" value="${user}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>NAME</td>
					<td>
						<input type="text" id="user_name" name="user_name" value="${memberDto.user_name}"/>
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
						<input type="submit" id="UpdateMyInfobtn" value="수정"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>