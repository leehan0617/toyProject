<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="ctg" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="/js/project/project.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>
<form action="${root}" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<table>
		<tr>
			<th>������Ʈ ���� �̸�</th>
			<td><input name = "project_name" id ="project_name" type="text" value="${projectDetail.getProject_name()}"/></td>
		</tr>
		<tr>
			<th>�����</th>
			<td>${projectDetail.getManager_name()}</td>
		</tr>
		<tr>
			<th>�Ⱓ</th>
			<td></td>
		</tr>
		<tr>
			<th>����������${departList.size()}</th>
			<td>
			
			  <c:forEach begin="0" var="i" items="${departList}" end="${departList.size()}">
			  	<c:forEach begin="0" var="j" items="${projectDep}" end="${projectDep.size()}">
					<input type="checkbox" id="depart_code" name="depart_code" value="${i.getDepart_code()}" onchange="project.departCount(this)"> ${i.getDepart_name()}
						<div id="${i.getDepart_code()}_count" style="display: none;">
							<select id="departMap[${i.getDepart_code()}]" name="departMap[${i.getDepart_code()}]">
								<c:forEach begin="1" end="10" var="i"> ������ �ο��� ���ϱ� 
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
						</div>
				</c:forEach>		
			   </c:forEach>
			</td>
		</tr>
		<tr>
			<th>������Ʈ�󼼼���</th>
			<td><textarea id="project_detail" name="project_detail">
					${projectDetail.getProject_detail()}
				</textarea>
			</td>
		</tr>
	</table>
	<input type="button" onclick="project.projectList()" value="���"/>
	<input type="submit" value="����"/>
	<input type="submit" value="����"/>
	<input type="submit" value="��û"/>
</form>
</body>
</html>