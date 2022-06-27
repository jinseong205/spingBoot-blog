<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- nav bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
	<a class="navbar-brand" href="/">Jinseong</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse navbar-ex1-collapse" id="collapsibleNavbar">
		<div class="d-flex container-fluid">
			<c:choose>
				<c:when test="${empty principal}">
					<div class="align-self-start">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="/board/boardList">자료정리</a></li>
							<li class="nav-item"><a class="nav-link" href="/toy">토이 프로젝트</a></li>
						</ul>
					</div>
					<div class="align-self-end">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
							<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div class="align-self-start">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="/board/boardList">자료정리</a></li>
							<li class="nav-item"><a class="nav-link" href="/toy">토이 프로젝트</a></li>
						</ul>
					</div>
					<div class="align-self-end">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="/member/updateForm">내 정보</a></li>
							<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>