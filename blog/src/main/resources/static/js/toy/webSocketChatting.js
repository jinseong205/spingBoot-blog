/**
 * 
 */

let index = {
	init: function() {
		wsOpen();
	}
}

var ws;

function wsOpen() {
	ws = new WebSocket("ws://" + location.host + "/toy/chatting")
	wsEvt();
}

function wsEvt() {
	ws.onopen = function(data) {
		//socket init 초기화
	}

	ws.onmessage = function(data) {
		var msg = data.data;
		if (msg != null && msg.trim() != '') {
			$("#chattingSpace").append("<p>" + msg + "</p>");
		}
	}

	document.addEventListener("keypress", function(e) {
		if (e.keyCode == 13) {	//enter press
			sendChat();
		}
	})

}

function sendChat() {
	var chatName = $("#chatName").val();
	var msg = $("#chatMsg").val();
	ws.send("[" + chatName + "] " + msg);
	$('#chatMsg').val("");
	$("#chattingSpace").scrollTop($("#chattingSpace")[0].scrollHeight);
	
}


index.init();

