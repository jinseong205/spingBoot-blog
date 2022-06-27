<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../layout/boardSideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>

			<div id="content" class="container-fluid ">
				<br /> <br />

				<div class="container">
					<div class="clearfix container-fluid">
						<div class="float-left">
							<button class="btn btn-primary" id="sidebarToggle"><></button>
						</div>
					</div>
					</br>
					<div>
						글번호: <span id="id">${board.id}</span> 작성자: <span><i>${board.member.username} </i></span>
					</div>
					<br />
					<div>
						<h3>${board.title}</h3>
					</div>
					<hr />
					<div>
						<div>${board.content}</div>
					</div>
					<hr />
					<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
					<c:if test="${board.member.id == principal.member.id}">
						<a href="/board/updateForm/${board.id}" class="btn btn-secondary">수정</a>
						<button id="btn-delete" class="btn btn-secondary">삭제</button>
					</c:if>

					<hr />
					<div class="card">
						<div class="card-body">
							<textarea class="form-control" rows="1"></textarea>
						</div>

						<div class="card-footer">
							<button class="btn btn-primary">등록</button>
						</div>
					</div>
					<br />
					<div class="card">
						<div class="card-header">댓글리스트</div>
					</div>
					<%@include file="../layout/footer.jsp"%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>