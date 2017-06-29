<!-- 전체 프로젝트 리스트 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${root}/js/dist/bootstrap.css" />

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

<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
<input type = 'hidden' id = 'rootValue'  value = "${root}">
<!-- form 전송 - 검색 조건 -->
<form id="projectform" method="get" action="${root}/project">
<input type="hidden" id="manager_id" name="manager_id" value="<sec:authentication property='principal.username'/>">
<input type="hidden" id="user_id" name="user_id" value="<sec:authentication property='principal.username'/>">
<div style="display: inline;"  class="container">
	<!-- 검색조건 테이블 -->
	<table class="table">
		<thead>
			<tr>
				<th colspan="2">검색조건</th>
			</tr>
			</thead>
		<tbody>
			<tr>
				<td>모집상태</td>
				<td>
					<c:forEach var="i" items="${recruitList}" begin="0" end="${recruitList.size()}">
						<input type="checkbox" id="${i.getState_code()}" name="recruitCodeList" value="${i.getState_code()}"/>${i.getState_name()}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>프로젝트상태</td>
				<td>
					<c:forEach var="i" items="${projectStateList}" begin="0" end="${projectStateList.size()}">
						<input type="checkbox" id="${i.getState_code()}" name="projectCodeList" value="${i.getState_code()}"/>${i.getState_name()}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>모집기간</th>
				<td><input type="date" id="recurit_start_date"  name = "recruit_start_date" data-date-format="YYYY-MM-DD"/> ~ <input type="date" id="recruit_end_date" name = "recruit_end_date" data-date-format="YYYY-MM-DD"/></td>
			</tr>
			<tr>
				<th>프로젝트기간</th>
				<td><input type="date" id="project_start_date"  name = "project_start_date" data-date-format="YYYY-MM-DD"/> ~ <input type="date" id="project_end_date" name = "project_end_date" data-date-format="YYYY-MM-DD"/></td>
			</tr>
			<tr>
				<td>직무별로 검색</td>
				<td>
					<c:forEach var="i" items="${departList}" begin="0" end="${departList.size()}">
						<input type="checkbox" id="${i.getDepart_code()}" name="departCodeList" value = "${i.getDepart_code()}"/>${i.getDepart_name()}
					</c:forEach>
				</td>
			</tr>
				<!-- 관리자 권한 이 있는 사람만 자기가 등록한 프로젝트 필터 가능 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
						<tr> 
							<td>
								<c:choose>
								 <c:when test= "${!empty paramDto.getManager_id()}">
									<input type="checkbox" id="myCheck" name="myCheck" checked onchange="project.myPjoectList(this)"/>내가 등록한 프로젝트 보기
								 </c:when>
								 <c:otherwise>
								 	<input type="checkbox" id="myCheck" name="myCheck" onchange="project.myPjoectList(this)"/>내가 등록한 프로젝트 보기${manager_id}
								 </c:otherwise>
							    </c:choose>
							</td>
						</tr>	
			</sec:authorize>
			<tr>
				<td>
					<input type="button" onclick ="project.searchList()" value="검색">
				</td>			
			</tr>
		</tbody>
	</table>
	<!-- // 검색조건 테이블 -->
</div>
</form>

<!-- 프로젝트 리스트 불러오기 -->
<div style="display: inline-table;">
	<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
		<ul style="border: 1px solid">
						<dt>프로젝트명 : ${i.getProject_name()} </dt>
						<li style="list-style:square;">모집기간 ${i.getRecruit_start_date()} ~ ${i.getRecruit_end_date()}</li>
						<li style="list-style:square;">프로젝트기간  ${i.getProject_start_date()} ~ ${i.getProject_end_date()}</li>
						<li style="list-style:square;">모집직무 :
							<c:forEach var="departMap" items="${i.getDepartMap()}">
								${departMap.key} - ${departMap.value} 명
							</c:forEach>
						</li>
						<li style="list-style:square;">담당자 : ${i.getManager_name()}</li>
						<li style="list-style:square;">모집상태 : ${i.getRecruit_state_name()}</li>
						<li style="list-style:square;"><input type="button" value="상세보기" onclick="javascript:project.projectDetail(${i.getProject_id()})"/> ${i.getRecruit_state_code()}</li>
						 <c:if test= "${!empty i.getRecruit_state_code()}">
						 <li style="list-style:square;">
							 <c:choose>
								<c:when  test="${i.getApplyyn().equals('Y') }"><!-- 이미내가 신청한 프로젝트 이면 다시 신청 못함 -->
									<input type="button" value="신청하기" onclick="javascript:project.applyOpen(${i.getProject_id()})"/>
								</c:when>
								<c:otherwise>
									신청완료
								</c:otherwise>
							</c:choose>
						 </li>
						</c:if>	
						
		</ul>
	</c:forEach>
</div>
<!-- //프로젝트 리스트 불러오기-->

<div style="display: inline;">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
				<br/><a href="${root}/project/new">프로젝트 생성하기</a><br/>		
	</sec:authorize>
</div>

<!-- 프로젝트 신청하기 팝업 -->
<div id= "registDiv" style="display: none;">
<jsp:include page="projectApply.jsp" flush="false"></jsp:include>
</div>
<!-- //프로젝트 신청하기 팝업 -->