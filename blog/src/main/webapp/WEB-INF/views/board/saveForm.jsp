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
						Category: <select id="category" class="selectpicker">
							<option value=0>Free Board</option>
							<c:if test="${principal.member.role eq 'ADMIN'}">
								<option value=1>Profile</option>
								<option value=2>PortFolio</option>
							</c:if>
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Enter title" id="title">
					</div>
					<div class="form-group">
						<div name="editor" id="content"></div>
					</div>

				</form>
				<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>
			</div>

			<script>
				window.onload = function() {
					ck = CKEDITOR.replace("editor");
				};
			</script>
		</div>
	</div>
	<%@include file="../layout/footer.jsp"%>
	<script src="/js/board.js"></script>
</body>
</html>



