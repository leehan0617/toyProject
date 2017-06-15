<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
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

<div>
	<form id="updatefrm" >
	<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
	<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
	<input type = 'hidden' id = 'rootValue'  value = "${root}">
	<input type="hidden" id="projectDep" value="${projectDep}"/>
		<table>
			<tr>
				<th>프로젝트 생성 이름</th>
				<td><input name = "project_name" id ="project_name" type="text" value="${projectDetail.getProject_name()}"/></td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>${projectDetail.getManager_name()}</td>
			</tr>
			<tr>
				<th>기간</th>
				<td><input type="date"/></td>
			</tr>
			<tr>
				<th>모집포지션</th>
				<td>
				  <c:forEach begin="0" var="i" items="${departList}" end="${departList.size()}">
						<input type="checkbox" id="${i.getDepart_code()}" name="departCodeList" value="${i.getDepart_code()}" onchange="javascript:project.departCount(this)"/> ${i.getDepart_name()}
							<div id="${i.getDepart_code()}_count" style="display:none;">
								<select id="${i.getDepart_code()}_select">
									<c:forEach begin="1" end="10" var="i"> <!-- 직무별 인원수 정하기 --> 
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>
							</div>
				   </c:forEach>
				</td>
			</tr>
			<tr>
				<th>프로젝트상세설명</th>
				<td>
					<textarea id="project_detail" name="project_detail">
						${projectDetail.getProject_detail()}
					</textarea>
				</td>
			</tr>
		</table>
		<input type="button" onclick="project.projectList()" value="목록"/>
		<c:set var="loginId" value="${user.getUsername()}"></c:set>
		<c:set var="ManagerId" value="${projectDetail.getManager_id()}"></c:set>
		
		<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 수정/삭제 가능 -->
			<input type="button" value="수정" onclick="javascript:project.projectUpdate(${projectDetail.getProject_id()})" />
			<input type="button" value="삭제" onclick="javascript:project.projectDelete(${projectDetail.getProject_id()})"/>
		</c:if>
	</form>	
</div>
