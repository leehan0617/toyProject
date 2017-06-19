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
<input type = "hidden" name="csrf_token" value="${_csrf.token}"/>
<input  type = "hidden" name="_csrf_header" value="${_csrf.headerName}"/>

<input id = 'rootValue' type = 'hidden' value = "${root}">
이슈리스트페이지
<br>
프로젝트 전체 이슈 
<div>
</div>
<c:forEach var ="issue" items = "${issueList}" begin = "0" end ="${issueList.size()}">
	<span><a href = "javascript:detailIssue.selectIssueDetail('${issue.issue_id}', '${issue.issue_name}', '${issue.issue_detail}', '${issue.state_name}', '${issue.issue_start_date}', '${issue.issue_end_date}', '${issue.reg_id}', '${issue.state_code}')">${issue.issue_name}</a></span><br>
</c:forEach>

<br>
<button onclick = 'issue.addIssuePopUp(${projectId})'>이슈생성</button>
<%-- <button onclick="window.open('${root}/issue/add','이슈생성','width=430,height=500,location=no,status=no,scrollbars=yes');">이슈생성</button> --%>

<div id = 'issuePopup' style = 'display:none; width:500px; height:400px; border:1px solid black' >
<form  method = "post" id = "addIssueForm">

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type = "hidden" id = "projectId" name = "project_id" value="${projectId}"/>
이슈제목 : <input id = 'issueTitle' name = 'issue_name' type = 'text'><br>
참여자 : 
<c:set var ="myId" value="${myInfo.user_id}"/>
<input type="hidden" id = "reg_id" name = "reg_id" value = "${myId}">
<c:forEach var = "member" items = "${memberList}" begin = "0" end = "${memberList.size()}">
	<c:if test="${member.user_id != myId}">
		<input type="checkbox" name="member_name" value="${member.user_id}">${member.user_id}
	</c:if>
</c:forEach>
<br>
상세내용 :<br>
<textarea id = 'issueDetail' name = "issue_detail"></textarea><br>
<input type = 'button' value = "생성" onclick = "issue.issueSave(${projectId})">
<input type = 'button' value='닫기' onclick ='issue.closeIssue()'>
</form>
</div>

<div id ='issueDetailPopup' style = 'width:500px; height:400px; display:none;  border:1px solid black'>
<input type = "hidden" id = 'issue_id'>
이슈상세내용
상태:<span id= 'issueState'></span><input type = 'hidden' id = "now_state"><br>
상세정보:<span id= 'issueInfo'></span><br>
담당자:<span id= 'issueRegId'></span><br>
기간:<span id= 'issueStartDate'></span><br>~<span id= 'issueEndDate'></span><br>
<input id = 'issueStateBtn' type = 'button' value = '이슈시작' onclick="issue.issueStateChange()">
<br>
<input id = 'issueModify' type = 'button' value = '이슈수정'>
<input id = 'issueDelete' type = 'button' value = '이슈삭제' onclick = 'issue.deleteStart(${projectId})'>
</div>
</body>
</html>