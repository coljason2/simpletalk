<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js?v" />"></script>
<script type="text/javascript"
	src="<c:url value="/dwr/interface/JavaChat.js?v=" /><%=new Date().getTime()%>"></script>
<script type="text/javascript" src="<c:url value="/dwr/util.js" />"></script>
<script type="text/javascript">
	function sendMessage() {
		JavaChat.addMessage(dwr.util.getValue("text"));
		var textarea = document.getElementById('chatlog');
		textarea.scrollTop = textarea.scrollHeight
	}
</script>
<title>Simple Talk Room</title>
</head>
<body onload="dwr.engine.setActiveReverseAjax(true)">
	<div class="container-fluid">
		<h1>Simple Talker Room</h1>
		<div class="form-group">
			<textarea id="chatlog" class="form-control" readonly rows="18"
				style="resize: none; background-color: white"> </textarea>
		</div>
		<form>
			<div class="form-group">
				<textarea class="form-control" id="text" rows="3"
					onkeypress="dwr.util.onReturn(event, sendMessage);"
					placeholder="輸入要說的訊息吧......"></textarea>
			</div>
			<button type="button" class="btn btn-primary btn-lg btn-block"
				onclick="sendMessage()">廣播出去</button>
		</form>
	</div>

</body>
</html>