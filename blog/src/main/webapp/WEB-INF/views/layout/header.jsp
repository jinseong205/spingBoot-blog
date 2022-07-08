<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Jinseong</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- jQuery & bootstrap -->
<script src="/js/jquery-3.5.1.js"></script>
<script src="/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
<!-- ck Editor -->
<script src="/js/ckeditor/ckeditor.js"></script>
<!--  custom -->
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/webGame.css">
<script src="/js/main.js"></script>
</head>