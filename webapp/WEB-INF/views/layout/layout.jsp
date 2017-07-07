<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<tiles:importAttribute name="stylesheets"/>

<!DOCTYPE html>
<html>
<c:forEach var="css" items="${stylesheets}">
     <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
</c:forEach>
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1">
</head>

<body>
	<div id="wrap">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
