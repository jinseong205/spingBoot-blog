<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../layout/boardSideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>

			<div id="content" class="container-fluid ">
				<br/>
				<div class="clearfix container-fluid">
					<div class="float-left">
						<button class="btn btn-primary" id="sidebarToggle"><></button>
					</div>
				</div>
				<br/>
				
				<div class="mr-5 ml-5 mb-5">

					<div>
						글번호: <span id="id">${board.id}</span> 작성자: <span><i>${board.member.username} </i></span>
					</div>
					<br />
					<div>
						<h3>${board.title}</h3>
					</div>
					<hr />
					<br />
					<div>
						<div>${board.content}</div>
					</div>
					<br />
					<hr />
					<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
					<c:if test="${board.member.id == principal.member.id}">
						<a href="/board/updateForm/${board.id}" class="btn btn-secondary">수정</a>
					</c:if>
					<c:if test="${board.member.id == principal.member.id || principal.member.role eq 'ADMIN'}">
						<button id="btn-delete" class="btn btn-secondary">삭제</button>
					</c:if>

					<hr />

					<c:choose>
						<c:when test="${not empty principal}">
							<div class="card">
								<form>
									<input type="hidden" id="memberId" value="${principal.member.id}" /> <input type="hidden" id="boardId" value="${board.id}" />
									<div class="card-body">
										<textarea id="reply-content" class="form-control" rows="1"></textarea>
									</div>

									<div class="card-footer">
										<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
									</div>
								</form>
							</div>
						</c:when>
					</c:choose>

					<br />
					<div class="card">

						<div class="card-header">댓글리스트</div>
						<ul id="reply--box" class="list-group">
							<c:forEach var="reply" items="${board.replys}">
								<li id="reply--${reply.id}" class="list-group-item d-flex justify-content-between">
									<div>${reply.content}</div>
									<div class="d-flex">
										<div class="font-italic">작성자 : ${reply.member.username} &nbsp;</div>
										<c:if test="${reply.member.id eq principal.member.id|| principal.member.role eq 'ADMIN'}">
											<button onClick="index.replyDelete(${board.id},${reply.id})" class="badge">삭제</button>
										</c:if>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<%@include file="../layout/footer.jsp"%>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="/js/board.js"></script>
</body>
</html>