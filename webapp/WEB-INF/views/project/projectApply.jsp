<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<c:set var="root" value="#{pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${root}/css/project/projectPopup.css" />

<div class="dim-layer">
<!--     <div class="dimBg"></div> -->
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<form method="POST" id = "memberApply">
				<input type='hidden' id = 'project_id' name = 'project_id' value = "">
					<table>
						<tr>
							<th>상세직무 설명 및 본인소개</th>
						</tr>
						<tr>
							<td>
								<textarea style="width: 100%" id= "depart_detail" name= "depart_detail"></textarea>
							</td>
						</tr>
					</table>
				</form>	
				<input type="button" value="신청" onclick="project.projectApply();"/>
                <input type="button"  onclick="project.applyClose()" value="Close" /><!-- 팝업 닫기 -->
            </div>
        </div>
    </div>
</div>


	
