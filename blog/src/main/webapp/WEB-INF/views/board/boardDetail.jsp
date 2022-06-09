<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

	<div>
		글번호: <span id="id">${board.id}</span>
		작성자: <span><i>${board.member.username} </i></span>
	</div>
	<br/>	
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr/>
	<div>
		<div>${board.content}</div>
	</div>
	<hr/>
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<c:if test="${board.member.id == principal.member.id}">
	<a href ="/board/updateForm/${board.id}" class="btn btn-secondary">수정</a>
	<button id="btn-delete" class="btn btn-secondary">삭제</button>
	</c:if>
</div>


<%@include file="../layout/footer.jsp"%>
<script src="/js/board.js"></script>


