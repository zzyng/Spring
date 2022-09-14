<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<script>
	function check(){
/* 		id = document.getElementById('id');
		pw = document.getElementById('pw');
		confirmPw = document.getElementById('confirmPw');
		if(id.value == "" || pw.value == "" || document.getElementById('name').value == ""){
			alert('필수 항목입니다.');
			return;
		}
		if(pw.value != confirmPw.value){
			alert('두 비밀번호가 일치하지 않습니다.');
			return;
		} */
		document.getElementById('f').submit();
	}
</script>
</head>
<body>
<c:if test="${empty sessionScope.id }">
	<script>alert('로그인 후 사용할 수 있습니다.'); location.href='login';</script>
</c:if>
<c:if test="${not empty msg }">
	<script>alert('${msg}'); </script>
</c:if>
	<form action="update" method="post" id="f">
		<input type="text" name="id" id="id" value='${sessionScope.id }'><br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<input type="password" name="confirmPw" id="confirmPw" placeholder="비밀번호 확인"><br>
		<input type="text" name="name" id="name" value='${sessionScope.name }'><br>
		<input type="text" name="email" id="email" value='${sessionScope.email }'><br>
		<input type="button" value="회원 수정" onclick="check();">
		<input type="button" value="취소" onclick="location.href='index'">
	</form>
</body>
</html>