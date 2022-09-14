<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex1</title>
<script>
	window.onload=start;
	function start(){
		/* alert('onload start'); */
		var link = document.getElementById('button');
		link.onclick=send;
	}
	var req;
	function send(){
		req = new XMLHttpRequest();
		req.onreadystatechange = textChange;
		req.open('post', 'ex3');
		req.send(null); //클라이언트가 서버로 데이터 전송 시 send(데이터) 담아서 전송
	}
	function textChange(){
		if(req.readyState == 4 && req.status == 200){
			console.log(req.responseText);
			var jsonData = JSON.parse(req.responseText); // String 데이터를 JSON 자료형으로 변환
			var label_id = document.getElementById('id');
			var label_pw = document.getElementById('pw');
			label_id.innerHTML = jsonData.id;
			label_pw.innerHTML = jsonData.pw;
			
		}
	}
</script></head>
<body>
	아이디 : <label id="id"></label><br>
	비밀번호 : <label id="pw"></label><br>
	<button id="button" type="button">정보 가져오기</button>
</body>
</html>