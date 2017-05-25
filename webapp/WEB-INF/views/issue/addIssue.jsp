<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
이슈제목 : <input id = 'issueTitle' type = 'text'><br>
참여자 : <select id = 'memberSelect'></select><br>
상세내용 :<br>
<textarea id = 'issueDetail'></textarea><br>

<input type = 'button' value = '이슈생성'> <input type = 'button' value = '취소' onclick = 'window.close()'>
</body>
</html>