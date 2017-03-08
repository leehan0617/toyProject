<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="root" value="#{pageContext.request.contextPath }"/>
 <c:set var="logoutUrl" value="/logout"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main page</title>
</head>
<body>
	main page
	<a href="${root}${logoutUrl}">logout</a>
</body>
</html>