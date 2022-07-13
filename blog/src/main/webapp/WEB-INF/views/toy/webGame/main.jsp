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
				<div class="ml-5 mr-5 mb-5">
					<h2>Canvas 웹게임</h2>
					HTML Canvas를 이용하여 간단한 게임을 구현하였습니다. 방향키, 마우스를 이용하여 화살표를 피하세요<br /> 
					리소스 출처 - 『유니티5 교과서』
					<br/>개발 일지 - <a href="https://gomawoomi.tistory.com/55">https://gomawoomi.tistory.com/54</a>
				</div>
				<canvas id="myCanvas" class="webGame" width="600" height="450"></canvas>
				<div class="align-items-center">
					<p class="d-flex justify-content-center"></p>
				</div>
			</div>
			<%@include file="../../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/webGame.js"></script>
	</div>

</body>
</html>