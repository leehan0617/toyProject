<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<form action="${root}" method="POST">
<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
<input type = 'hidden' id = 'rootValue'  value = "${root}">
<input type="hidden" id="projectDep" value="${projectDep}"/>
	<table>
		<tr>
			<th>프로젝트 생성 이름</th>
			<td>${projectDetail.getProject_name()}</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${projectDetail.getManager_name()}</td>
		</tr>
		<tr>
			<th>모집 기간 </th>
			<td>${projectDetail.getRecruit_start_date()} ~ ${projectDetail.getRecruit_end_date()}</td>
		</tr>
		<tr>
			<th>프로젝트 기간</th>
			<td>${projectDetail.getProject_start_date()} ~ ${projectDetail.getProject_end_date()}</td>
		</tr>
		<tr>
			<th>모집포지션</th>
			<td>
					 <c:forEach begin="0" var="j" items="${projectDepList}" end="${projectDepList.size()}">
						${j.getDepart_name()} - ${j.getUsercount()}
					 </c:forEach>
			</td>
		</tr>
		<tr>
			<th>프로젝트상세설명</th>
			<td>
				${projectDetail.getProject_detail()}
			</td>
		</tr>
	</table>
</form>	
	<input type="button" onclick="project.projectList()" value="목록"/>
	
	<sec:authentication property='principal.username' var="loginId"/>
	<c:set var="ManagerId" value="${projectDetail.getManager_id()}"></c:set>

	<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 수정/삭제 가능 -->
		<input type="submit" value="수정" onclick="project.projectModify(${projectDetail.getProject_id()})"/>
		<input type="submit" value="삭제" onclick="project.projectDelete(${projectDetail.getProject_id()})"/>
	</c:if>
	
	<c:if test= "${!empty projectDetail.getRecruit_state_code()}">
		<c:if test="${projectDetail.getApplyyn().equals('Y') }"><!-- 이미 내가 신청한 프로젝트 이면 다시 신청 못함 -->
			<input type="button" value="신청" onclick="javascript:project.applyOpen(${projectDetail.getProject_id()})"/>
		</c:if>
	</c:if>
	
<!-- 프로젝트 신청하기 팝업 -->
<div id= "registDiv" style="display: none;">
<jsp:include page="projectApply.jsp" flush="false"></jsp:include>
</div>
<!-- //프로젝트 신청하기 팝업 -->
	
