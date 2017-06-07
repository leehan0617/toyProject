<%-- project 생성 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/project/project.js" charset="UTF-8"></script>
<title>toyProject</title>
</head>
<body>
<form action="${root}/project" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<table>
		<tr>
			<th>프로젝트 생성 이름</th>
			<td><input name = "project_name" id ="project_name" type="text"/></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${userDto.getUser_name()}</td>
		</tr>
		<tr>
			<th>기간</th>
			<td></td>
		</tr>
		<tr>
			<th>모집포지션</th>
			<td>
			   <c:forEach begin="0" var="i" items="${departList}" end="${departList.size()}">
					<input type="checkbox" id="departCodeList" name="departCodeList" value="${i.getDepart_code()}" onchange="project.departCount(this)"/> ${i.getDepart_name()}
					<div id="${i.getDepart_code()}_count" style="display: none;">
						<select id="departMap[${i.getDepart_code()}]" name="departMap[${i.getDepart_code()}]">
							<c:forEach begin="1" end="10" var="i"> <!-- 직무별 인원수 정하기  -->
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
			</textarea></td>
		</tr>
	</table>
	<input type="button" onclick="project.projectList()" value="목록"/>
	<input type="submit" value="생성"/>
</form>
</body>
</html>