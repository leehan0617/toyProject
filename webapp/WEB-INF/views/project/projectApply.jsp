<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${root}/css/project/projectPopup.css" />
<link rel="stylesheet" type="text/css" href="${root}/css/project/project.css" />

<div class="dim-layer">
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
        	<button type="button" class="btn btn-default" onclick="project.applyClose()" style = "float:right; border:0px;">
			    <span class="glyphicon glyphicon-remove"></span>
		  	</button>
            <div class="pop-conts">
            	<div class="page-header" >
					<h2>신청하기</h2>      
				</div>
				<div class="form-group">
					<form method="POST" id = "memberApply">
					<input type='hidden' id = 'project_id' name = 'project_id' value = "">
						<h3><label id="project_name" style="color:#FF8000;"></label></h3>
						<p>
							<label for="comment"> 상세직무 설명 및 본인소개 </label>
							<textarea class="form-control" id= "depart_detail" name= "depart_detail"></textarea>
						</p>
					</form>	
					<br>
					<input type="button" value="신청" style="border: 2px solid #d9534f; color:#d9534f; float:right;" class = "btn btn-default btn-sm" onclick="project.projectApply();"/>
            		<br>
            	</div>
            </div>
        </div>
    </div>
</div>