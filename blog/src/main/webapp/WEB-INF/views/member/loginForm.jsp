<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<body class="">
	<div class="d-flex" id="wrapper">
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>
			<div class="container">
				<br /> <br />
				<form action="/auth/loginProc" method="post">
					<div class="form-group">
						<label for="id">ID</label> <input type="text" name="username" class="form-control" placeholder="Enter ID" id="username">
					</div>
					<div class="form-group">
						<label for="pwd">Password</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
					</div>
					<button id="btn-login" class="btn btn-primary">로그인</button>
					<a href="/auth/kakao/login"><img height="38px" src="/image/kakao_login_button.png" /></a>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../layout/footer.jsp"%>
	<script src="/js/member.js"></script>
</body>
</html>




