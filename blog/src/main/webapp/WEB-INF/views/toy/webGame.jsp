<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../layout/toySideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>

			<div id="content" class="container-fluid ">
				<br /> 
				<div class="clearfix container-fluid">
					<div class="float-left">
						<button class="btn btn-primary" id="sidebarToggle"><></button>
					</div>
				</div>
				<br/>
				<canvas id="myCanvas" class="webGame" width="600" height="450"></canvas>
				<br>
				<div class="align-items-center">
					<p class="d-flex justify-content-center">Resource - 『유니티5 교과서』</p>
				</div>
			</div>
			<%@include file="../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/webGame.js"></script>
	</div>

</body>
</html>