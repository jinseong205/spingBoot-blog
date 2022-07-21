<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>


<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../../layout/toySideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../../layout/nav.jsp"%>

			<div id="content" class="container-fluid ">
				<br />
				<div class="clearfix container-fluid">
					<div class="float-left">
						<button class="btn btn-primary" id="sidebarToggle"><></button>

					</div>
				</div>
				<br />
				<div class="ml-5 mr-5 mb-3">
					<h2>WebSocket 채팅</h2>
					웹소켓을 이용하여 간단한 채팅방을 구현하였습니다. <br />메세지 내용은 별도로 저장되지 않습니다. <br />개발 일지 - <a href="https://gomawoomi.tistory.com/56">https://gomawoomi.tistory.com/56</a>
				</div>
				<div class="ml-5 mr-5 mb-5">
					<div id="chattingSpace" class="chattingSpace p-3">
						${chatName}님 환영합니다.
						<hr />
					</div>

					<input id="chatName" name="chatName" class="form-control form-control-lg form-control-borderless" type="hidden" value="${chatName}">

					<div id="chatMsgDiv" class="card-body row no-gutters align-items-center">
						<div class="col">
							<input id="chatMsg" name="chatMsg" class="form-control form-control-lg form-control-borderless" type="search" placeholder="대화를 입력하세요." />
						</div>
						<div class="col-auto">
							<button id="sendChatBtn" class="btn btn-lg btn-primary" onclick="sendChat()">&nbsp전송&nbsp</button>
						</div>
					</div>

				</div>
			</div>
			<%@include file="../../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/webSocketChatting.js"></script>
	</div>

</body>
</html>