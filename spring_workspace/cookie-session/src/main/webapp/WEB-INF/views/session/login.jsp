<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h3>로그인 페이지</h3>
	<form action="login" method="post">
		<input type="text" name="id" placeholder="아이디" /><br>
		<input type="password" name="pw" placeholder="비밀번호" /><br>
		<input type="submit" value="로그인" >
		<input type="button" value="취소" onclick="location.href='index'" />
	</form>
</body>
</html>