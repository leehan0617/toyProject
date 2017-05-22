<!-- ��ü ������Ʈ ����Ʈ -->
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
console.log("�ȳ�")
</script>
<input type="hidden" id="user_id" value="${userDto.getUser_id()}">

<div style="display: inline;">
	<!-- �˻����� ���̺� -->
	<table>
		<thead>
			<tr>
				<th>�˻�����</th>
			</tr>
			</thead>
		<tbody>
			<tr>
				<td>�������º��� �˻�(������,�����Ϸ�)</td>
			</tr>
			<tr>
				<td>�Ⱓ���� �˻�</td>
			</tr>
			<tr>
				<td>�������� �˻�</td>
			</tr>
				<!-- ������ ���� �� �ִ� ����� �ڱⰡ ����� ������Ʈ ���� ���� -->
			<c:forEach var="i" items="${userDto.getAuthorities()}" begin="0" end="${userDto.getAuthorities().size()}">
				<c:if test="${i.getAuth_code().equals('02') }">
						<tr> 
							<td>
								<input type="checkbox" id="myCheck" name="myCheck" onchange="project.myPjoectList(this)"/>���� ����� ������Ʈ ����
							</td>
						</tr>	
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<!-- // �˻����� ���̺� -->
</div>

<!-- ������Ʈ ����Ʈ �ҷ����� -->
<div style="display: inline-table;">
	<c:forEach var="i" items="${projectList}" begin="0" end="${projectList.size()}">
		<ul style="border: 1px solid">
						<dt>������Ʈ�� : ${i.getProject_name()} </dt>
						<li style="list-style:square;">�Ⱓ</li>
						<li style="list-style:square;">���ο� : ${i.getUsercount()}</li>
						<li style="list-style:square;">�������� :
							<c:forEach var="depart_name" items="${i.getDepart_code()}" begin="0" end = "${i.getDepart_code().size()}">
								${depart_name}
							</c:forEach>
						</li>
						<li style="list-style:square;">����� : ${i.getManager_name()}</li>
						<li style="list-style:square;">�������� : ${i.getState_name()}</li>
						<li style="list-style:square;"><input type="button" value="�󼼺���"/></li>
						<li style="list-style:square;"><input type="button" value="��û�ϱ�"/></li>
		</ul>
	</c:forEach>
</div>
<!-- //������Ʈ ����Ʈ �ҷ����� -->

<div style="display: inline;">
	<c:forEach var="i" items="${userDto.getAuthorities()}" begin="0" end="${userDto.getAuthorities().size()}">
		<c:if test="${i.getAuth_code().equals('02') }">
				<br/><a href="${root}/project/new">������Ʈ �����ϱ�</a><br/>		
		</c:if>
	</c:forEach>
</div>
</body>
</html>