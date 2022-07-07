<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>


<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../../layout/toySideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../../layout/nav.jsp"%>

			<div id="content" class="container-fluid ">
				<br />
				<div class=" clearfix container-fluid">
					<div class="float-left">
						<button class="btn btn-primary" id="sidebarToggle"><></button>

					</div>
				</div>
				<br />
				<div class="ml-5 mr-5 mb-3">
					<h2>WebSocket 채팅 (미개발)</h2>
					웹소켓을 이용하여 간단한 채팅방을 구현하였습니다. <br /> 로그인시 id로 미로그인시 임의의 값으로 계정명으로 사용됩니다.
				</div>
				<div class="ml-5 mr-5 mb-5"></div>
			</div>
			<%@include file="../../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/riot-api.js"></script>
	</div>

</body>
</html>