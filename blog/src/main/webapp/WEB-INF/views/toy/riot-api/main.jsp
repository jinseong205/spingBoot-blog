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
				<div class="ml-5 mr-5 mb-5">
					<div class="align-items-center">
						<div class="form-group">
							<div class="form-group">
								<label for="username"></label> <input type="text" class="form-control" placeholder="소환사명을 입력하세요" id="username">
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/webGame.js"></script>
	</div>

</body>
</html>