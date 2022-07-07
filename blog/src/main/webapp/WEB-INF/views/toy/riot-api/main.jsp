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
					<h2>Riot-API (개발중)</h2>
					라이엇 API 를 이용하여 리그오브레전드 전적정보를 검색합니다.<br/>
					API Key가 만료 될 시 더미데이터를 이용합니다.
				</div>
				<div class="ml-5 mr-5 mb-5">
					<div>
						<div class="card-body row no-gutters align-items-center">
							<div class="col">
								<input id="username" class="form-control form-control-lg form-control-borderless" type="search" placeholder="소환사명을 입력하세요">
							</div>
							<div class="col-auto">
								<button id="btn-search" class="btn btn-lg btn-primary">&nbsp검색&nbsp</button>
							</div>
						</div>

					</div>
				</div>
			</div>
			<%@include file="../../layout/footer.jsp"%>
		</div>
		<script src="/js/toy/riot-api.js"></script>
	</div>

</body>
</html>