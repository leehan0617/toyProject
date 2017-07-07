<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${root}/css/project/projectPopup.css" />

<div class="dim-layer">
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
        	<button type="button" class="btn btn-default" onclick="project.infoDetailClose()" style = "float:right; border:0px;">
			    <span class="glyphicon glyphicon-remove"></span>
		  	</button>
            <div class="pop-conts">
            	<div class="page-header" >
					<h2>정보보기</h2>      
				</div>
				<table class="table table-hover">
					<tr>
						<th scope="row" class="bg-gray">이름</th>
						<td id="name">
							
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">부서</th>
						<td id="depart">
							
						</td>
					</tr>
					<tr>
						<th scope="row" class="bg-gray">자기소개</th>
						<td  id="depart_detail">
							
						</td>
					</tr>
				</table>
            </div>
        </div>
    </div>
</div>