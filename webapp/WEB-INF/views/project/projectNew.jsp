<%-- project 생성 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="UTF-8"></script>
<form action="${root}/project" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type = 'hidden' id = 'rootValue'  value = "${root}">
	<table>
		<tr>
			<th>프로젝트 생성 이름</th>
			<td><input name = "project_name" id ="project_name" type="text"/></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td><sec:authentication property='principal.username'/></td>
		</tr>
		<tr>
			<th>모집기간</th>
			<td><input type="date" id="recurit_start_date" name = "recruit_start_date" data-date-format="YYYY-MM-DD"/> ~ <input type="date" id="recruit_end_date" name = "recruit_end_date" data-date-format="YYYY-MM-DD"/></td>
		</tr>
		<tr>
			<th>프로젝트기간</th>
			<td><input type="date" id="project_start_date" name = "project_start_date" data-date-format="YYYY-MM-DD"/> ~ <input type="date" id="project_end_date" name = "project_end_date" data-date-format="YYYY-MM-DD"/></td>
		</tr>
		<tr>
			<th>모집포지션</th>
			<td>
			   <c:forEach begin="0" var="i" items="${departList}" end="${departList.size()}">
					<input type="checkbox" id="departCodeList" name="departCodeList" value="${i.getDepart_code()}" onchange="project.departCount(this)"/> ${i.getDepart_name()}
					<div id="${i.getDepart_code()}_count" style="display: none;">
						<select id="${i.getDepart_code()}_select">
							<c:forEach begin="1" end="10" var="i"> <!-- 직무별 인원수 정하기  -->
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>
					</div>
			   </c:forEach>
			</td>
		</tr>
		<tr>
			<th>프로젝트상세설명</th>
			<td><textarea id="project_detail" name="project_detail"></textarea></td>
		</tr>
	</table>
	<input type="button" onclick="project.projectList()" value="목록"/>
	<input type="submit" value="생성"/>
</form>
