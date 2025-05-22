<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/templates/meta.jsp" />
<link rel="stylesheet"
	href="<c:url value="/resources/static/login-style.css"/>">
<title>Login</title>
</head>
<body>
	<div class="container">
		<h1 class="form-heading" align="center">Simple Talk Room</h1>
		<div class="main-div">
			<form id="login-form" class="form-signin"
				action="<c:url value="/register"/>" method="post">
				<h3 class="form-signin-heading">給個聊天名稱吧</h3>
				<p class="form-signin-heading">一個簡單的聊天平台</p>
				<div class="form-group">
					<input class="form-control" name="username" placeholder="輸入聊天名稱">
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">開始聊天</button>
			</form>
		</div>
	</div>
</body>
</html>