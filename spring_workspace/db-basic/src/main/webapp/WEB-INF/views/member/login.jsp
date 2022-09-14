<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script>
	function check() {
		/* 		id = document.getElementById('id');
		 pw = document.getElementById('pw');
		
		 if(id.value == "" || pw.value == ""){
		 alert('필수 항목입니다.');
		 return;
		 } */
		document.getElementById('f').submit();
	}
</script>
</head>
<body>
	<c:if test="${not empty msg }">
		<script>
			alert("${msg}");
		</script>
	</c:if>


<c:choose>
	<c:when test="${empty sessionScope.id }">
		<form action="login" method="post" id="f">
			<input type="text" name="id" placeholder="아이디" /> <br>
			<input type="password" name="pw" placeholder="비밀번호" /> <br>
			<input type="button" value="로그인" onclick="check();">
			<input type="button" value="취소" onclick="javascript:location.href='index'">
		</form>
<%-- 		
이미지 경로
https://developers.kakao.com/tool/demo/login/login?method=dynamic
REST API : 	0070664f6956225934aebb37c784f4ed
REDIRECT URI : http://localhost:8085/basic/kakaoLogin
인가 코드 요청 URI :
oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code HTTP/1.1

 --%>
		
		<a href="https://kauth.kakao.com/oauth/authorize?
		client_id=0070664f6956225934aebb37c784f4ed&
		redirect_uri=http://localhost:8085/basic/kakaoLogin&
		response_type=code" >
		  <img src="//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
			    width="111" alt="카카오 로그인 버튼" />
			</a>
		</c:when>
		<c:otherwise>
			<p>
				${sessionScope.name }님 환영합니다.<br>
				<br> <a href="index">인덱스 페이지로 이동</a>
			</p>
		</c:otherwise>
	</c:choose>
</body>
</html>



