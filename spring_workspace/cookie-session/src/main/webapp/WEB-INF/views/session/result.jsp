<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result</title>
</head>
<body>
	<h3>세션 확인 페이지</h3>
	아이디 : ${sessionScope.id }<br>
	비밀번호 : ${sessionScope.pw }<br>
	<a href="delete">세션 삭제하기</a>
</body>
</html>