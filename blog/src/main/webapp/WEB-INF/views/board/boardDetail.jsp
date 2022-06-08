<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">


	<div>
		<h3>${board.title}</h3>
	</div>
	<hr/>
	<div>
		<div>${board.content}</div>
	</div>
	<hr/>
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<button id="btn-update" class="btn btn-secondary">수정</button>
	<button id="btn-delete" class="btn btn-secondary">삭제</button>
</div>


<%@include file="../layout/footer.jsp"%>
<script src="/js/board.js"></script>


