<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>popup</title>
<c:if test="${ cookie.quizName.value == 'quizValue'}">
	<script> window.close(); </script>
</c:if>
</head>
<body>
	<h3>팝업페이지</h3>
	<h4>광고 광고</h4>
	<br><br><br>
	<input type="checkbox" onclick="location.href='make'">30초  동안 닫기
</body>
</html>