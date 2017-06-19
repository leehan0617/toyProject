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
				<table>
					<tr>
						<th colspan="2">정보</th>
					</tr>
					<tr>
						<td>
							이름
						</td>
						<td id="name">
							
						</td>
					</tr>
					<tr>
						<td>
							부서
						</td>
						<td id="depart">
							
						</td>
					</tr>
					<tr>
						<td>
							자기소개
						</td>
						<td  id="depart_detail">
							
						</td>
					</tr>
				</table>
                <input type="button"  onclick="project.infoDetailClose()" value="Close" /><!-- 팝업 닫기 -->
            </div>
        </div>
    </div>
</div>