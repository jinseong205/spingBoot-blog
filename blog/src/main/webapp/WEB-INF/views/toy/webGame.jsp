<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="wrapper">
	<%@include file="../layout/toySideBar.jsp"%>

	<div id="content" class="container-fluid ">
		<br/>	
		<br/>	
		<canvas id="myCanvas" class = "webGame" width="800" height="600"></canvas>
		<%@include file="../layout/footer.jsp"%>
	</div>

</div>

<script src="/js/toy/webGame.js"></script> 



