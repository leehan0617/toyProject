<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="#{pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="js/bower_components/sockjs/sockjs.min.js"></script>
	<script type="text/javascript" src="js/stomp.js"></script>
	<script>
		var stompClient = null;
		function setConnected(connected) {
			document.getElementById('connect').disabled = connected;
			document.getElementById('disconnect').disabled = !connected;
			document.getElementById('calculationDiv').style.visibility = connected ? 'visible' : 'hidden';
			document.getElementById('calResponse').innerHTML = '';
		}
		
		function connect() {
			var socket = new SockJS('${root}/add');
			stompClient = Stomp.over(socket);
			stompClient.connect({} , function(frame) {
				setConnected(true);
				console.log('Connected' + frame);
				stompClient.subscribe('/topic/showResult' , function(calResult) {
					console.log(calResult);
					console.log(calResult.body);
					showResult(calResult.body);
					//showResult(JSON.parse(calResult.body).result);
				});
			});
		}
		
		function disconnect() {
			stompClient.disconnect();
			setConnected(false);
			console.log('Disconnected');
		}
		
		function sendNum() {
			var num1 = document.getElementById('num1').value;
			stompClient.send('/calcApp/add' , {} , JSON.stringify({'num1' : num1}));
		}
		
		function showResult(message) {
			var response = document.getElementById('calResponse');
			var p = document.createElement('p');
			p.style.wordWrap = 'break-word';
			p.appendChild(document.createTextNode(message));
			response.appendChild(p);
		}
	</script>
</head>
<body>
	<h1>stomp test</h1>
		
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="calculationDiv">
			<label>value1:</label><input type="text" id="num1" /><br/>
	        <button id="sendNum" onclick="sendNum();">Send to Add</button>
	        <p id="calResponse"></p>
		</div>
	</div>
</body>
</html>