<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />

<div class="container">
		<form action="${root}" method="POST">
		<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
		<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
		<input type = 'hidden' id = 'rootValue'  value = "${root}">
		<input type = 'hidden' name = 'project_id'  value = "${projectDetail.getProject_id()}">
		<div style="background-color: #FFFFFF">
			<div class="page-header" >
				<h2 style="word-wrap:break-word">${projectDetail.getProject_name()}</h2>      
			</div>
			<sec:authentication property='principal.username' var="loginId"/>
			<c:set var="ManagerId" value="${projectDetail.getManager_id()}"></c:set>
				<table class="table table-hover">
					<tr>
						<th scope="row" class="bg-gray">담당자</th>
						<td>${projectDetail.getManager_name()}</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">모집 기간 </th>
						<td>
							<c:set var = "labelColor" value="label label-pill label-warning"></c:set>
							<input type = 'hidden' name = 'recruit_start_date' value = "${projectDetail.getRecruit_start_date()}">
							<input type = 'hidden' name = 'recruit_end_date'  value = "${projectDetail.getRecruit_end_date()}">
							${projectDetail.getRecruit_start_date()} ~ ${projectDetail.getRecruit_end_date()}
							<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 시작/종료 가능 -->
								<c:choose>
								    <c:when test="${empty projectDetail.getRecruit_state_code()}">
								    	<span class="${labelColor}"> ${projectDetail.getRecruit_state_name()} </span>
										<button type="button" name="radius" class="btn btn-danger" onclick = "project.recruitState('recruiting')">
											시작
										</button>
									</c:when>
									<c:when test="${projectDetail.getRecruit_state_code() == 'recruiting'}">
										<c:set var = "labelColor" value="label label-pill label-success"></c:set>
								    	<span class="${labelColor}"> ${projectDetail.getRecruit_state_name()} </span>
										<button type="button" name="radius" class="btn btn-danger" onclick = "project.recruitState('complete')">
											종료
										</button>
									</c:when>
									<c:otherwise>
										<c:set var = "labelColor" value="label label-pill label-info"></c:set>
										<span class="${labelColor}"> ${projectDetail.getRecruit_state_name()} </span>
									</c:otherwise>
								</c:choose>
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">프로젝트 기간</th>
						<td>
							<c:set var = "labelColor" value="label label-pill label-warning"></c:set>
							<input type = 'hidden' name = 'project_start_date'  value = "${projectDetail.getProject_start_date()}">
							<input type = 'hidden' name = 'project_end_date'  value = "${projectDetail.getProject_end_date()}">
							${projectDetail.getProject_start_date()} ~ ${projectDetail.getProject_end_date()}
							<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 시작/종료 가능 -->
								<c:choose>
									<c:when test="${empty projectDetail.getProject_state_code()}">
								    	<span class="${labelColor}"> ${projectDetail.getProject_state_name()} </span>
										<button type="button" name="radius" class="btn btn-danger" onclick = "project.projectState('start')">
											시작
										</button>
									</c:when>
									<c:when test="${projectDetail.getProject_state_code() == 'start'}">
										<c:set var = "labelColor" value="label label-pill label-success"></c:set>
								    	<span class="${labelColor}"> ${projectDetail.getProject_state_name()} </span>
										<button type="button" name="radius" class="btn btn-danger" onclick = "project.projectState('end')">
											종료
										</button>
									</c:when>
									<c:otherwise>
										<c:set var = "labelColor" value="label label-pill label-info"></c:set>
										<span class="${labelColor}"> ${projectDetail.getProject_state_name()} </span>
									</c:otherwise>
								</c:choose>
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">모집포지션</th>
						<td>
								 <c:forEach begin="0" var="j" items="${projectDepList}" end="${projectDepList.size()}">
									${j.getDepart_name()} - ${j.getUsercount()}
								 </c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">프로젝트상세설명</th>
						<td>
							${projectDetail.getProject_detail()}
						</td>
					</tr>
				</table>
		</div>
		</form>	
			<div class="divfloat">
				<input class="btn btn-default" type="button" onclick="project.projectList()" value="목록"/>
				<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 수정/삭제 가능 -->
					<input style="border: 2px solid #d9534f; color:#d9534f;" class="btn btn-default" type="submit" value="수정" onclick="project.projectModify(${projectDetail.getProject_id()})"/>
			<%-- 		<input type="submit" value="삭제" onclick="project.projectDelete(${projectDetail.getProject_id()})"/> --%>
				</c:if>
				
				<c:if test= "${!empty projectDetail.getRecruit_state_code()}">
					<c:if test="${projectDetail.getApplyyn().equals('Y') }"><!-- 이미 내가 신청한 프로젝트 이면 다시 신청 못함 -->
						<input style="border: 2px solid #d9534f; color:#d9534f;" class = "btn btn-default btn-sm" type="button" value="신청" onclick="javascript:project.applyOpen(${projectDetail.getProject_id()},'${projectDetail.getProject_name()}')"/>
					</c:if>
				</c:if>
			</div>	
			<!-- 프로젝트 신청하기 팝업 -->
			<div id= "registDiv" style="display: none;">
			<jsp:include page="projectApply.jsp" flush="false"></jsp:include>
			</div>
			<!-- //프로젝트 신청하기 팝업 -->
</div>