<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div>
<c:set var="root" value="#{pageContext.request.contextPath}"/>

�̽����� : <input id = 'issueTitle' type = 'text'><br>
������ : <select id = 'memberSelect'></select><br>
�󼼳��� :<br>
<textarea id = 'issueDetail'></textarea><br>

<input type = 'button' value = '�̽�����'> <input type = 'button' value = '���' onclick = 'window.close()'>
</div>
</body>
</html>