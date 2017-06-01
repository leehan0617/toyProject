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
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script type="text/javascript" src="${root}/js/issue/issue.js" charset="utf-8"></script>
이슈리스트페이지

<br>
<button onclick = 'issue.addIssuePopUp()'>이슈생성</button>
<%-- <button onclick="window.open('${root}/issue/add','이슈생성','width=430,height=500,location=no,status=no,scrollbars=yes');">이슈생성</button> --%>

<div id = 'issuePopup' style = 'display:none; width:500px; height:400px; border:1px solid black' >
이슈제목 : <input id = 'issueTitle' type = 'text'><br>
참여자 : <select id = 'memberSelect'></select><br>
상세내용 :<br>
<textarea id = 'issueDetail'></textarea><br>

</div>
</body>
</html>