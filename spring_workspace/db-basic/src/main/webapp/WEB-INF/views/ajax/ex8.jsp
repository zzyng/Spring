<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex8</title>
<script>
	window.onload=start;
	function start(){
		var button = document.getElementById('button');
		button.onclick=send;
	}
	
	var req;
	function send(){ 
		req = new XMLHttpRequest();
		req.onreadystatechange = textChange;
		req.open('post', 'ex8');
		// 서버로 보낼 데이터는 select tag의 값과 사용자의 입력 값 두 개
		var t = document.getElementById('inputData').value;
		var s = document.getElementById('sel').value;
		var data = {inputData:t, sel:s};
		data = JSON.stringify(data); // JSON 데이터를 String 자료형으로 변환
		req.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
		req.send(data); 
	}
	
	function textChange(){
		if(req.readyState == 4 && req.status == 200){
			var jsonDatas = JSON.parse(req.responseText);
			console.log(jsonDatas);
			var data = "";
			
			for(i = 0; i < jsonDatas.cd.length; i++){
				data += "<tr>";
				data = data + "<td>" + jsonDatas.cd[i].title + "</td>";
				data = data + "<td>" + jsonDatas.cd[i].artist + "</td>";
				data = data + "<td>" + jsonDatas.cd[i].price + "</td>";
				data += "</tr>";
			}
			var tbody = document.getElementById('tbody');
			tbody.innerHTML = data;
		}
	
	}
</script></head>
<body>
	<select id="sel">
		<option value="title">제목</option>
		<option value="artist">아티스트</option>
		<option value="price">가격</option>
	</select>
	<input type="text" id="inputData" onkeyup="send()"> 
	<button id="button" type="button">데이터 가져오기</button>
	<table border=1>
		<thead>
			<tr>
				<th>title</th>
				<th>artist</th>
				<th>price</th>
			</tr>
		</thead>
		<tbody id="tbody">
			
		</tbody>
	</table>
</body>
</html>