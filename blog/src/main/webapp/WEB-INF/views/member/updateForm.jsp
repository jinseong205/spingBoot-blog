<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<body class="">
	<div class="d-flex" id="wrapper">
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>
			<div class="container">
				<br /> <br />
				<form>
					<input type="hidden" id="id" value="${principal.member.id}" />
					<div class="form-group">
						<label for="username">ID</label> <input type="text" value="${principal.member.username }" class="form-control" placeholder="Enter ID" id="username" readonly="readonly">
					</div>

					<c:if test="${empty principal.member.oauth}">
						<div class="form-group">
							<label for="pwd">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
						</div>
					</c:if>

					<div class="form-group">
						<label for="email">Email</label> <input type="email" value="${principal.member.email }" class="form-control" placeholder="Enter email" id="email">
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../layout/footer.jsp"%>
	<script src="/js/member.js"></script>
</body>
</html>




