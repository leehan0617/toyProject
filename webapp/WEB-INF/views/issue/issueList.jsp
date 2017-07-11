<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${root}/css/project/projectPopup.css" />
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
<input type = "hidden" id = "projectName" value="${projectName}">
<input type = "hidden" id = "viewMyIssueFlag" value="${viewMyIssueFlag}">

<input id = 'rootValue' type = 'hidden' value = "${root}">
<br>


<div  class="container" style = 'background-color:white; width:1000px; height:80%; padding:20px; '>
<div class="page-header">
	<div style="overflow: hidden; text-overflow: ellipsis; white-space:nowrap; word-wrap:normal; width:950px;"><h2 style ="margin-left:10px;">${projectName}</h2></div>      
</div>
<div>
<select id = "stateSearch"  class="form-control" style = "width:120px; display:inline-block; margin-left:20px;" onchange="issue.issueSearch(${projectId})" >
<option value = "all">��ü</option>
<option value = "wait">���</option>
<option value = "start">����</option>
<option value = "stop">����</option>
<option value = "restart">�����</option>
<option value = "end">����</option>
</select>

<input id = "chUserId" type = "checkbox" class="styled" value="${myId}" onclick = "issue.issueSearch(${projectId})" style="display:inline-block; margin-left:10px">�� �̽��� ����
</div>
<br>


<div style = "margin-left:0px; width:500px; display:inline-block;margin-bottom:300px;">

	<table class="table table-bordered" style = 'background-color:white; width:500px;height:80%;'>
		<c:forEach var ="issue" items = "${issueList}" begin = "0" end ="${issueList.size()}">
		<tr>
			<td><a href = "javascript:detailIssue.selectIssueDetail('${issue.issue_id}')">${issue.issue_name}</a></td>
		</tr>
		</c:forEach>
	</table>
<br>
<div style = "margin-top:5px; height:36px;"><button onclick = 'issue.addIssuePopUp(${projectId})' class = "btn btn-default" style = "float:right; border: 2px solid #d9534f; color:#d9534f;" >�̽�����</button></div>
<div style="text-align: center;">
	<jsp:include page="../common/paging.jsp" flush="true">
		<jsp:param name = "count" value = "${count}"/>
		<jsp:param name = "seq" value = "${seq}"/>
		<jsp:param name = "pageCount" value = "${pageCount}"/>
		<jsp:param name = "nextPage" value = "${nextPage}"/>
		<jsp:param name = "prevPage" value = "${prevPage}"/>
		<jsp:param name = "nowBlockFirst" value = "${nowBlockFirst}"/>
		<jsp:param name = "nowBlockLast" value = "${nowBlockLast}"/>
	</jsp:include>
</div>
</div>

<div id ='issueDetailPopup' style = 'display:none; width:30%; vertical-align:top; float:right;'>
			
			 <table class="table table-bordered">
		  	 	<tr>
		  	 		<td>����</td><td><span id= 'issueState'></span><input type = 'hidden' id = "now_state"><button id = 'issueStateBtn' type = 'button' class="btn btn-xs btn-danger" onclick="issue.issueStateChange()" style="margin-left:10px">����</button></td>
		  	 	</tr>
		  	 	<tr>
		  	 		<td>������</td><td><span id= 'issueInfo'></span></td>
		  	 	</tr>
		  	 	<tr>
		  	 		<td>�����</td>
		  	 		<td><span id= 'issueRegId'></span></td>
		  	 	</tr>
		  	 	<tr>
		  	 		<td>�Ⱓ</td>
		  	 		<td><span id= 'issueStartDate'></span>~<span id= 'issueEndDate'></span></td>
		  	 	</tr>
		  	 </table>
			<br>
			
			<div style = "float:right; margin-right:20px; margin-bottom:10px;">
			<button onclick="issue.addUpdateIssuePopup()" style="border: 2px solid #d9534f; color:#d9534f;" class = "btn btn-default btn-sm">����</button>
			<button style="border: 2px solid #d9534f; color:#d9534f;" class = "btn btn-default btn-sm" id = 'issueDelete' type = 'button' onclick = 'issue.deleteStart(${projectId})'>����</button>
			</div>
</div>

</div>

<div id = 'issuePopup' style = 'display:none;' class="dim-layer">
	<div class="pop-layer">
		<div class="pop-container">
		<button type="button" class="btn btn-default" onclick ='issue.closeIssue()' style = "float:right; border:0px;">
		    <span class="glyphicon glyphicon-remove"></span>
	  	</button>
		  	
		<div class="pop-conts">
		<div class="page-header" >
			<h2 style ="margin-left:10px">�̽�����</h2>      
		</div>
			
		<form  method = "post" id = "addIssueForm" action="/issue/add">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type = 'hidden' id = 'rootValue'  value = "${root}">
		<input type = "hidden" id = 'issue_id' name = 'issue_id'>
		<input type = "hidden" id = "projectId" name = "project_id" value="${projectId}"/>
		<input type = "hidden" id = "projectName" name = "projectName">
		<table class="table table-bordered">
	  	 	<tr>
	  	 		<td>���� </td><td><input name = 'issue_name' type = 'text'></td>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>������</td>
	  	 		<td>
	  	 		<input type="hidden" id = "reg_id" name = "reg_id" value = "${myId}">
				<c:forEach var = "member" items = "${memberList}" begin = "0" end = "${memberList.size()}">
					<c:if test="${member.user_id != myId}">
						<input type="checkbox" id ="${member.user_id}" name="userList" value="${member.user_id}">${member.user_id}
					</c:if>
				</c:forEach>
			</td>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>���۳�¥</td>
	  	 		<td><input type = "date" name = "start_date"></td>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>���ᳯ¥</td>
	  	 		<td><input type = "date" name = "end_date"></td>
	  	 	</tr>
	  	 </table>
		�󼼳��� :<br>
		<textarea  name = "issue_detail" class="form-control" ></textarea><br>
		<input type = 'submit' value = "����" style="border: 2px solid #d9534f; color:#d9534f; float:right; margin-bottom:10px; margin-right:5px;" class = "btn btn-default btn-sm" >
		</form>
		</div>
		</div>
	</div>
</div>



<div id = 'issueUpdatePopUp' style = 'display:none;'  class="dim-layer">
	<div class="pop-layer">
		<div class="pop-container">
			<button type="button" class="btn btn-default" onclick ='issue.closeUpdateIssue()' style = "float:right; border:0px;">
			    <span class="glyphicon glyphicon-remove"></span>
		  	</button>
		  	<div class="pop-conts">
		  	<div class="page-header" >
				<h2 style ="margin-left:10px">�̽�����</h2>      
			</div>

			<form  method = "post" id = "updateIssueForm">
			 <table class="table table-bordered">
		  	 	<tr>
		  	 		<td>���� </td><td><input id = 'updateIssueTitle' name = 'issue_name' type = 'text'></td>
		  	 	</tr>
		  	 	<tr>
		  	 		<td>������</td>
		  	 		<td>
		  	 		<c:set var ="myId" value='${myInfo.username}'/>
						<input type="hidden" id = "reg_id" name = "reg_id" value = "${myId}">
						<c:forEach var = "member" items = "${memberList}" begin = "0" end = "${memberList.size()}">
						<c:if test="${member.user_id != myId}">
							<input type="checkbox" name="userList2" value="${member.user_id}">${member.user_id}
						</c:if>
					</c:forEach>
				</td>
		  	 	</tr>
		  	 	<tr>
		  	 		<td>���۳�¥</td>
		  	 		<td><input type = "date" name = "start_date" id = "updateStartDate"></td>
		  	 	</tr>
		  	 	<tr>
		  	 		<td>���ᳯ¥</td>
		  	 		<td><input type = "date" name = "end_date" id = "updateEndDate"></td>
		  	 	</tr>
		  	 </table>
			<br>
			
			<input type = "hidden" id = 'issue_id2' name = 'issue_id'>
			<input type = 'hidden'  id = "now_state2" name = "state_code">
			�󼼳��� :<br>
			<textarea id = 'updateDetail' name = "issue_detail" class="form-control"></textarea><br>
			<input type = 'button' value = "����" onclick = "issue.updateIssue(${projectId})" style="border: 2px solid #d9534f; color:#d9534f; float:right;" class = "btn btn-default btn-sm" >
			</form>
			</div>
		</div>
	</div>
</div>


</body>
</html>