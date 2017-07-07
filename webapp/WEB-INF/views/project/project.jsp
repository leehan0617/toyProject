<!-- 전체 프로젝트 리스트 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />
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
	<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
	<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
	<input type = 'hidden' id = 'rootValue'  value = "${root}">
	<!-- form 전송 - 검색 조건 -->
	<form id="projectform" method="get" action="${root}/project">
	<input type="hidden" id="manager_id" name="manager_id" value="<sec:authentication property='principal.username'/>">
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
								<input type="checkbox"  class="styled" id="${i.getState_code()}" name="recruitCodeList" value="${i.getState_code()}"/>
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
						<input class="form-control" type="date" id="recurit_start_date"  value= "${paramDto.getRecruit_start_date()}" name = "recruit_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" id="recruit_end_date" name = "recruit_end_date" value= "${paramDto.getRecruit_end_date()}"  data-date-format="YYYY-MM-DD"/>
					</td>
					<th scope="row" class="bg-gray">프로젝트기간</th>
					<td>
						<input class="form-control" type="date" id="project_start_date"  value= "${paramDto.getProject_start_date()}" name = "project_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" id="project_end_date" name = "project_end_date" value= "${paramDto.getProject_end_date()}" data-date-format="YYYY-MM-DD"/>
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
					<td  colspan="2">
						<input class="form-control" type="text" id="project_name"  name = "project_name" value="${paramDto.getProject_name()}" />
					</td>
					<td>
						<button type="button" class="btn btn-default" onclick ="project.searchList()">검색</button>
						<!-- 관리자 권한 이 있는 사람만 자기가 등록한 프로젝트 필터 가능 -->
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<div class="abc-checkbox checkbox-inline" style="float: right">
								<c:choose>
									 <c:when test= "${!empty paramDto.getManager_id()}">
										<input type="checkbox" id="myCheck" name="myCheck" checked onchange="project.myPjoectList(this)"/>
									 </c:when>
									 <c:otherwise>
									 	<input type="checkbox" id="myCheck" name="myCheck" onchange="project.myPjoectList(this)"/>
									 </c:otherwise>
							    </c:choose>
							    <label for="myCheck">
							    	내가 등록한 프로젝트 보기
							    </label>
						    </div>
						</sec:authorize>
						<!-- //관리자 권한 이 있는 사람만 자기가 등록한 프로젝트 필터 가능 -->
					</td>	
				</tr>
			</tbody>
		</table>
		<!-- // 검색조건 테이블 -->
	</form>
	
	<!-- 프로젝트 리스트 불러오기 -->
	<div class="row">
		<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
			<div class="col-md-4" style="height: 350px;"> 
				<div id = "listpanel" class="panel"  style="height: 90%;">
					<div style="padding: 10px 15px;">
						<strong><h2>${i.getProject_name()}</h2></strong>
					</div>
					<div class="panel-body">
							<dl class="dl-horizontal" style="border-left: 5px solid #eee;">
								<dt><strong>모집기간 : </strong> </dt>
								<dd>
									 ${i.getRecruit_start_date()} ~ ${i.getRecruit_end_date()}
								</dd>
								<dt><strong>프로젝트기간  </strong>  </dt>
								<dd>
									${i.getProject_start_date()} ~ ${i.getProject_end_date()}
								</dd>
								<dt><strong>모집직무 :</strong> </dt>
								<dd>
									<c:forEach var="departMap" items="${i.getDepartMap()}">
										${departMap.key} - ${departMap.value} 명
									</c:forEach>
								</dd>
								<dt><strong>담당자 : </strong></dt>
								<dd>${i.getManager_name()}</dd>
								<dt><strong>모집상태 : </strong></dt>
								<dd>
									<c:set var = "labelColor" value="label label-pill label-warning"></c:set>
									<c:if test="${!empty i.getRecruit_state_code()}">
										<c:set var = "labelColor" value="label label-pill label-success"></c:set>
									</c:if>
									<c:if test="${i.getRecruit_state_code() == 'complete'}">
										<c:set var = "labelColor" value="label label-pill label-info"></c:set>
									</c:if>
									<span class="${labelColor}">
									 	${i.getRecruit_state_name()} 
									</span>
								</dd>
								<dt><strong>프로젝트상태 : </strong></dt>
								<dd>
									<c:set var = "labelColor" value="label label-pill label-warning"></c:set>
									<c:if test="${!empty i.getProject_state_code()}">
										<c:set var = "labelColor" value="label label-pill label-success"></c:set>
									</c:if>
									<c:if test="${i.getProject_state_code() == 'end'}">
										<c:set var = "labelColor" value="label label-pill label-info"></c:set>
									</c:if>
									<span class="${labelColor}"> 
										${i.getProject_state_name()} 
									</span>
								</dd>
							</dl>
					</div>
					<div class = "panel-footer" style="background-color: white;">
						<button type="button" class="btn btn-default" onclick="javascript:project.projectDetail(${i.getProject_id()})">상세보기 </button>
						 <c:if test= "${!empty i.getRecruit_state_code()}">
							 <c:choose>
								<c:when  test="${i.getApplyyn().equals('Y') }"><!-- 이미내가 신청한 프로젝트 이면 다시 신청 못함 -->
									<button type="button"  style="border: 2px solid #d9534f; color:#d9534f;" class = "btn btn-default btn-sm" onclick="javascript:project.applyOpen(${i.getProject_id()},'${i.getProject_name()}')">신청하기</button>
								</c:when>
								<c:otherwise>
									<label for="warning" class="label label-danger">신청완료</label>
								</c:otherwise>
							</c:choose>
						</c:if>	
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- //프로젝트 리스트 불러오기-->
	
	<div style="display: inline;">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
					<br/><a href="${root}/project/new"> <label for="warning" style="float: right" class="btn btn-danger">프로젝트 생성하기</label></a><br/>		
		</sec:authorize>
	</div>
	
	<!-- 프로젝트 신청하기 팝업 -->
	<div id= "registDiv" style="display: none;">
		<jsp:include page="projectApply.jsp" flush="false"></jsp:include>
	</div>
	<!-- //프로젝트 신청하기 팝업 -->

</div>