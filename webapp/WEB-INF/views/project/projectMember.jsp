<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
<input type = 'hidden' id = 'rootValue'  value = "${root}">
<style>
/* 부트스트랩 깨짐 설정 */
.checkbox-inline, .radio-inline{
	    padding-left: 20px;
}
</style>
<script type="text/javascript">
window.onload = function () {
 	
	var departCodeList =${departCodeList};//직무별로 검색
	var recruitCodeList =${recruitCodeList};//모집상태검색
	var projectCodeList =${projectCodeList};//프로젝트상태검색
	
	var departCodecheckbox = document.getElementsByName("departCodeList");//직무별로 검색
	var recruitCodeListcheckbox = document.getElementsByName("recruitCodeList");//모집상태검색
	var projectCodeListcheckbox = document.getElementsByName("projectCodeList");//프로젝트상태검색
	
	for (i = 0 ; i < departCodecheckbox.length ; i++) {
		if(departCodeList){
			for (j  = 0 ; j < departCodeList.length ; j++) {
				if(departCodecheckbox[i].value == departCodeList[j]){
					document.getElementById(departCodecheckbox[i].value).checked = true;
				}
			}
		}
	 }
	
	for (i = 0 ; i < recruitCodeListcheckbox.length ; i++) {
		if(recruitCodeList){
			for (j  = 0 ; j < recruitCodeList.length ; j++) {
				if(recruitCodeListcheckbox[i].value == recruitCodeList[j]){
					document.getElementById(recruitCodeListcheckbox[i].value).checked = true;
				}
			}
		}
	 }
	
	for (i = 0 ; i < projectCodeListcheckbox.length ; i++) {
		if(projectCodeList){
			for (j  = 0 ; j < projectCodeList.length ; j++) {
				if(projectCodeListcheckbox[i].value == projectCodeList[j]){
					document.getElementById(projectCodeListcheckbox[i].value).checked = true;
				}
			}
		}
	 }
	
}
</script>
<div class="container">
		<!-- form 전송 - 검색 조건 -->
		<form id="projectform" method="get" action="${root}/project/my/project/1">
		<input type="hidden" id="manager_id" value="<sec:authentication property='principal.username'/>">
		<input type="hidden" id="user_id" name="user_id" value="<sec:authentication property='principal.username'/>">
			<!-- 검색조건 테이블 -->
			<table class="table table-hover" style="background-color: #FFFFFF">
				<thead>
					<tr>
						<th colspan="4">검색조건</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row" class="bg-gray">모집상태</th>
						<td>
							<c:forEach var="i" items="${recruitList}" begin="0" end="${recruitList.size()}">
								<div class="abc-checkbox checkbox-inline">
									<input type="checkbox" class="styled" id="${i.getState_code()}" name="recruitCodeList" value="${i.getState_code()}"/>
									<label for="${i.getState_code()}">
										${i.getState_name()}
									</label>
								</div>
							</c:forEach>
						</td>
						<th scope="row" class="bg-gray">프로젝트상태</th>
						<td>
							<c:forEach var="i" items="${projectStateList}" begin="0" end="${projectStateList.size()}">
								<div class="abc-checkbox checkbox-inline">	
									<input type="checkbox" id="${i.getState_code()}" name="projectCodeList" value="${i.getState_code()}"/>
									<label for="${i.getState_code()}">
										${i.getState_name()}
									</label>
								</div>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">모집기간</th>
						<td>
							<input class="form-control" type="date" id="recurit_start_date"   value= "${paramDto.getRecruit_start_date()}" name = "recruit_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" id="recruit_end_date" name = "recruit_end_date" value= "${paramDto.getRecruit_end_date()}"  data-date-format="YYYY-MM-DD"/>
						</td>
						<th scope="row" class="bg-gray">프로젝트기간</th>
						<td>
							<input class="form-control" type="date" id="project_start_date"  value= "${paramDto.getProject_start_date()}"  name = "project_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" id="project_end_date" name = "project_end_date" value= "${paramDto.getProject_end_date()}" data-date-format="YYYY-MM-DD"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">직무</th>
						<td colspan="3">
							<c:forEach var="i" items="${departList}" begin="0" end="${departList.size()}">
								<div class="abc-checkbox checkbox-inline">	
									<input type="checkbox" id="${i.getDepart_code()}" name="departCodeList" value = "${i.getDepart_code()}"/>
									<label for="${i.getDepart_code()}">
										${i.getDepart_name()}
									</label>
								</div>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">제목</th>
						<td colspan="2">
							<input type="text" class="form-control" id="project_name"  name = "project_name" value="${paramDto.getProject_name()}"/>
						</td>
						<td>
							<button type="submit" class="btn btn-default">검색</button>
						</td>	
					</tr>
				</tbody>
			</table>
			<!-- // 검색조건 테이블 -->
		</form>
		<!-- 프로젝트 리스트 불러오기 -->
		<div>
			<sec:authentication property='principal.username' var="loginId"/>
			<div class="panel-group">
			<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
				<div id = "listpanel" class="panel panel-danger">
					<div class="panel-heading" style="background-color: #E95420;color:#FFFFFF">${i.getProject_name()}</div>
					<div class="panel-body">
						<ul class = "list-group">
							<li class = "list-group-item">
								<strong>모집기간 : </strong>
								${i.getRecruit_start_date()} ~ ${i.getRecruit_end_date()}
							</li>
							<li class = "list-group-item">
								<strong>모집상태 : </strong>
								${i.getRecruit_state_name()}
							</li>
							<li class = "list-group-item">
								<strong>프로젝트기간 : </strong>
								${i.getProject_start_date()} ~ ${i.getProject_end_date()}
							</li>
							<li class = "list-group-item">
								<strong>프로젝트상태 : </strong>
								${i.getProject_state_name()}
							</li>
							<li class = "list-group-item">
								<strong>담당자 : </strong>
								${i.getManager_name()}
							</li>
							<li class = "list-group-item">
								<strong>모집직무 :</strong>
								<c:forEach var="departMap" items="${i.getDepartMap()}">
									${departMap.key} - ${departMap.value} 명
								</c:forEach>
							</li>
							<li class = "list-group-item">
								<strong>프로젝트상세설명 : </strong>
								${i.getProject_detail()}
							</li>
						</ul>
					</div>
					<div class = "panel-footer">
						<c:choose>
							<c:when test= "${i.getManager_id() == loginId}">
								<button type="button" style="border: 2px solid #E95420; color:#E95420;" class = "btn btn-default btn-sm" onclick="project.memberList(${i.getProject_id()})">관리 </button>
							</c:when>
							<c:otherwise>
								<c:set var = "labelColor" value="label label-pill label-default"></c:set>
								<c:if test="${i.getState_code() == 'accept'}">
									<c:set var = "labelColor" value="label label-pill label-info"></c:set>
								</c:if>
								<c:if test="${i.getState_code() == 'refuse'}">
									<c:set var = "labelColor" value="label label-pill label-danger"></c:set>
								</c:if>
								<span class="${labelColor}"> 
									${i.getState_name()}
								</span>
							</c:otherwise>
						</c:choose>
						<button type="button" style="margin-left: 10px;border: 2px solid #E95420; color:#E95420;" class = "btn btn-default btn-sm" onclick="location.href='${root}/issue/detail/${i.getProject_id()}/${i.getProject_name()}/1'">이슈 </button>
					</div>
					<div class="container-fluid" id="panel_${i.getProject_id()}">
						<div id = "memberTable">
							<ul class="admin" id="memberTr" style="display: none">
								<li style="display:block"></li>
								<li style="display:block">
									<input type="hidden" id="project_id" name= "project_id" value="">
									<input type="hidden" id="user_id" name= "user_id" value="">
									<button class="btn btn-default" type="button" onclick = "javascript:project.infoDetailOpen(this)" > 정보</button>
									<c:forEach var="i" items="${stateList}" begin="0" end="${stateList.size()}">
										<c:if test = "${!i.getState_code().equals('apply')}">
											<button class="btn btn-default" type="button" id="${i.getState_code()}btn" onclick = "javascript:project.apprMember('${i.getState_code()}',this)" >${i.getState_name()}</button>
										</c:if>
									</c:forEach>
								</li>
							</ul>
						</div>	
					</div>
				</div>
				<br>
				<br>
			</c:forEach>
				<!-- 프로젝트 인원리스트 불러오기 -->
			</div>
			
				<!-- 페이징-->
				<div style="text-align: center;">
					<jsp:include page="../common/paging.jsp" flush="true">
						<jsp:param name = "count" value = "${paramDto.getCount()}"/>
						<jsp:param name = "seq" value = "${seq}"/>
						<jsp:param name = "pageCount" value = "${pageCount}"/>
						<jsp:param name = "nextPage" value = "${nextPage}"/>
						<jsp:param name = "prevPage" value = "${prevPage}"/>
						<jsp:param name = "nowBlockFirst" value = "${nowBlockFirst}"/>
						<jsp:param name = "nowBlockLast" value = "${nowBlockLast}"/>
					</jsp:include>
				</div>
		</div>
	
		<!-- 프로젝트 사람정보 팝업 -->
		<div id= "infoDetailDiv" style="display: none;">
		<jsp:include page="projectMemberDetail.jsp" flush="false"></jsp:include>
		</div>
		<!-- //프로젝트 사람정보 팝업 -->
</div>