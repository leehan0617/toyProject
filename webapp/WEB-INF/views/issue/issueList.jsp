<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="csrf-token" content="${_csrf.token}"/>
<meta  name="csrf-token" content="${_csrf.headerName}"/>

<title>Insert title here</title>
</head>
<body>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script type="text/javascript" src="${root}/js/issue/issue.js" charset="utf-8"></script>
<input id = 'rootValue' type = 'hidden' value = "${root}">
�̽�����Ʈ������
<br>
<c:forEach var ="issue" items = "${issueList}" begin = "0" end ="${issueList.size()}">
	<span>${issue.issue_name}</span><br>
</c:forEach>

<br>
<button onclick = 'issue.addIssuePopUp(${projectId})'>�̽�����</button>
<%-- <button onclick="window.open('${root}/issue/add','�̽�����','width=430,height=500,location=no,status=no,scrollbars=yes');">�̽�����</button> --%>

<div id = 'issuePopup' style = 'display:none; width:500px; height:400px; border:1px solid black' >
<form  method = "post" id = "addIssueForm">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type = "hidden" id = "projectId" />
�̽����� : <input id = 'issueTitle' type = 'text'><br>
������ : 
<c:set var ="myId" value="${myInfo.user_id}"/>
<c:forEach var = "member" items = "${memberList}" begin = "0" end = "${memberList.size()}">
	<c:if test="${member.user_id != myId}">
		<input type="checkbox" name="member_name" value="${member.user_id}">${member.user_id}
	</c:if>
</c:forEach>
<br>
�󼼳��� :<br>
<textarea id = 'issueDetail'></textarea><br>
<input type = 'submit' value = "����"><button>�ݱ�</button>
</form>
</div>
</body>
</html>