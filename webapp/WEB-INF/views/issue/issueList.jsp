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
<c:set var ="myId" value='${myInfo.username}'/>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script type="text/javascript" src="${root}/js/issue/issue.js" charset="utf-8"></script>
<input type = "hidden" name="csrf_token" value="${_csrf.token}"/>
<input  type = "hidden" name="_csrf_header" value="${_csrf.headerName}"/>
<input type = "hidden" id = "nowSt" value="${state_code}">
<input id = 'rootValue' type = 'hidden' value = "${root}">
이슈리스트페이지
<br>
프로젝트 전체 이슈  

<select id = "stateSearch">
<option value = "all">전체</option>
<option value = "wait">대기</option>
<option value = "start">시작</option>
<option value = "stop">중지</option>
<option value = "restart">재시작</option>
<option value = "end">종료</option>
</select>

<input type = "button" value = "검색" onclick = "issue.issueSearch(${projectId})">
<input type = "checkbox">내 이슈만 보기

<div>
</div>
<c:forEach var ="issue" items = "${issueList}" begin = "0" end ="${issueList.size()}">
	<span><a href = "javascript:detailIssue.selectIssueDetail('${issue.issue_id}')">${issue.issue_name}</a></span><br>
</c:forEach>

<br>
<button onclick = 'issue.addIssuePopUp(${projectId})'>이슈생성</button>
<%-- <button onclick="window.open('${root}/issue/add','이슈생성','width=430,height=500,location=no,status=no,scrollbars=yes');">이슈생성</button> --%>

<div id = 'issuePopup' style = 'display:none; width:500px; height:400px; border:1px solid black' >
<form  method = "post" id = "addIssueForm">
<input type = "hidden" id = 'issue_id' name = 'issue_id'>
<input type = "hidden" id = "projectId" name = "project_id" value="${projectId}"/>
이슈제목 : <input name = 'issue_name' type = 'text'><br>
참여자 : 

<input type="hidden" id = "reg_id" name = "reg_id" value = "${myId}">
<c:forEach var = "member" items = "${memberList}" begin = "0" end = "${memberList.size()}">
	<c:if test="${member.user_id != myId}">
		<input type="checkbox" name="userList" value="${member.user_id}">${member.user_id}
	</c:if>
</c:forEach>
<br>
시작날짜:<input type = "date" name = "start_date">
종료날짜:<input type = "date" name = "end_date"><br>
상세내용 :<br>
<textarea  name = "issue_detail"></textarea><br>
<input type = 'button' value = "생성" onclick = "issue.issueSave(${projectId})">
<input type = 'button' value='닫기' onclick ='issue.closeIssue()'>
</form>
</div>

<div id ='issueDetailPopup' style = 'width:500px; height:400px; display:none;  border:1px solid black'>

이슈상세내용<br>
이슈제목:<span id= 'issueName'></span><br>
상태:<span id= 'issueState'></span><input type = 'hidden' id = "now_state"><br>
상세정보:<span id= 'issueInfo'></span><br>
담당자:<span id= 'issueRegId'></span><br>
기간:<span id= 'issueStartDate'></span>~<span id= 'issueEndDate'></span><br>
<input id = 'issueStateBtn' type = 'button' value = '이슈시작' onclick="issue.issueStateChange()">
<br>
<input id = 'issueModify' type = 'button' value = '이슈수정' onclick="issue.addUpdateIssuePopup()">
<input id = 'issueDelete' type = 'button' value = '이슈삭제' onclick = 'issue.deleteStart(${projectId})'>
</div>

<div id = 'issueUpdatePopUp' style = 'display:none; width:500px; height:400px; border:1px solid black' >
이슈수정<br>
<form  method = "post" id = "updateIssueForm">
<input type = "hidden" id = 'issue_id2' name = 'issue_id'>
<input type = 'hidden'  id = "now_state2" name = "state_code">
이슈제목 : <input id = 'updateIssueTitle' name = 'issue_name' type = 'text'><br>
참여자 : 
<c:set var ="myId" value='${myInfo.username}'/>
<input type="hidden" id = "reg_id" name = "reg_id" value = "${myId}">
<c:forEach var = "member" items = "${memberList}" begin = "0" end = "${memberList.size()}">
	<c:if test="${member.user_id != myId}">
		<input type="checkbox" name="userList2" value="${member.user_id}">${member.user_id}
	</c:if>
</c:forEach>
<br>
시작날짜:<input type = "date" name = "start_date" id = "updateStartDate">
종료날짜:<input type = "date" name = "end_date" id = "updateEndDate"><br>
상세내용 :<br>
<textarea id = 'updateDetail' name = "issue_detail"></textarea><br>
<input type = 'button' value = "수정" onclick = "issue.updateIssue(${projectId})">
<input type = 'button' value='닫기' onclick ='issue.closeUpdateIssue()'>
</form>
</div>
</body>
</html>