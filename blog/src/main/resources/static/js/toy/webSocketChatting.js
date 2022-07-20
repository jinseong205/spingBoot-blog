/**
 * 
 */

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
			$("#chatting").append("<p>" + msg + "</p>");
		}
	}

	document.addEventListener("keypress", function(e) {
		if (e.keyCode == 13) {	//enter press
			sendChat();
		}
	})

}

function setChatName() {
	var chatName = $("#chatName").val();
	if (chatName == null || chatName.trim() == '') {
		alert("사용자 이름을 입력해주세요.");
		$("#chatName").focus();
	} else {
		wsOpen();
		$("#chatNameDiv").hide();
		$("#chatMsgDiv").show().css('position','relative');
		$("#chatMsg").css('display','block');
	}
}

function sendChat() {
	var chatName = $("#chatName").val();
	var msg = $("#chatMsg").val();
	ws.send("[" + chatName + "] " + msg);
	$('#chatMsg').val("");
}

