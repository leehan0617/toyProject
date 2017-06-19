<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
<input type = 'hidden' id = 'rootValue'  value = "${root}">

<!-- 프로젝트 리스트 불러오기 -->
<div style="display: inline-table;float:left;">
	<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
		<ul style="border: 1px solid" onclick="project.memberList(${i.getProject_id()})">
			<dt> 프로젝트명 : ${i.getProject_name()} </dt>
			<li style="list-style:square;">기간</li>
			<li style="list-style:square;">모집직무 :
				<c:forEach var="departMap" items="${i.getDepartMap()}">
					${departMap.key} - ${departMap.value} 명
				</c:forEach>
			</li>
			<li style="list-style:square;">담당자 : ${i.getManager_name()}</li>
			<li style="list-style:square;">모집상태 : ${i.getState_name()}</li>
		</ul>
	</c:forEach>
</div>

<!-- 프로젝트 인원리스트 불러오기 -->
<div style="display: inline-table;float:right;">
	<input type="text" id="project_id" name= "project_id" value="">
	<table>
		<tbody id = "memberTable">
			<tr id="memberTr" style="display: none">
				<td>하하</td>
				<td>
					<input type="text" id="user_id" name= "user_id" value="">
					<input type="button" onclick = "javascript:project.infoDetailOpen(this)" value="정보" />
					<input type="button" id="acceptBtn" onclick = "javascript:project.apprMember('accept',this)" value="수락" />
					<input type="button" id="rejectBtn" onclick = "javascript:project.apprMember('reject',this)" value="거절" />
				</td>
			</tr>
		</tbody>	
	</table>
</div>

<!-- 프로젝트 사람정보 팝업 -->
<div id= "infoDetailDiv" style="display: none;">
<jsp:include page="projectMemberDetail.jsp" flush="false"></jsp:include>
</div>
<!-- //프로젝트 사람정보 팝업 -->