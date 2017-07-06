<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<span><sec:authentication property='principal.username'/>님(나)의 프로젝트 리스트</span>

<br>
	<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
		<span>
			프로젝트명 :<a href="${root}/issue/detail/${i.getProject_id()}/${i.getProject_name()}"> ${i.getProject_name()}</a>
		</span><br>
	</c:forEach>

<br>
</body>
</html>