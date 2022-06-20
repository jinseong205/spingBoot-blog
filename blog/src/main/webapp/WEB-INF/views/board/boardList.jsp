<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="wrapper">
	<%@include file="../layout/boardSideBar.jsp"%>

	<div id="content" class="container-fluid ">
		<br />
		<div class="d-flex flex-column">
			<c:choose>
				<c:when test="${not empty principal}">
					<div class="align-self-end">
						<a href="/board/saveForm" class="btn btn-secondary align-self-end">글 작성</a>
					</div>
				</c:when>

			</c:choose>
		</div>

		<c:forEach var="board" items='${boards.content}'>
			<div class="card m-2">
				<div class="card-body">
					<a href="/board/boardDetail/${board.id}">
					<div class="d-flex container-fluid">
						<h4 class="d-flex card-title align-self-start">${board.title}</h4>
						&nbsp&nbsp<p>- ${board.member.username}</p>
						&nbsp&nbsp<p class="d-flex card-content  align-self-end"> ${board.createDate} </p>
					</div>
					</a>	
					
				</div>
			</div>
		</c:forEach>

		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${boards.last}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<%@include file="../layout/footer.jsp"%>
	</div>

</div>




