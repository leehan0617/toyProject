<%-- project 생성 페이지 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.js" async defer></script>
<script type="text/javascript" src="${root}/js/project/project.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />
<style>
/* 부트스트랩 깨짐 설정 */
.checkbox-inline, .radio-inline{
	    padding-left: 20px;
}
</style>

<div class="container">
	<form action="${root}/project" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type = 'hidden' id = 'rootValue'  value = "${root}">
		<table class="table table-hover" style="background-color: #FFFFFF">
			<tr>
				<th scope="row" class="bg-gray">프로젝트 생성 이름</th>
				<td colspan="3"><input  class="form-control" name = "project_name" id ="project_name" type="text"/></td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">담당자</th>
				<td colspan="3"><sec:authentication property='principal.username'/></td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">모집기간</th>
				<td><input class="form-control" type="date" id="recurit_start_date" name = "recruit_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" id="recruit_end_date" name = "recruit_end_date" data-date-format="YYYY-MM-DD"/></td>
				<th scope="row" class="bg-gray">모집상태</th>
				<td colspan="3">
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
				<td><input class="form-control" type="date" id="project_start_date" name = "project_start_date" data-date-format="YYYY-MM-DD"/> ~ <input class="form-control" type="date" id="project_end_date" name = "project_end_date" data-date-format="YYYY-MM-DD"/></td>
				<th scope="row" class="bg-gray">프로젝트상태</th>
				<td colspan="3">
					<c:forEach begin="0" var="i" items="${projectStateList}" end="${projectStateList.size()}">
						<div class="abc-radio abc-radio-danger radio-inline">	
						    <c:set var="ck" value=""></c:set>	
						    <c:if test="${projectDetail.getRecruit_state_code() == i.getState_code()}">
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
							<input type="checkbox" id="${i.getDepart_code()}" name="departCodeList" value="${i.getDepart_code()}" onchange="project.departCount(this)"/>
							<label for="${i.getDepart_code()}">
								${i.getDepart_name()}
							</label>
						</div>
						<div id="${i.getDepart_code()}_count" style="display: none;margin-right:20px;">
							<select class="form-control" id="${i.getDepart_code()}_select">
								<c:forEach begin="1" end="10" var="i"> <!-- 직무별 인원수 정하기  -->
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
						</div>
				   </c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row" class="bg-gray">프로젝트상세설명</th>
				<td colspan="3"><textarea class="form-control" id="project_detail" name="project_detail"></textarea></td>
			</tr>
		</table>
		<div class="divfloat">
			<input class = "btn btn-default btn-sm" type="button" onclick="project.projectList()" value="목록"/>
			<input style="border: 2px solid #E95420; color:#E95420;" class = "btn btn-default btn-sm" type="submit" value="생성"/>
		</div>
	</form>
</div>
