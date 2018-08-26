<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/layout/meta.jsp" />
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
<body onload="dwr.engine.setActiveReverseAjax(true)"
	style="background-color: #eee;">
	<div class="container-fluid">
		<h1>Simple Talker Room</h1>
		<h2 id="allUsers"></h2>
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