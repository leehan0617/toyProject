<!-- 전체 프로젝트 리스트 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="/js/project/project.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
console.log("안녕")
</script>
<input type="hidden" id="user_id" value="${userDto.getUser_id()}">

<div style="display: inline;">
	<!-- 검색조건 테이블 -->
	<table>
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
			<c:forEach var="i" items="${userDto.getAuthorities()}" begin="0" end="${userDto.getAuthorities().size()}">
				<c:if test="${i.getAuth_code().equals('02') }">
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
						<li style="list-style:square;">총인원 : ${i.getUsercount()}</li>
						<li style="list-style:square;">모집직무 :
							<c:forEach var="depart_name" items="${i.getDepart_code()}" begin="0" end = "${i.getDepart_code().size()}">
								${depart_name}
							</c:forEach>
						</li>
						<li style="list-style:square;">담당자 : ${i.getManager_name()}</li>
						<li style="list-style:square;">모집상태 : ${i.getState_name()}</li>
						<li style="list-style:square;"><input type="button" value="상세보기"/></li>
						<li style="list-style:square;"><input type="button" value="신청하기"/></li>
		</ul>
	</c:forEach>
</div>
<!-- //프로젝트 리스트 불러오기 -->

<div style="display: inline;">
	<c:forEach var="i" items="${userDto.getAuthorities()}" begin="0" end="${userDto.getAuthorities().size()}">
		<c:if test="${i.getAuth_code().equals('02') }">
				<br/><a href="${root}/project/new">프로젝트 생성하기</a><br/>		
		</c:if>
	</c:forEach>
</div>
</body>
</html>