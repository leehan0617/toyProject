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

<span><sec:authentication property='principal.username'/>��(��)�� ������Ʈ ����Ʈ</span>

<br>
	<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
		<span>
			������Ʈ�� :<a href="${root}/issue/detail/${i.getProject_id()}/${i.getProject_name()}"> ${i.getProject_name()}</a>
		</span><br>
	</c:forEach>

<br>
</body>
</html>