<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath }"/>

<script type ="text/javascript">
function dropDown() {
	var dropdowns = document.getElementById("dropdown-menu");
	  if (dropdowns.style.display == 'block') {
		  dropdowns.style.display = 'none';
 	  }
 	  else {
 		 dropdowns.style.display = 'block';
 	  }
}
</script>
<header>    
<nav class="navbar navbar-custom" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">ToyProject</a>
  </div>

  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
      <li><a href="#">프로젝트</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a href="#" onclick = "dropDown()" class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenuLink" aria-haspopup="true" aria-expanded="false"><sec:authentication property='principal.username'/>님<b class="caret"></b></a>
        <ul class="dropdown-menu"  class="nav dropdown-toggle" id = "dropdown-menu">
        
      		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="dropdown-item"><a href="${root}/admin">관리자 페이지</a></li>
			</sec:authorize>
          <li class="dropdown-item"><a href="${root}/user/<sec:authentication property='principal.username'/>">개인정보수정</a></li>
          <li class="dropdown-item" style = 'padding-left:20px;'>
          <form name = 'logout_form'action="${root}/logout" method="POST">
        	<a href="javascript:document.logout_form.submit()"> 로그아웃<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></a>
          </form>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
    
</header>