<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="layout/header.jsp"%>

<body class="">
	<div class="d-flex" id="wrapper">
		<div id="page-content-wrapper">
			<%@include file="layout/nav.jsp"%>
			<div class="container">
				<br />
				<div class="card m-2">
					<div class="card-body">
						<div class="d-flex container-fluid justify-content-center">
							<img height="250px" class="m-2 rounded-circle align-self-center" src="/image/jinseong.jpg">
						</div>
						<div class="d-flex container-fluid justify-content-center mt-2">
							<h3>Jinseong</h3>
						</div>
						<div class="d-flex container-fluid justify-content-center">
							<div class="float-left mt-3">
								<h5>
									<a class="text-dark" href="board/boardList?category=1">🧑🏻 프로필</a>
								</h5>
								<h5>
									<a class="text-dark" href="board/boardList?category=2">📄 포트폴리오</a>
								</h5>
								<h5>
									<a class="text-dark" href="toy/">🔫 토이 프로젝트</a>
								</h5>
							</div>
							<div class="float-left mt-3 ml-3">
								<h5>
									<a class="text-dark" href="https://gomawoomi.tistory.com/">📒 개인 블로그</a>
								</h5>
								<h5>
									<a class="text-dark" href="https://github.com/jinseong205">💾 Github</a>
								</h5>
							</div>
						</div>
					</div>
					
					<div class="container justify-content-center d-flex mt-4 mb-4">
						<hr />
						<img class="img-fluid git-chart" src="https://ghchart.rshah.org/666666/jinseong205" />
					</div>
				</div>

				<%@include file="layout/footer.jsp"%>
			</div>
		</div>
	</div>


</body>
</html>
