<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member</title>
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
		}  */
		document.getElementById('f').submit();
	}
	
	var req;
	function doubleCheck(){
		req = new XMLHttpRequest();
		req.onreadystatechange = result;
		var checkId = document.getElementById('id').value;
		console.log('checkId : ' + checkId);
		req.open('get', 'doubleCheck?id='+checkId);
		req.send(null);
	}
	function result(){
		if(req.readyState == 4 && req.status == 200){
			document.getElementById('doubleCheckMsg').innerHTML = req.responseText;
		}
	}
	function sendAuth(){
		req = new XMLHttpRequest();
		req.onreadystatechange = result;
		req.open('post', 'sendAuth');
		req.send(document.getElementById('email').value);
	}
	function checkAuth(){
		req = new XMLHttpRequest();
		req.onreadystatechange = result;
		req.open('post', 'checkAuth');
		req.send(document.getElementById('authNumber').value);
	}
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function daumPost(){
    	new daum.Postcode({
            oncomplete: function(data) {
            	var addr = "";
               if(data.userSelectedType == "R")
            	   addr = data.roadAddress;
               else
            	   addr = data.jibunAddress;
               
               document.getElementById('zonecode').value = data.zonecode;
               document.getElementById('addr1').value=addr;
               document.getElementById('addr2').focus();
            }
        }).open();
    }
</script>
</head>
<body>
<c:if test="${not empty msg }" >
	<script>alert("${msg}");</script>
</c:if>
	<!-- 
		클라이언트 : doubleCheck 자바스크립트 함수에서 서버로 사용자가 입력한 아이디를 갖고 요청 
		서버 : doubleCheck 요청을 받아 doubleCheck 자바 메서드호출
		서버 : service 객체 내doubleCheck 자바 메서드호출
		서버 : dao 객체 내 doubleCheck 자바 메서드호출
		서버 : memberMapper.xml 파일에서 <select> 태그로 중복 확인 쿼리문  동작
		서버 : 클라이언트 반환 데이터 "중복된 아이디" or "사용가능 아이디";
		클라이언트 : id="doubleCheckMsg" 태그에 출력.
	-->
	<font color="red" id="doubleCheckMsg"></font>
	<form action="member" method="post" id="f">
		<input type="text" name="id" id="id" placeholder="아이디">
		<input type="button" value="중복 확인" onclick="doubleCheck()">
		<br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"><br>
		<input type="password" name="confirmPw" id="confirmPw" placeholder="비밀번호 확인"><br>
		<input type="text" name="name" id="name" placeholder="이름"><br>
		
		<input type="text" name="email" id="email" placeholder="이메일">
		<input type="button" value="인증 번호 전송" onclick="sendAuth()"><br>
		
		<input type="text" name="authNumber" id="authNumber" placeholder="인증 번호">
		<input type="button" value="인증 번호 확인" onclick="checkAuth()"><br>
		
		<input type="text" name="zonecode" id="zonecode" placeholder="우편번호"
		readonly="readonly" >
		<input type="button" value="우편번호 찾기" onclick="daumPost()"><br>
		
	<input type="text" name="addr1" id="addr1" placeholder="주소" 
		readonly="readonly" style="width:275px;"><br>
		<input type="text" name="addr2" id="addr2" placeholder="상세 주소" ><br>
		
		<input type="button" value="회원 가입" onclick="check();">
		<input type="button" value="취소" onclick="location.href='index'">
	</form>
</body>
</html>