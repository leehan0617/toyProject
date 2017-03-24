<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	let wsUri = "ws://localhost:8080/websocket/chat"
	let webSocket;
	let output;
	
	function init() {
		webSocket = new WebSocket(wsUri);
		output = document.getElementById('output');
	}
	
	function send_message() {
		onOpen();
		webSocket.onopen = function(evt) {
			onOpen(evt);
		}
		webSocket.onmessage = function (evt) {
			onMessage(evt);
		}
		webSocket.onerror = function (evt) {
			onError(evt);
		}
		
		return false;
	}
	
	function onOpen() {
		console.log('check');
		writeToScreen("Connected to EndPoint");
		doSend(textId.value);
	}
	function onMessage(evt) {
        writeToScreen("Message Received: " + evt.data);
    }
    function onError(evt) {
        writeToScreen('ERROR: ' + evt.data);
    }
    function doSend(message) {
        writeToScreen("Message Sent: " + message);
        webSocket.send(message);
    }
    function writeToScreen(message) {
        var pre = document.createElement("p");
        pre.style.wordWrap = "break-word";
        pre.innerHTML = message;
        output.appendChild(pre);
    }
    window.addEventListener("load", init, false);
</script>
</head>
<body>
	채팅방 테스트 입니다.
	<div style="text-align: center;">
            <form onsubmit="return send_message()" action="#">
            	<input type="submit" value="전송"/>
                <input id="textId" name="message" value="Hello WebSocket!" type="text"><br>
            </form>
        </div>
        <div id="output"></div>
</body>
</html>