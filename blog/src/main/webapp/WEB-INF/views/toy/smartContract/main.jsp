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
					<h2>SmartContract (미개발)</h2>
					이더리움 스마트 컨트랙트를 이용하여 데이터를 저장합니다.<br /> 해당 데이터는 이더리움 테스트 네트워크에 저장됩니다.
				</div>
				<div class="ml-5 mr-5 mb-5"></div>
			</div>
			<%@include file="../../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/riot-api.js"></script>
	</div>

</body>
</html>