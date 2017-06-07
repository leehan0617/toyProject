<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${root}/js/project/project.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
window.onload = function () {
 	
	let projectDep =${projectDep};
	let checkbox = document.getElementsByName("departcodeList");
	
	for (let i of checkbox) {
		for (let j of projectDep) {
			if(i.value == j.depart_code){
				document.getElementById(i.value).checked = true;
				document.getElementById(i.value+"_count").style.display = "inline-table";
				document.getElementById(i.value+"_select").options[j.usercount-1].selected = true;
			}
		}
	 }
	
}
</script>
<body>
<form action="${root}" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
			<td></td>
		</tr>
		<tr>
			<th>모집포지션</th>
			<td>
			  <c:forEach begin="0" var="i" items="${departList}" end="${departList.size()}">
					<input type="checkbox" id="${i.getDepart_code()}" name="departcodeList" value="${i.getDepart_code()}" onchange="project.departCount(this)"> ${i.getDepart_name()} 
						<div id="${i.getDepart_code()}_count" style="display:none;">
							<select id="${i.getDepart_code()}_select" name="departMap[${i.getDepart_code()}]">
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
			<td><textarea id="project_detail" name="project_detail">
					${projectDetail.getProject_detail()}
				</textarea>
			</td>
		</tr>
	</table>
</form>	
	<input type="button" onclick="project.projectList()" value="목록"/>
	<c:set var="loginId" value="${userDto.getUser_id()}"></c:set>
	<c:set var="ManagerId" value="${projectDetail.getManager_id()}"></c:set>

	<c:if test="${ManagerId == loginId}"><!-- 자신이 등록한 프로젝트만 수정/삭제 가능 -->
		<input type="submit" value="수정"/>
		<input type="submit" value="삭제" onclick="project.projectDelete(${projectDetail.getProject_id()})"/>
	</c:if>
	<input type="submit" value="신청"/>
	
</body>
</html>