<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie</title>
<c:if test="${empty clientSendCookie }">
	<script>
			window.open("popup", "", "width=500,height=300,top=300,left=500");
	</script>
</c:if>
</head>
<body onload="popup()">
	<h3>쿠기 페이지 입니다.</h3>
</body>
</html>