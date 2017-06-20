<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath }"/>
<header>    
	<div>로고</div>
	<sec:authorize access="isAnonymous()">
		<div>
			<a href="${root}/login">로그인</a>
			<a href="${root}/user/join">회원가입</a>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<div>
			<span>
				<sec:authentication property='principal.username'/>님
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${root}/admin">관리자 페이지</a>
				</sec:authorize>
				<a href="${root}/user/<sec:authentication property='principal.username'/>">개인정보수정</a>
			</span>
			<form action="${root}/logout" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="로그아웃"/>
			</form>
		</div>
	</sec:authorize>
</header>
