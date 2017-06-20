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
<input type="hidden" name="csrf-token" value="${_csrf.token}"/>
<input type="hidden" name="_csrf_header" value="${_csrf.headerName}"/>
<input type="hidden" id="user_id" value="${user.getUsername()}">
<input type = 'hidden' id = 'rootValue'  value = "${root}">
<div style="display: inline;"  class="container">
	<!-- 검색조건 테이블 -->
	<table class="table">
		<thead>
			<tr>
				<th>검색조건</th>
			</tr>
			</thead>
		<tbody>
			<tr>
				<td>모집상태별로 검색(모집중,모집완료)</td>
			</tr>
			<tr>
				<td>기간별로 검색</td>
			</tr>
			<tr>
				<td>직무별로 검색</td>
			</tr>
				<!-- 관리자 권한 이 있는 사람만 자기가 등록한 프로젝트 필터 가능 -->
			<c:forEach var="i" items="${user.getAuthorities()}" begin="0" end="${user.getAuthorities().size()}">
				<c:if test="${i == '02'}">
						<tr> 
							<td>
								<input type="checkbox" id="myCheck" name="myCheck" onchange="project.myPjoectList(this)"/>내가 등록한 프로젝트 보기
							</td>
						</tr>	
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<!-- // 검색조건 테이블 -->
</div>

<!-- 프로젝트 리스트 불러오기 -->
<div style="display: inline-table;">
	<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
		<ul style="border: 1px solid">
						<dt>프로젝트명 : ${i.getProject_name()} </dt>
						<li style="list-style:square;">기간</li>
						<li style="list-style:square;">모집직무 :
							<c:forEach var="departMap" items="${i.getDepartMap()}">
								${departMap.key} - ${departMap.value} 명
							</c:forEach>
						</li>
						<li style="list-style:square;">담당자 : ${i.getManager_name()}</li>
						<li style="list-style:square;">모집상태 : ${i.getState_name()}</li>
						<li style="list-style:square;"><input type="button" value="상세보기" onclick="javascript:project.projectDetail(${i.getProject_id()})"/></li>
						<li style="list-style:square;">
							<c:if test="${i.getApplyyn().equals('Y') }"><!-- 이미내가 신청한 프로젝트 이면 다시 신청 못함 -->
								<input type="button" value="신청하기" onclick="javascript:project.applyOpen(${i.getProject_id()})"/>
							</c:if>
							<c:if test="${i.getApplyyn().equals('N') }">
								신청완료
							</c:if>
						</li>
		</ul>
	</c:forEach>
</div>
<!-- //프로젝트 리스트 불러오기-->

<div style="display: inline;">
	<c:forEach var="i" items="${user.getAuthorities()}" begin="0" end="${user.getAuthorities().size()}">
		<c:if test="${i == '02'}">
				<br/><a href="${root}/project/new">프로젝트 생성하기</a><br/>		
		</c:if>
	</c:forEach>
</div>

<!-- 프로젝트 신청하기 팝업 -->
<div id= "registDiv" style="display: none;">
<jsp:include page="projectApply.jsp" flush="false"></jsp:include>
</div>
<!-- //프로젝트 신청하기 팝업 -->