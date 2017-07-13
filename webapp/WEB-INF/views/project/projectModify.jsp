<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />
<style>
/* 부트스트랩 깨짐 설정 */
.checkbox-inline, .radio-inline{
	    padding-left: 20px;
}
</style>

<script type="text/javascript">
window.onload = function () {
 	
	var projectDep =${projectDep};
	var checkbox = document.getElementsByName("departCodeList");
	
	for (i = 0 ; i < checkbox.length ; i++) {
		for (j  = 0 ; j < projectDep.length ; j++) {
			if(checkbox[i].value == projectDep[j].depart_code){
				document.getElementById(checkbox[i].value).checked = true;
				document.getElementById(checkbox[i].value+"_count").style.display = "inline-table";
				document.getElementById(checkbox[i].value+"_select").options[projectDep[j].usercount-1].selected = true;
				document.getElementById(checkbox[i].value + "_select").name = "departMap["+checkbox[i].value+"]";
			}
		}
	 }
	
}
</script>

<div class="container">
	<form id="updatefrm" method="POST" action="${root}/project/update/${projectDetail.getProject_id()}">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
	<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
	<input type='hidden' id = 'rootValue'  value = "${root}">
	<input type="hidden" id="projectDep" value="${projectDep}"/>
	<div class="well" style="background-color: #FFFFFF">
		<table class="table table-hover" style="background-color: #FFFFFF">
			<tr>
				<th scope="row" class="bg-gray">프로젝트 생성 이름</th>
				<td colspan="3"><input class="form-control" name = "project_name" id ="project_name" type="text" value="${projectDetail.getProject_name()}"/></td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">담당자</th>
				<td colspan="3"><input type="hidden" name="manager_id" value="${projectDetail.getManager_id()}"/>${projectDetail.getManager_name()}</td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">모집기간</th>
				<td><input class="form-control" type="date" id="recurit_start_date" value="${projectDetail.getRecruit_start_date()}" name = "recruit_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" value="${projectDetail.getRecruit_end_date()}" id="recruit_end_date" name = "recruit_end_date" data-date-format="YYYY-MM-DD"/></td>
				<th scope="row" class="bg-gray">모집상태</th>
				<td>
					<c:forEach begin="0" var="i" items="${recruitList}" end="${recruitList.size()}">
						<div class="abc-radio abc-radio-danger radio-inline">
						    <c:set var="ck" value=""></c:set>	
						    <c:if test="${projectDetail.getRecruit_state_code() == i.getState_code()}">
						    	 <c:set var="ck" value="checked"></c:set>	
						    </c:if>
							<input type="radio" id="${i.getState_code()}" ${ck} name="recruit_state_code" value="${i.getState_code()}"/>
							<label for="${i.getState_code()}">
								${i.getState_name()}
							</label>
						</div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">프로젝트기간</th>
				<td><input class="form-control" type="date" id="project_start_date" value="${projectDetail.getProject_start_date()}" name = "project_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" value="${projectDetail.getProject_end_date()}" id="project_end_date" name = "project_end_date" data-date-format="YYYY-MM-DD"/></td>
				<th scope="row" class="bg-gray">프로젝트상태</th>
				<td>
					<c:forEach begin="0" var="i" items="${projectStateList}" end="${projectStateList.size()}">
						<div class="abc-radio abc-radio-danger radio-inline">	
						    <c:set var="ck" value=""></c:set>	
						    <c:if test="${projectDetail.getProject_state_code() == i.getState_code()}">
						    	 <c:set var="ck" value="checked"></c:set>	
						    </c:if>	
							<input type="radio" id="${i.getState_code()}" ${ck} name="project_state_code" value="${i.getState_code()}"/>
							<label for="${i.getState_code()}">
								${i.getState_name()}
							</label>
						</div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">모집포지션</th>
				<td colspan="3">
				  <c:forEach begin="0" var="i" items="${departList}" end="${departList.size()}">
						<div class="abc-checkbox abc-checkbox-danger checkbox-inline">	
							<input type="checkbox" id="${i.getDepart_code()}" name="departCodeList" value="${i.getDepart_code()}" onchange="javascript:project.departCount(this)"/>
							<label for="${i.getDepart_code()}">
								${i.getDepart_name()}
							</label>
						</div>
						<div id="${i.getDepart_code()}_count" style="display:none;margin-right:20px;">
							<select class="form-control" id="${i.getDepart_code()}_select">
								<c:forEach begin="1" end="10" var="i"> <!-- 직무별 인원수 정하기 --> 
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
						</div>
				   </c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">프로젝트상세설명</th>
				<td colspan="3">
					<textarea class="form-control" id="project_detail" name="project_detail">
						${projectDetail.getProject_detail()}
					</textarea>
				</td>
			</tr>
		</table>
		</div>
		<div class="divfloat">
			<input class="btn btn-default" type="button" onclick="project.projectList()" value="목록"/>
			<sec:authentication property='principal.username' var="loginId"/>
			<c:set var="ManagerId" value="${projectDetail.getManager_id()}"></c:set>
			
			<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 수정/삭제 가능 -->
				<input  style="border: 2px solid #E95420; color:#E95420;" class = "btn btn-default btn-sm"  type="submit" value="수정" />
	<%-- 			<input type="button" value="삭제" onclick="javascript:project.projectDelete(${projectDetail.getProject_id()})"/> onclick="javascript:project.projectUpdate(${projectDetail.getProject_id()})"  --%>
			</c:if>
		</div>
	</form>	
</div>
