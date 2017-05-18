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
			var socket = new SockJS('${root}/add'); // endpoint 설정
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
				
				stompClient.subscribe('/user/topic2/privateMsg' , function(calResult) {
					console.log(calResult);
					alert(calResult.body);
				});
				
				stompClient.subscribe('/topic3/otherMsg' , function(calResult) {
					console.log(calResult);
					showResult(calResult.body);
				});
				
				stompClient.subscribe('/user/topic2/test' , function(calResult) {
					console.log(calResult);
					showResult(calResult.body);
				});
			});
		}
		
		function disconnect() {
			stompClient.disconnect();
			setConnected(false);
			console.log('Disconnected');
		}
		
		function sendNum() {
			var num1 = document.getElementById('msg').value;
			stompClient.send('/calcApp/add' , {} , JSON.stringify({'num1' : num1}));
		}
		
		function sendOther() {
			var msg = document.getElementById('msg').value;
			stompClient.send('/calcApp/add2' , {} , JSON.stringify({'msg' : msg}));
		}
		
		function sendPrivateMsg() {
			var text = document.getElementById('privateInput').value;
			console.log(text);
			stompClient.send('/calcApp/privateMsg' , {} , JSON.stringify({'text' : text}));
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
		<input type="text" id="privateInput"/>
		<button onclick="sendPrivateMsg()">Send Private Msg</button>
	</div>		
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="calculationDiv">
			<label>메세지 : </label><input type="text" id="msg" /><br/>
	        <button onclick="sendNum();">Send to Add</button>
	        <button onclick="sendOther();">Send to Other</button>
	        <p id="calResponse"></p>
		</div>
	</div>
</body>
</html>