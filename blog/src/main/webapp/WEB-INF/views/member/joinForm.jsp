<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>
<body class="">
	<div class="d-flex" id="wrapper">
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>
			<div class="container">
				<br /> <br />
				<form>
					<div class="form-group">
						<label for="username">ID</label> <input type="text" class="form-control" placeholder="Enter ID" id="username">
					</div>
					<div class="form-group">
						<label for="pwd">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
					</div>
					<div class="form-group">
						<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
					</div>
				</form>

				<button id="btn-save" class="btn btn-primary">회원가입</button>

			</div>
		</div>
	</div>
	<%@include file="../layout/footer.jsp"%>
	<script src="/js/member.js"></script>
</body>
</html>



